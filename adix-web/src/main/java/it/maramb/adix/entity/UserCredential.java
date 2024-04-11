package it.maramb.adix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.maramb.adix.base.AdixBaseEntity;
import it.maramb.adix.base.AdixBaseSimpleEntity;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "ADIX_USER_CREDENTIAL")
public class UserCredential extends AdixBaseSimpleEntity {

    @Column(name = "PASSWORD", columnDefinition = "VARCHAR", length = 256, nullable = false)
    private String password;
    @Column(name = "TO_CHANGE", columnDefinition = "BIT", length = 1, nullable = false)
    @ColumnDefault("0")
    private Boolean toChange;
    @Column(name = "LAST_CHANGE", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChange;
    @Column(name = "EXPIRES", columnDefinition = "BIT", length = 1, nullable = false)
    @ColumnDefault("0")
    private Boolean expires;
    @Column(name = "EXPIRES_EVERY_DAYS", columnDefinition = "INTEGER")
    private Integer expiresEveryDays;
    @Column(name = "EXPIRES_ON", columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private Date expiresOn;

    @OneToOne(optional = false)
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    public Boolean isValid() {
        return Objects.nonNull(password) && !toChange && (!expires || (new Date()).before(expiresOn));
    }
}
