package it.maramb.adix.domain;

import lombok.Getter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
public class LoginRequest {

    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;
}
