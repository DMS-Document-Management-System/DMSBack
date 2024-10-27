package com.si.apirest.dto.Rol;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RolDTO {
    @NotBlank(message = "El nombre del rol no debe estar vacío.")
    private String name;
}
