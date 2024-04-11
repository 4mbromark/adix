package it.maramb.adix.dao;

import it.maramb.adix.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    Optional<User> findFirstByCodeAndEmailAddress(String code, String emailAddress);
}
