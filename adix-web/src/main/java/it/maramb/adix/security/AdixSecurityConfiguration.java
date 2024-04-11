package it.maramb.adix.security;

import it.maramb.adix.entity.Permit;
import it.maramb.adix.entity.permits.UserPermits;
import it.maramb.adix.entity.permits.UserRoles;
import it.maramb.adix.security.authentication.AuthenticationService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import static java.lang.String.format;

/**
 * Classe che gestisce l'autenticazione di Spring
 *
 * @see <a href="https://www.toptal.com/spring/spring-security-tutorial">Fonte per classe gestione sicurezza (spring boot 2.6.14)</a>
 * @see <a href="https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter#ldap-authentication">Fonte per passaggio a jakarta (spring boot 3.0.1)</a>
 * @see <a href="https://reflectoring.io/bean-validation-with-spring-boot/">Fonte per validazione bean</a>
 * @author Marco Ambrosi <maramb>
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Slf4j
public class AdixSecurityConfiguration {

    @Autowired
    private AdixSecurityJwtTokenFilter jwtTokenFilter;
    @Autowired
    private AuthenticationService authenticationService;

    @PostConstruct
    private void postConstruct() {
        log.info("[ADIX SECURITY] Inizializzazione in corso");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authentication -> authentication
                        // Our public endpoints
                        .requestMatchers("/auth/login", "/auth/verify").permitAll()
                        // Our private endpoints
                        // .requestMatchers("/actuator/**").hasAuthority(UserPermits.Constants.ROLE_SUPER_MANAGEMENT)
                        .anyRequest().authenticated()
                )
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(authenticationService)
                .build();
    }
}
