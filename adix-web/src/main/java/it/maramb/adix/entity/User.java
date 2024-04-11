package it.maramb.adix.entity;


import it.maramb.adix.base.AdixBaseEntity;
import it.maramb.adix.entity.permits.UserPermits;
import it.maramb.adix.entity.permits.UserRoles;
import it.maramb.adix.security.authentication.AuthenticationContainer;
import it.maramb.adix.domain.dtos.UserDto;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "ADIX_USER")
@DynamicInsert
public class User extends AdixBaseEntity<UserDto> implements UserDetails {

    @Column(name = "CODE", columnDefinition = "CHAR", length = 2, unique = true, nullable = false)
    private String code;
    @Column(name = "EMAIL_ADDRESS", columnDefinition = "VARCHAR", length = 256, unique = true, nullable = false)
    private String emailAddress;
    @Column(name = "FIRST_NAME", columnDefinition = "VARCHAR", length = 256, nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", columnDefinition = "VARCHAR", length = 256, nullable = false)
    private String lastName;
    @Column(name = "IS_ADMIN", columnDefinition = "BIT", length = 1, nullable = false)
    @ColumnDefault("0")
    private Boolean isAdmin;
    @Column(name = "ENABLED", columnDefinition = "BIT", length = 1, nullable = false)
    @ColumnDefault("0")
    private Boolean enabled;
    @Column(name = "NOTE", columnDefinition = "TEXT")
    private String note;

    @OneToOne(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserCredential userCredential;
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<UserRole> userRoles;
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<UserPermit> userPermits;

    @OneToMany(mappedBy = "userPartner")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Customer> partnerCustomers;
    @OneToMany(mappedBy = "userInspector")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Customer> inspectorCustomers;
    @OneToMany(mappedBy = "userResponsible")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Customer> responsibleCustomers;
    @OneToMany(mappedBy = "userAccountant")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Customer> accountantCustomers;

    @Override
    public UserDto toDto() {
        return UserDto.builder()
                .code(code)
                .emailAddress(emailAddress)
                .firstName(firstName)
                .lastName(lastName)
                .isAdmin(isAdmin)
                .enabled(enabled)
                .note(note)
                .roles(getRoleList())
                .build();
    }

    public AuthenticationContainer toContainer() {
        UserDto user = toDto();
        List<UserPermits> userPermits = getPermitList();

        return new AuthenticationContainer(user, userPermits);
    }

    public Collection<Role> getRoles() {
        return userRoles.stream()
                .filter(UserRole::isEnabled)
                .map(UserRole::getRole)
                .collect(Collectors.toList());
    }

    public List<UserRoles> getRoleList() {
        return getRoles().stream().map(Role::getCode).collect(Collectors.toList());
    }

    public Collection<Permit> getPermits() {
        return userPermits.stream()
                .filter(UserPermit::isEnabled)
                .map(UserPermit::getPermit)
                .collect(Collectors.toList());
    }

    public List<UserPermits> getPermitList() {
        return getPermits().stream().map(Permit::getCode).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getPermits();
    }

    @Override
    public String getUsername() {
        return code + ":" + emailAddress;
    }

    @Override
    public String getPassword() {
        return Objects.nonNull(userCredential) ? userCredential.getPassword() : null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userCredential.isValid();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Boolean isAdmin() {
        return getIsAdmin();
    }

    public Boolean hasRole(String role) {
        return this.userRoles.stream().filter(UserRole::isEnabled).map(UserRole::getCode).collect(Collectors.toList()).contains(role);
    }

    public Boolean hasPermit(String permit) {
        return this.userPermits.stream().filter(UserPermit::isEnabled).map(UserPermit::getCode).collect(Collectors.toList()).contains(permit);
    }
}
