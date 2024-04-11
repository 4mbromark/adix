package it.maramb.adix.domain.dtos;

import it.maramb.adix.entity.types.FulfillmentStatus;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FulfillmentRecordDto {

    private CustomerDto customer;
    private FulfillmentDto fulfillment;
    private Integer period;
    private FulfillmentStatus status;
    private Date dispatchDate;
    private String note;
}
