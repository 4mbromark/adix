package it.maramb.adix.dao;

import it.maramb.adix.entity.UserCredential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialDao extends CrudRepository<UserCredential, Long> {
}