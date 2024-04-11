package it.maramb.adix.service;

import it.maramb.adix.base.AdixBaseRecordService;
import it.maramb.adix.dao.CustomerDao;
import it.maramb.adix.domain.requests.CreateUpdateCustomerRequest;
import it.maramb.adix.domain.dtos.CustomerDto;
import it.maramb.adix.domain.FetchRecordResponse;
import it.maramb.adix.domain.Response;
import it.maramb.adix.entity.Customer;
import it.maramb.adix.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CustomerService extends AdixBaseRecordService<Customer, CustomerDto, CreateUpdateCustomerRequest> {

    private CustomerDao customerDao;

    @Autowired
    protected CustomerService(CustomerDao customerDao) {
        super(customerDao);
        this.customerDao = customerDao;
    }

    public FetchRecordResponse<Customer, CustomerDto> fetchByPartner(User loggedUser) {
        Iterable<Customer> customerIterable = customerDao.findByUserPartner(loggedUser);
        List<Customer> customers = new ArrayList<>();
        customerIterable.forEach(customers::add);

        return FetchRecordResponse.create(customers);
    }

    public FetchRecordResponse<Customer, CustomerDto> fetchByInspector(User loggedUser) {
        Iterable<Customer> customerIterable = customerDao.findByUserInspector(loggedUser);
        List<Customer> customers = new ArrayList<>();
        customerIterable.forEach(customers::add);

        return FetchRecordResponse.create(customers);
    }

    public FetchRecordResponse<Customer, CustomerDto> fetchByResponsible(User loggedUser) {
        Iterable<Customer> customerIterable = customerDao.findByUserResponsible(loggedUser);
        List<Customer> customers = new ArrayList<>();
        customerIterable.forEach(customers::add);

        return FetchRecordResponse.create(customers);
    }

    public FetchRecordResponse<Customer, CustomerDto> fetchByAccountant(User loggedUser) {
        Iterable<Customer> customerIterable = customerDao.findByUserAccountant(loggedUser);
        List<Customer> customers = new ArrayList<>();
        customerIterable.forEach(customers::add);

        return FetchRecordResponse.create(customers);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Response toggleEnabled(User loggedUser, Long id, Boolean enabled) {
        Optional<Customer> customerOptional = customerDao.findById(id);

        if (!customerOptional.isPresent()) {
            return Response.error(Response.Message.CUSTOMER_NOT_FOUND);
        }

        Customer customer = customerOptional.get();
        customer.setEnabled(enabled);

        customerDao.save(customer);
        return Response.create(customer.getId(), Response.Message.CUSTOMER_UPDATED);
    }
}
