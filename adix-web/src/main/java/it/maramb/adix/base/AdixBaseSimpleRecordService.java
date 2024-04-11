package it.maramb.adix.base;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdixBaseSimpleRecordService<E extends AdixBaseSimpleEntity> {

    protected CrudRepository dao;

    protected AdixBaseSimpleRecordService(CrudRepository dao) {
        this.dao = dao;
    }

    public List<E> getAll() {
        Iterable<E> recordIterable = dao.findAll();
        List<E> records = new ArrayList<>();
        recordIterable.forEach(records::add);

        return records;
    }

    public Long count() {
        return dao.count();
    }

    public Boolean existsById(Long id) {
        return dao.existsById(id);
    }

    public Optional<E> getById(Long id) {
        return dao.findById(id);
    }

    public E insertUpdate(E record) {
        return (E) dao.save(record);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    public void delete(E record) {
        dao.delete(record);
    }
}
