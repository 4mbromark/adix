package it.maramb.adix.entity;

import it.maramb.adix.base.AdixBaseEntity;
import it.maramb.adix.base.AdixBaseEntityPermit;
import it.maramb.adix.domain.dtos.RoleDto;
import it.maramb.adix.entity.permits.UserRoles;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "ADIX_ROLE")
public class Role extends AdixBaseEntity<RoleDto> implements AdixBaseEntityPermit {

    @Column(name = "CODE", columnDefinition = "VARCHAR", length = 100, unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoles code;
    @Column(name = "NAME", columnDefinition = "VARCHAR", length = 100, unique = true, nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(name = "ENABLED", columnDefinition = "BIT", length = 1, nullable = false)
    @ColumnDefault("0")
    private Boolean enabled;

    @OneToMany(mappedBy = "parentRole")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Permit> childrenPermits;

    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<UserRole> userRoles;

    @Override
    public RoleDto toDto() {
        return RoleDto.builder()
                .code(code)
                .name(name)
                .description(description)
                .enabled(enabled)
                .build();
    }

    @Override
    public Boolean isEnabled() {
        return enabled;
    }

    @Override
    public Boolean isEnabledFor(User user) {
        return user.hasRole(code.toString()) && enabled;
    }
}
