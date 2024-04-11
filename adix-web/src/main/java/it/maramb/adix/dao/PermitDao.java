package it.maramb.adix.dao;

import it.maramb.adix.entity.Permit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitDao extends CrudRepository<Permit, Long> {
}
