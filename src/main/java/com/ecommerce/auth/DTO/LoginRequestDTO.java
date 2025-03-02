package com.ecommerce.auth.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotEmpty(message = "Email can't be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|yahoo\\.com|outlook\\.com|hotmail\\.com|icloud\\.com)$", message = "Email must be a valid gmail, yahoo, outlook, hotmail, or icloud email")
    private String email;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 8, max = 24, message = "Password must be between 8 and 24 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d{2,})(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,24}$", message = "Password must have at least one uppercase letter, two numbers, and one special character")
    private String password;
}
