package it.maramb.adix.base;

import it.maramb.adix.domain.FetchRecordResponse;
import it.maramb.adix.domain.Response;
import it.maramb.adix.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

public abstract class AdixBaseRecordController<E extends AdixBaseEntity<D>, D, R extends AdixBaseCreateUpdateRequest<E>> {

    private AdixBaseRecordService<E, D, R> service;

    protected AdixBaseRecordController(AdixBaseRecordService<E, D, R> service) {
        this.service = service;
    }

    protected User getUserFromSecurityContext() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public abstract ResponseEntity<FetchRecordResponse> fetch();

    protected ResponseEntity<FetchRecordResponse> doFetch() {
        User loggedUser = getUserFromSecurityContext();

        FetchRecordResponse<E, D> response = service.fetch(loggedUser);
        return ResponseEntity.ok().body(response);
    }

    public abstract ResponseEntity<Response> add(@RequestBody @Valid R request);

    protected ResponseEntity<Response> doAdd(R request) {
        User loggedUser = getUserFromSecurityContext();

        Response response = service.create(loggedUser, request);
        return ResponseEntity.ok().body(response);
    }

    public abstract ResponseEntity<Response> update(@RequestBody @Valid R request, @PathVariable("id") @NotNull Long id);

    protected ResponseEntity<Response> doUpdate(R request, Long id) {
        User loggedUser = getUserFromSecurityContext();

        Response response = service.update(loggedUser, request, id);
        return ResponseEntity.ok().body(response);
    }

    public abstract ResponseEntity<Response> delete(@PathVariable("id") @NotNull Long id);

    protected ResponseEntity<Response> doDelete(Long id) {
        User loggedUser = getUserFromSecurityContext();

        Response response = service.delete(loggedUser, id);
        return ResponseEntity.ok().body(response);
    }
}
