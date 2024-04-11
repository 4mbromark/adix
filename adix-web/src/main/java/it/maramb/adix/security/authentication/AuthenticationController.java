package it.maramb.adix.security.authentication;

import it.maramb.adix.domain.LoginRequest;
import it.maramb.adix.domain.Response;
import it.maramb.adix.entity.User;
import it.maramb.adix.security.AdixSecurityJwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Rest controller che gestisce l'autenticazione
 *
 * @author Marco Ambrosi <maramb>
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AdixSecurityJwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody @Valid LoginRequest request) {

        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generate(user)
                    ).body(Response.create(user.getId(), Response.Message.LOGIN_USER_SUCCESS));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Response.error(Response.Message.LOGIN_USER_NOT_FOUND));
        } catch (LockedException e) {
            String message = "Un utente disabilitato sta provando ad accedere";
            log.warn(message);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Response.error(Response.Message.LOGIN_USER_NOT_ENABLED));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Response.error(Response.Message.LOGIN_USER_BAD_CREDENTIALS));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<HttpStatus> verify() {
        Boolean valid = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        HttpStatus status = Boolean.TRUE.equals(valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).build();
    }

    @GetMapping("/user")
    public ResponseEntity<AuthenticationContainerProvider> getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthenticationContainer container = authenticationService.getAuthoritiesContainer(user);
        AuthenticationContainerProvider response = new AuthenticationContainerProvider(container);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
