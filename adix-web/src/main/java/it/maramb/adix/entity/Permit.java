package it.maramb.adix.entity;

import it.maramb.adix.base.AdixBaseEntity;
import it.maramb.adix.base.AdixBaseEntityPermit;
import it.maramb.adix.domain.dtos.PermitDto;
import it.maramb.adix.entity.permits.UserPermits;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "ADIX_PERMIT")
public class Permit extends AdixBaseEntity<PermitDto> implements AdixBaseEntityPermit, GrantedAuthority {

    @Column(name = "CODE", columnDefinition = "VARCHAR", length = 100, unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserPermits code;
    @Column(name = "NAME", columnDefinition = "VARCHAR", length = 100, unique = true, nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(name = "ENABLED", columnDefinition = "BIT", length = 1, nullable = false)
    @ColumnDefault("0")
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "PARENT_ROLE", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Role parentRole;
    @ManyToOne
    @JoinColumn(name = "PARENT_PERMIT", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Permit parentPermit;

    @OneToMany(mappedBy = "parentPermit")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Permit> childrenPermits;

    @OneToMany(mappedBy = "permit")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<UserPermit> userPermits;

    @Override
    public PermitDto toDto() {
        return PermitDto.builder()
                .code(code)
                .name(name)
                .description(description)
                .enabled(enabled)
                .build();
    }

    @Override
    public String getAuthority() {
        return code.toString();
    }

    @Override
    public Boolean isEnabled() {
        return enabled;
    }

    @Override
    public Boolean isEnabledFor(User user) {
        return (Objects.isNull(parentRole) || parentRole.isEnabledFor(user)) &&
                (Objects.isNull(parentPermit) || parentPermit.isEnabledFor(user)) &&
                Boolean.TRUE.equals(enabled) &&
                (user.isAdmin() || !UserPermits.isAdminAuthority(code));
    }
}
