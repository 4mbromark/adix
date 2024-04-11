package it.maramb.adix.dao;

import it.maramb.adix.entity.FulfillmentRecord;
import it.maramb.adix.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FulfillmentRecordDao extends CrudRepository<FulfillmentRecord, Long> {

}
