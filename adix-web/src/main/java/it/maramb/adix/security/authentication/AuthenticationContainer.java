package it.maramb.adix.security.authentication;

import it.maramb.adix.domain.dtos.UserDto;
import it.maramb.adix.entity.permits.UserPermits;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationContainer {
    private UserDto user;
    private List<UserPermits> permits;
}
