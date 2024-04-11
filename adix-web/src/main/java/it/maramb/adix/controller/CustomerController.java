package it.maramb.adix.controller;

import it.maramb.adix.base.AdixBaseRecordController;
import it.maramb.adix.domain.requests.CreateUpdateCustomerRequest;
import it.maramb.adix.domain.dtos.CustomerDto;
import it.maramb.adix.domain.FetchRecordResponse;
import it.maramb.adix.domain.Response;
import it.maramb.adix.entity.Customer;
import it.maramb.adix.entity.User;
import it.maramb.adix.entity.permits.UserPermits;
import it.maramb.adix.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController extends AdixBaseRecordController<Customer, CustomerDto, CreateUpdateCustomerRequest> {

    private CustomerService customerService;

    @Autowired
    protected CustomerController(CustomerService customerService) {
        super(customerService);
        this.customerService = customerService;
    }

    @Override
    @GetMapping
    @Secured({ UserPermits.Constants.ANY_ROLE_VIEW_ALL_CUSTOMERS })
    public ResponseEntity<FetchRecordResponse> fetch() {
        return doFetch();
    }

    @GetMapping("/partner")
    @Secured({ UserPermits.Constants.ROLE_PARTNER_VIEW_OWN_CUSTOMERS })
    public ResponseEntity<FetchRecordResponse> fetchByPartner() {
        User loggedUser = getUserFromSecurityContext();

        FetchRecordResponse<Customer, CustomerDto> response = customerService.fetchByPartner(loggedUser);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/inspector")
    @Secured({ UserPermits.Constants.ROLE_INSPECTOR_VIEW_OWN_CUSTOMERS })
    public ResponseEntity<FetchRecordResponse> fetchByInspector() {
        User loggedUser = getUserFromSecurityContext();

        FetchRecordResponse<Customer, CustomerDto> response = customerService.fetchByInspector(loggedUser);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/manager")
    @Secured({ UserPermits.Constants.ROLE_RESPONSIBLE_VIEW_OWN_CUSTOMERS })
    public ResponseEntity<FetchRecordResponse> fetchByResponsible() {
        User loggedUser = getUserFromSecurityContext();

        FetchRecordResponse<Customer, CustomerDto> response = customerService.fetchByResponsible(loggedUser);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/accountant")
    @Secured({ UserPermits.Constants.ROLE_ACCOUNTANT_VIEW_OWN_CUSTOMERS })
    public ResponseEntity<FetchRecordResponse> fetchByAccountant() {
        User loggedUser = getUserFromSecurityContext();

        FetchRecordResponse<Customer, CustomerDto> response = customerService.fetchByAccountant(loggedUser);
        return ResponseEntity.ok().body(response);
    }

    @Override
    @PostMapping
    @Secured({ UserPermits.Constants.ANY_ROLE_ADD_CUSTOMER })
    public ResponseEntity<Response> add(@RequestBody @Valid CreateUpdateCustomerRequest request) {
        return doAdd(request);
    }

    @Override
    @PutMapping("/{id}")
    @Secured({ UserPermits.Constants.ANY_ROLE_EDIT_CUSTOMER })
    public ResponseEntity<Response> update(@RequestBody @Valid CreateUpdateCustomerRequest request, @PathVariable("id") @NotNull Long id) {
        return doUpdate(request, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @Secured({ UserPermits.Constants.ANY_ROLE_DELETE_CUSTOMER })
    public ResponseEntity<Response> delete(@PathVariable("id") @NotNull Long id) {
        return doDelete(id);
    }
}
