package it.maramb.adix.security.authentication;

import it.maramb.adix.domain.Response;
import it.maramb.adix.security.authentication.AuthenticationContainer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationContainerProvider extends Response {
    private AuthenticationContainer container;
}
