package it.maramb.adix;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Classe di configurazione Spring
 * Esegue il component scan
 *
 * @see <a href="https://stackoverflow.com/questions/55324610/exception-handler-not-working-on-a-spring-boot-app-restcontrolleradvice-is-nev">Fonte per annotazione @EnableWebMvc</a>
 * @author Marco Ambrosi <maramb>
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class AdixApplicationConfiguration {

    public static final String DEFAULT = "classpath:application.properties";
    public static final String LOCAL = "classpath:application.local.properties";

    @Configuration
    @Profile({ "local", "default" })
    @PropertySource({ DEFAULT, LOCAL })
    static class LocalConfig {
    }
}
