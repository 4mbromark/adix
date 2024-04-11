package it.maramb.adix.service;

import it.maramb.adix.base.AdixBaseRecordService;
import it.maramb.adix.dao.FulfillmentDao;
import it.maramb.adix.domain.dtos.FulfillmentDto;
import it.maramb.adix.domain.requests.CreateUpdateFulfillmentRequest;
import it.maramb.adix.entity.Fulfillment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FulfillmentService extends AdixBaseRecordService<Fulfillment, FulfillmentDto, CreateUpdateFulfillmentRequest> {

    private FulfillmentDao fulfillmentDao;

    @Autowired
    protected FulfillmentService(FulfillmentDao fulfillmentDao) {
        super(fulfillmentDao);
        this.fulfillmentDao = fulfillmentDao;
    }
}
