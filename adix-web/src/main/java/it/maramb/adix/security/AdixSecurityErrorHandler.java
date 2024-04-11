package it.maramb.adix.security;

import io.jsonwebtoken.security.SignatureException;
import it.maramb.adix.domain.Response;
import it.maramb.adix.security.exceptions.ApplicationReloadedSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



/**
 * @see <a href="https://www.baeldung.com/exception-handling-for-rest-with-spring">...</a>
 */
@ControllerAdvice
@Slf4j
public class AdixSecurityErrorHandler {

    @ExceptionHandler({ ApplicationReloadedSecurityException.class })
    public ResponseEntity<Response> handleSignatureExceptionExceptions(ApplicationReloadedSecurityException e) {
        String message = "Applicazione riavviata, token non più valido";
        log.warn(message);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Response.error(Response.Message.LOGIN_USER_APPLICATION_RELOADED));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Response.error(Response.Message.ACCESS_DENIED));
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<Response> handleValidatorExceptions(Exception e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.error(Response.Message.REQUEST_VALIDATION_FAILED));
    }
}
