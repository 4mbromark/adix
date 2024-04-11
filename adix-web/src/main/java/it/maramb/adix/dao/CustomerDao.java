package it.maramb.adix.dao;

import it.maramb.adix.entity.Customer;
import it.maramb.adix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Long> {

    Iterable<Customer> findByUserPartner(User userPartner);

    Iterable<Customer> findByUserInspector(User userInspector);

    Iterable<Customer> findByUserResponsible(User userManager);

    Iterable<Customer> findByUserAccountant(User userAccountant);
}
