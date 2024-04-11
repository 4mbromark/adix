package it.maramb.adix.domain.requests;

import it.maramb.adix.base.AdixBaseCreateUpdateRequest;
import it.maramb.adix.domain.validators.EnumValidator;
import it.maramb.adix.entity.Customer;
import it.maramb.adix.entity.types.CustomerAccountingType;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateUpdateCustomerRequest extends AdixBaseCreateUpdateRequest<Customer> {

    @NotNull
    @NotBlank
    private String code;
    @NotNull
    @NotBlank
    private String businessName;
    @NotNull
    @NotBlank
    private String vatNumber;
    @EnumValidator(enumClass = CustomerAccountingType.class)
    private String accountingType;
    private Boolean delegation;
    private Boolean enabled;
    private String note;

    public Customer toEntity() {
        Customer customer = new Customer();
        return updateEntity(customer);
    }

    public Customer updateEntity(Customer customer) {
        customer.setCode(code);
        customer.setBusinessName(businessName);
        customer.setVatNumber(vatNumber);
        customer.setAccountingType(CustomerAccountingType.valueOf(accountingType));
        customer.setDelegation(delegation);
        customer.setEnabled(enabled);
        customer.setNote(note);

        return customer;
    }
}
