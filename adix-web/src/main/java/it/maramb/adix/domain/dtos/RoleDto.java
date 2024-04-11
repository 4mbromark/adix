package it.maramb.adix.domain.dtos;

import it.maramb.adix.entity.permits.UserRoles;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private UserRoles code;
    private String name;
    private String description;
    private Boolean enabled;
}