package it.maramb.adix.domain;

import it.maramb.adix.base.AdixBaseEntity;
import it.maramb.adix.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class FetchRecordResponse<T extends AdixBaseEntity, Y> extends Response {

    private List<Y> records;

    public static <T extends AdixBaseEntity> FetchRecordResponse create(List<T> records) {
        FetchRecordResponse response = new FetchRecordResponse();
        response.setError(false);
        response.setRecords(
                records.stream().map(AdixBaseEntity::toDto).collect(Collectors.toList())
        );

        return response;
    }
}
