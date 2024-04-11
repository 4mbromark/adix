package it.maramb.adix.dao;

import it.maramb.adix.entity.User;
import it.maramb.adix.entity.UserPermit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermitDao extends CrudRepository<UserPermit, Long> {
    
}
