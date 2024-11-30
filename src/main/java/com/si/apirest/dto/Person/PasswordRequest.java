package com.si.apirest.dto.Person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordRequest {
    
    @NotEmpty(message = "El campo usuario no puede ser nulo.")
    @NotNull(message = "El campo usuario no puede ser nulo.")
    @NotBlank(message = "El campo usuario no puede estar vacio.")
    private String username;

    @NotEmpty(message = "El campo contraseña no puede ser nulo.")
    @NotNull(message = "El campo contraseña no puede ser nulo.")
    @NotBlank(message = "El campo contraseña no puede estar vacío.")
    @Size(min = 4, message = "El campo contraseña debe tener como mínimo 4 caracteres.")
    private String oldPassword;
    
    @NotEmpty(message = "El campo contraseña no puede ser nulo.")
    @NotNull(message = "El campo contraseña no puede ser nulo.")
    @NotBlank(message = "El campo contraseña no puede estar vacío.")
    @Size(min = 4, message = "El campo contraseña debe tener como mínimo 4 caracteres.")
    private String newPassword;
}
