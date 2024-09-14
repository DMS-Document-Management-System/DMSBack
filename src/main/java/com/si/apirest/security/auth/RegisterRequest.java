package com.si.apirest.security.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "El campo usuario no puede ser nulo.")
    @NotNull(message = "El campo usuario no puede ser nulo.")
    @NotBlank(message = "El campo usuario no puede estar vacio.")
    private String username;

    @NotEmpty(message = "El campo contraseña no puede ser nulo.")
    @NotNull(message = "El campo contraseña no puede ser nulo.")
    @NotBlank(message = "El campo contraseña no puede estar vacío.")
    @Size(min = 4, message = "El campo contraseña debe tener como mínimo 4 caracteres.")
    private String password;

}
