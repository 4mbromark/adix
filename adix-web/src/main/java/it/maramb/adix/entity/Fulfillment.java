package it.maramb.adix.entity;

import it.maramb.adix.base.AdixBaseEntity;
import it.maramb.adix.domain.dtos.FulfillmentDto;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "ADIX_FULFILLMENT")
@DynamicInsert
public class Fulfillment extends AdixBaseEntity<FulfillmentDto> {

    @Column(name = "NAME", columnDefinition = "VARCHAR", length = 256, unique = true, nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(name = "MONTH_RECURRENCE", columnDefinition = "INT", nullable = false)
    private Integer monthRecurrence;
    @Column(name = "ENABLED", columnDefinition = "BIT", length = 1, nullable = false)
    @ColumnDefault("0")
    private Boolean enabled;
    @Column(name = "NOTE", columnDefinition = "TEXT")
    private String note;

    @OneToMany(mappedBy = "fulfillment")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FulfillmentRecord> fulfillmentRecords;

    @Override
    public FulfillmentDto toDto() {
        return FulfillmentDto.builder()
                .name(name)
                .description(description)
                .monthRecurrence(monthRecurrence)
                .enabled(enabled)
                .note(note)
                .build();
    }
}

