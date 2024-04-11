package it.maramb.adix.domain.dtos;

import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FulfillmentDto {

    private String name;
    private String description;
    private Integer monthRecurrence;
    private Boolean enabled;
    private String note;
}
