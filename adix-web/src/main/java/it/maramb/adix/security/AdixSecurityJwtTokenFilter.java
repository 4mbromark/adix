package it.maramb.adix.security;

import it.maramb.adix.security.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Classe che verifica il token delle chiamate in ingresso
 *
 * @see <a href="https://www.toptal.com/spring/spring-security-tutorial">Fonte per classe gestione sicurezza</a>
 * @see <a href="https://reflectoring.io/bean-validation-with-spring-boot/">Fonte per validazione bean</a>
 * @author Marco Ambrosi <maramb>
 */
@Component
public class AdixSecurityJwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        authenticationService.doFilter(request, response, filterChain);
    }
}
