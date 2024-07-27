package com.minimarket_api.controllers;

import com.minimarket_api.controllers.dto.AuthRegisterRequest;
import com.minimarket_api.controllers.dto.AuthLoginRequest;
import com.minimarket_api.controllers.dto.AuthResponse;
import com.minimarket_api.services.implementations.AccountDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    private AccountDetailServiceImpl accountDetailServiceImpl;

    @PostMapping("/register")
    public ResponseEntity</*AuthResponse*/?> register(@RequestBody @Valid AuthRegisterRequest authRegisterRequest) {
        //return new ResponseEntity<>(this.accountDetailService.register(authRegisterRequest), HttpStatus.OK);
        return this.accountDetailServiceImpl.register(authRegisterRequest);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest authLoginRequest) {
        return new ResponseEntity<>(this.accountDetailServiceImpl.login(authLoginRequest), HttpStatus.OK);
    }
}
