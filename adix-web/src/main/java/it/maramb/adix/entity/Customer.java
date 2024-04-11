package it.maramb.adix.entity;

import it.maramb.adix.base.AdixBaseEntity;
import it.maramb.adix.domain.dtos.CustomerDto;
import it.maramb.adix.entity.types.CustomerAccountingType;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "ADIX_CUSTOMER")
@DynamicInsert
public class Customer extends AdixBaseEntity<CustomerDto> {

    @Column(name = "CODE", columnDefinition = "CHAR", length = 2, unique = true, nullable = false)
    private String code;
    @Column(name = "BUSINESS_NAME", columnDefinition = "VARCHAR", length = 256, unique = true, nullable = false)
    private String businessName;
    @Column(name = "VAT_NUMBER", columnDefinition = "CHAR", length = 11, unique = true, nullable = false)
    private String vatNumber;
    @Column(name = "ACCOUNTING_TYPE", columnDefinition = "VARCHAR", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerAccountingType accountingType;
    @Column(name = "DELEGATION", columnDefinition = "BIT", length = 1, nullable = false)
    @ColumnDefault("0")
    private Boolean delegation;
    @Column(name = "ENABLED", columnDefinition = "BIT", length = 1, nullable = false)
    @ColumnDefault("0")
    private Boolean enabled;
    @Column(name = "NOTE", columnDefinition = "TEXT")
    private String note;

    @ManyToOne
    @JoinColumn(name = "ID_USER_PARTNER", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User userPartner;
    @ManyToOne
    @JoinColumn(name = "ID_USER_INSPECTOR", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User userInspector;
    @ManyToOne
    @JoinColumn(name = "ID_USER_RESPONSIBLE", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User userResponsible;
    @ManyToOne
    @JoinColumn(name = "ID_USER_ACCOUNTANT", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User userAccountant;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FulfillmentRecord> fulfillmentRecords;

    public CustomerDto toDto() {
        return CustomerDto.builder()
                .userPartner(Objects.nonNull(userPartner) ? userPartner.toDto() : null)
                .userInspector(Objects.nonNull(userInspector) ? userInspector.toDto() : null)
                .userResponsible(Objects.nonNull(userResponsible) ? userResponsible.toDto() : null)
                .userAccountant(Objects.nonNull(userAccountant) ? userAccountant.toDto() : null)
                .businessName(businessName)
                .vatNumber(vatNumber)
                .accountingType(accountingType)
                .delegation(delegation)
                .enabled(enabled)
                .note(note)
                .build();
    }
}
