package it.maramb.adix.controller;

import it.maramb.adix.base.AdixBaseRecordController;
import it.maramb.adix.domain.*;
import it.maramb.adix.domain.dtos.CustomerDto;
import it.maramb.adix.domain.dtos.UserDto;
import it.maramb.adix.domain.requests.CreateUpdateUserRequest;
import it.maramb.adix.domain.responses.FetchPermitResponse;
import it.maramb.adix.entity.Customer;
import it.maramb.adix.entity.User;
import it.maramb.adix.entity.permits.UserPermits;
import it.maramb.adix.entity.permits.UserRoles;
import it.maramb.adix.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends AdixBaseRecordController<User, UserDto, CreateUpdateUserRequest> {

    private UserService userService;

    @Autowired
    protected UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @Override
    @GetMapping
    @Secured({ UserPermits.Constants.ADMIN_VIEW_ALL_USERS })
    public ResponseEntity<FetchRecordResponse> fetch() {
        return doFetch();
    }

    @Override
    @PostMapping
    @Secured({ UserPermits.Constants.ADMIN_ADD_USER })
    public ResponseEntity<Response> add(@RequestBody @Valid CreateUpdateUserRequest request) {
        return doAdd(request);
    }

    @Override
    @PutMapping("/{id}")
    @Secured({ UserPermits.Constants.ADMIN_EDIT_USER })
    public ResponseEntity<Response> update(@RequestBody @Valid CreateUpdateUserRequest request, @PathVariable("id") @NotNull Long id) {
        return doUpdate(request, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @Secured({ UserPermits.Constants.ADMIN_DELETE_USER })
    public ResponseEntity<Response> delete(@PathVariable("id") @NotNull Long id) {
        return doDelete(id);
    }

    @GetMapping("/permits/{id}")
    @Secured({ UserPermits.Constants.ADMIN_EDIT_USER_PERMITS })
    public ResponseEntity<Response> fetchPermits(@PathVariable("id") @NotNull Long id) {
        User loggedUser = getUserFromSecurityContext();

        Response response = userService.fetchPermits(loggedUser, id);
        return ResponseEntity.ok().body(response);
    }
}
