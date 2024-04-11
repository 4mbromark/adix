package it.maramb.adix.domain.dtos;

import it.maramb.adix.entity.types.CustomerAccountingType;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private UserDto userPartner;
    private UserDto userInspector;
    private UserDto userResponsible;
    private UserDto userAccountant;
    private String businessName;
    private String vatNumber;
    private CustomerAccountingType accountingType;
    private Boolean delegation;
    private Boolean enabled;
    private String note;
}
