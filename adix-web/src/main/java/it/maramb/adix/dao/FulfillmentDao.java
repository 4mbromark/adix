package it.maramb.adix.dao;

import it.maramb.adix.entity.Fulfillment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FulfillmentDao extends CrudRepository<Fulfillment, Long> {

}
