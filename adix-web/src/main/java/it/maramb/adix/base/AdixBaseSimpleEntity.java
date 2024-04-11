package it.maramb.adix.base;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Primary;

import java.util.Date;

@Getter
@MappedSuperclass
public class AdixBaseSimpleEntity {

    @Id
    @Column(name = "ID", columnDefinition = "BIGINT", unique = true, nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(name = "INSERT_DATE", columnDefinition = "TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("current_timestamp")
    private Date insertDate;
    @UpdateTimestamp
    @Column(name = "UPDATE_DATE", columnDefinition = "TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
}
