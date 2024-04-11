package it.maramb.adix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Classe di configurazione Spring principale
 *
 * @author Marco Ambrosi <maramb>
 */
@SpringBootApplication
@Import({ AdixApplicationConfiguration.class })
public class AdixApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AdixApplication.class, args);
    }
}
