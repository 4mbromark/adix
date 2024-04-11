package it.maramb.adix.entity;

import it.maramb.adix.base.AdixBaseEntity;
import it.maramb.adix.base.AdixBaseEntityPermitWithUser;
import it.maramb.adix.base.AdixBaseSimpleEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "ADIX_USER_ROLE", uniqueConstraints = { @UniqueConstraint(name = "UNIQUE_USER_ROLE", columnNames = { "ID_USER", "ID_ROLE" }) })
public class UserRole extends AdixBaseSimpleEntity implements AdixBaseEntityPermitWithUser {

    @Column(name = "ENABLED", columnDefinition = "BIT", length = 1, nullable = false)
    @ColumnDefault("0")
    private Boolean enabled;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Role role;

    @Override
    public String getCode() {
        return role.getCode().toString();
    }

    @Override
    public Boolean isEnabled() {
        return role.isEnabled() && enabled;
    }
}
