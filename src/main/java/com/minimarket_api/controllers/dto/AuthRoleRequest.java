package com.minimarket_api.controllers.dto;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthRoleRequest(
        @Size(max = 2, message = "El usuario no puede tener más de dos roles.") List<String> roleList) {
}
