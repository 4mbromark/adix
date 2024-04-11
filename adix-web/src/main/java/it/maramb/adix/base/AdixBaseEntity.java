package it.maramb.adix.base;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
public abstract class AdixBaseEntity<D> extends AdixBaseSimpleEntity {

    public abstract D toDto();
}
