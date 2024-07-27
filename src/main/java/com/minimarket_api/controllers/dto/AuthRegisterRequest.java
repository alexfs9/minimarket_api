package com.minimarket_api.controllers.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthRegisterRequest(@NotBlank String username,
                                  @NotBlank String firstNames,
                                  @NotBlank String lastNames,
                                  @NotBlank String phone,
                                  @NotBlank String email,
                                  @NotBlank String password,
                                  @Valid AuthRoleRequest roleRequest) {
}
