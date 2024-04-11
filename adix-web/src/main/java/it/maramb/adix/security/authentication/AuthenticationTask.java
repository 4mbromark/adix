package it.maramb.adix.security.authentication;

import it.maramb.adix.entity.UserCredential;
import it.maramb.adix.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class AuthenticationTask {

    @Autowired
    private UserCredentialService userCredentialService;

    // @Scheduled
    public void updateExpiredCredentialsTask() {
        List<UserCredential> userCredentials = userCredentialService.getAll();

        userCredentials.stream().filter(userCredential ->
                userCredential.getUser().isEnabled() && userCredential.getExpires() && !userCredential.getToChange()
        ).forEach(userCredential -> {
            Date date = new Date();

            Boolean isToChange = date.after(userCredential.getExpiresOn());

            if (isToChange) {
                userCredential.setToChange(true);
                userCredentialService.insertUpdate(userCredential);
            }
        });
    }
}
