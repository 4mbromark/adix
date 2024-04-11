package it.maramb.adix.base;

public abstract class AdixBaseCreateUpdateRequest<E extends AdixBaseEntity> {

    public abstract E toEntity();

    public abstract E updateEntity(E entity);
}
