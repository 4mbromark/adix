package it.maramb.adix.base;

import it.maramb.adix.domain.FetchRecordResponse;
import it.maramb.adix.domain.Response;
import it.maramb.adix.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdixBaseRecordService<E extends AdixBaseEntity<D>, D, R extends AdixBaseCreateUpdateRequest<E>> {

    protected CrudRepository dao;

    protected AdixBaseRecordService(CrudRepository dao) {
        this.dao = dao;
    }

    public FetchRecordResponse<E, D> fetch(User loggedUser) {
        Iterable<E> recordIterable = dao.findAll();
        List<E> records = new ArrayList<>();
        recordIterable.forEach(records::add);

        return FetchRecordResponse.create(records);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Response create(User loggedUser, R request) {
        E record = request.toEntity();

        dao.save(record);
        return Response.create(record.getId(), Response.Message.RECORD_SAVED);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Response update(User loggedUser, R request, Long id) {
        Optional<E> recordOptional = dao.findById(id);

        if (!recordOptional.isPresent()) {
            return Response.error(Response.Message.RECORD_NOT_FOUND);
        }

        E record = recordOptional.get();
        request.updateEntity(record);

        dao.save(record);
        return Response.create(record.getId(), Response.Message.RECORD_UPDATED);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Response delete(User loggedUser, Long id) {
        Optional<E> recordOptional = dao.findById(id);

        if (!recordOptional.isPresent()) {
            return Response.error(Response.Message.RECORD_NOT_FOUND);
        }

        E record = recordOptional.get();
        dao.delete(record);
        return Response.create(record.getId(), Response.Message.RECORD_DELETED);
    }
}
