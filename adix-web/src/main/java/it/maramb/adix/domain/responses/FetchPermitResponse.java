package it.maramb.adix.domain.responses;

import it.maramb.adix.base.AdixBaseEntity;
import it.maramb.adix.domain.Response;
import it.maramb.adix.entity.permits.UserPermits;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class FetchPermitResponse extends Response {

    private List<UserPermits> permits;

    public static FetchPermitResponse create(List<UserPermits> permits) {
        FetchPermitResponse response = new FetchPermitResponse();
        response.setError(false);
        response.setPermits(permits);

        return response;
    }
}