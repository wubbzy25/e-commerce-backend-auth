package com.ecommerce.auth.Controllers;

import com.ecommerce.auth.DTO.LoginRequestDTO;
import com.ecommerce.auth.DTO.RegisterRequestDTO;
import com.ecommerce.auth.Services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
       return new ResponseEntity<>(authService.login(loginRequestDTO), HttpStatus.OK);
    }

    @PostMapping("register")
    public  ResponseEntity<?> register(@Valid @RequestBody  RegisterRequestDTO registerRequestDTO) {
        return new ResponseEntity<>(authService.register(registerRequestDTO), HttpStatus.OK);
    }

    @GetMapping("validate/{token}")
    public Boolean validateToken(@PathVariable String token) {
        return authService.validate(token);
    }
}
