package it.maramb.adix.service;

import it.maramb.adix.base.AdixBaseSimpleRecordService;
import it.maramb.adix.dao.FulfillmentDao;
import it.maramb.adix.dao.UserCredentialDao;
import it.maramb.adix.entity.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserCredentialService extends AdixBaseSimpleRecordService<UserCredential> {

    private UserCredentialDao userCredentialDao;

    @Autowired
    protected UserCredentialService(UserCredentialDao userCredentialDao) {
        super(userCredentialDao);
        this.userCredentialDao = userCredentialDao;
    }
}
