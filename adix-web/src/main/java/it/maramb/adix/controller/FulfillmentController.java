package it.maramb.adix.controller;

import it.maramb.adix.base.AdixBaseRecordController;
import it.maramb.adix.domain.*;
import it.maramb.adix.domain.dtos.FulfillmentDto;
import it.maramb.adix.domain.requests.CreateUpdateFulfillmentRequest;
import it.maramb.adix.entity.Fulfillment;
import it.maramb.adix.entity.permits.UserPermits;
import it.maramb.adix.service.FulfillmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fulfillments")
public class FulfillmentController extends AdixBaseRecordController<Fulfillment, FulfillmentDto, CreateUpdateFulfillmentRequest> {

    private FulfillmentService fulfillmentService;

    @Autowired
    protected FulfillmentController(FulfillmentService fulfillmentService) {
        super(fulfillmentService);
        this.fulfillmentService = fulfillmentService;
    }

    @Override
    @GetMapping
    @Secured({ UserPermits.Constants.ANY_ROLE_VIEW_ALL_FULFILLMENTS })
    public ResponseEntity<FetchRecordResponse> fetch() {
        return doFetch();
    }

    @Override
    @PostMapping
    @Secured({ UserPermits.Constants.ANY_ROLE_ADD_FULFILLMENT })
    public ResponseEntity<Response> add(@RequestBody @Valid CreateUpdateFulfillmentRequest request) {
        return doAdd(request);
    }

    @Override
    @PutMapping("/{id}")
    @Secured({ UserPermits.Constants.ANY_ROLE_EDIT_FULFILLMENT })
    public ResponseEntity<Response> update(@RequestBody @Valid CreateUpdateFulfillmentRequest request, @PathVariable("id") @NotNull Long id) {
        return doUpdate(request, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @Secured({ UserPermits.Constants.ANY_ROLE_DELETE_FULFILLMENT })
    public ResponseEntity<Response> delete(@PathVariable("id") @NotNull Long id) {
        return doDelete(id);
    }
}
