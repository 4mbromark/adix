package it.maramb.adix.entity;

import it.maramb.adix.base.AdixBaseEntity;
import it.maramb.adix.base.AdixBaseEntityPermitWithUser;
import it.maramb.adix.base.AdixBaseSimpleEntity;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "ADIX_USER_PERMIT", uniqueConstraints = { @UniqueConstraint(name = "UNIQUE_USER_PERMIT", columnNames = { "ID_USER", "ID_PERMIT" }) })
public class UserPermit extends AdixBaseSimpleEntity implements AdixBaseEntityPermitWithUser {

    @Column(name = "ENABLED", columnDefinition = "BIT", length = 1, nullable = false)
    @ColumnDefault("0")
    private Boolean enabled;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PERMIT", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Permit permit;

    @Override
    public String getCode() {
        return permit.getCode().toString();
    }

    @Override
    public Boolean isEnabled() {
        return permit.isEnabledFor(user) && enabled;
    }
}
