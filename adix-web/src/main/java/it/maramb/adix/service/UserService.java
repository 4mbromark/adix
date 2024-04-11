package it.maramb.adix.service;

import it.maramb.adix.base.AdixBaseRecordService;
import it.maramb.adix.dao.UserDao;
import it.maramb.adix.domain.*;
import it.maramb.adix.domain.dtos.UserDto;
import it.maramb.adix.domain.requests.CreateUpdateUserRequest;
import it.maramb.adix.domain.responses.FetchPermitResponse;
import it.maramb.adix.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService extends AdixBaseRecordService<User, UserDto, CreateUpdateUserRequest> {

    private UserDao userDao;

    @Autowired
    protected UserService(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Response toggleEnabled(Long id, Boolean enabled) {
        Optional<User> userOptional = userDao.findById(id);

        if (!userOptional.isPresent()) {
            return Response.error(Response.Message.USER_NOT_FOUND);
        }

        User user = userOptional.get();
        user.setEnabled(enabled);

        userDao.save(user);
        return Response.create(user.getId(), Response.Message.USER_UPDATED);
    }

    public Response fetchPermits(User loggedUser, Long id) {
        Optional<User> userOptional = userDao.findById(id);

        if (!userOptional.isPresent()) {
            return Response.error(Response.Message.USER_NOT_FOUND);
        }

        User user = userOptional.get();

        return FetchPermitResponse.create(user.getPermitList());
    }
}
