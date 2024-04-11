package it.maramb.adix.domain.requests;

import it.maramb.adix.base.AdixBaseCreateUpdateRequest;
import it.maramb.adix.entity.Fulfillment;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateUpdateFulfillmentRequest extends AdixBaseCreateUpdateRequest<Fulfillment> {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @Positive
    @Min(1)
    @Max(12)
    private Integer monthRecurrence;
    private Boolean enabled;
    private String note;

    public Fulfillment toEntity() {
        Fulfillment fulfillment = new Fulfillment();
        return updateEntity(fulfillment);
    }

    public Fulfillment updateEntity(Fulfillment fulfillment) {
        fulfillment.setName(name);
        fulfillment.setDescription(description);
        fulfillment.setMonthRecurrence(monthRecurrence);
        fulfillment.setEnabled(enabled);
        fulfillment.setNote(note);

        return fulfillment;
    }
}
