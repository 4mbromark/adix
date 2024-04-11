package it.maramb.adix.domain.dtos;

import it.maramb.adix.entity.permits.UserRoles;
import lombok.*;

import jakarta.persistence.Column;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String code;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private Boolean isAdmin;
    private Boolean enabled;
    private String note;
    private List<UserRoles> roles;
}
