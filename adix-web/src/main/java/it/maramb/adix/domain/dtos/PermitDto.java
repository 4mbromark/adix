package it.maramb.adix.domain.dtos;

import it.maramb.adix.entity.permits.UserPermits;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermitDto {

    private UserPermits code;
    private String name;
    private String description;
    private Boolean enabled;
}
