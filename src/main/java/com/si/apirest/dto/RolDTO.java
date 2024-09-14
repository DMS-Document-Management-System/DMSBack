package com.si.apirest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RolDTO {
    @NotBlank(message = "No llega esta cosa")
    private String name;
}
