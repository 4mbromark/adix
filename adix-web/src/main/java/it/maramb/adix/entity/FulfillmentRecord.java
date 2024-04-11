package it.maramb.adix.entity;


import it.maramb.adix.base.AdixBaseEntity;
import it.maramb.adix.domain.dtos.FulfillmentRecordDto;
import it.maramb.adix.entity.types.FulfillmentStatus;
import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "ADIX_FULFILLMENT_RECORD", uniqueConstraints = { @UniqueConstraint(name = "UNIQUE_FULFILLMENT_RECORD", columnNames = { "ID_CUSTOMER", "PERIOD" }) })
public class FulfillmentRecord extends AdixBaseEntity<FulfillmentRecordDto> {

    @Column(name = "PERIOD", columnDefinition = "ID", nullable = false)
    private Integer period;
    @Column(name = "STATUS", columnDefinition = "VARCHAR", length = 20, nullable = false)
    private FulfillmentStatus status;
    @Column(name = "DISPATCH_DATE", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dispatchDate;
    @Column(name = "NOTE", columnDefinition = "TEXT")
    private String note;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CUSTOMER", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_FULFILLMENT", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Fulfillment fulfillment;

    @Override
    public FulfillmentRecordDto toDto() {
        return FulfillmentRecordDto.builder()
                .customer(customer.toDto())
                .fulfillment(fulfillment.toDto())
                .period(period)
                .status(status)
                .dispatchDate(dispatchDate)
                .note(note)
                .build();
    }
}
