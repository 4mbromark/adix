package it.maramb.adix.domain.requests;

import it.maramb.adix.base.AdixBaseCreateUpdateRequest;
import it.maramb.adix.entity.User;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateUpdateUserRequest extends AdixBaseCreateUpdateRequest<User> {

    @NotNull
    @NotBlank
    private String code;
    @NotNull
    @NotBlank
    @Email
    private String emailAddress;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    private Boolean enabled;
    private String note;

    public User toEntity() {
        User user = new User();
        return updateEntity(user);
    }

    public User updateEntity(User user) {
        user.setCode(code);
        user.setEmailAddress(emailAddress);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(enabled);
        user.setNote(note);

        return user;
    }
}
