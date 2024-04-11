package it.maramb.adix.security.authentication;

import it.maramb.adix.dao.UserDao;
import it.maramb.adix.domain.Response;
import it.maramb.adix.entity.Permit;
import it.maramb.adix.entity.User;
import it.maramb.adix.security.AdixSecurityJwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuthenticationService implements UserDetailsManager {

    @Autowired
    private AdixSecurityJwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDao userDao;

    public User getUserFromSecurityContext() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Optional<User> getByUsername(String username) throws UsernameNotFoundException {
        String[] usernameParts = username.split(":");
        return userDao.findFirstByCodeAndEmailAddress(usernameParts[0], usernameParts[1]);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String username) {
        UserDetails userDetails = getByUsername(username).orElse(null);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                Objects.nonNull(userDetails) ?
                        userDetails.getAuthorities() : List.of()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = getByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(Response.Message.LOGIN_USER_NOT_FOUND.toString());
        }

        return userOptional.get();
    }

    public AuthenticationContainer getAuthoritiesContainer(User loggedUser) {
       Optional<User> userOptional = userDao.findById(loggedUser.getId());
       return userOptional.get().toContainer();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteUser(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(String username) {
        return getByUsername(username).isPresent();
    }

    public void doFilter(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(header) || header.isBlank() || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken
                authentication = getAuthentication(jwtTokenUtil.getUsername(token));

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
