package com.ecommerce.auth.Services;

import com.ecommerce.auth.DTO.AuthResponseDTO;
import com.ecommerce.auth.DTO.LoginRequestDTO;
import com.ecommerce.auth.DTO.RegisterRequestDTO;
import com.ecommerce.auth.DTO.ValidateResponseDTO;
import com.ecommerce.auth.Exceptions.BadsCredentialsException;
import com.ecommerce.auth.Exceptions.EmailDontExistsException;
import com.ecommerce.auth.Exceptions.UserAlreadyExistsException;
import com.ecommerce.auth.Models.Users;
import com.ecommerce.auth.Repositories.AuthRepository;
import com.ecommerce.auth.Utils.DateFormatted;
import com.ecommerce.auth.Utils.HashPassword;
import com.ecommerce.auth.Utils.JwtUtils;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class    AuthService {

    private final AuthRepository authRepository;
    private final DateFormatted dateFormat;
    private final JwtUtils JwtUtil;
    private final HashPassword hash;
    @Autowired
    public AuthService(AuthRepository authRepository,
                       DateFormatted dateFormat,
                       JwtUtils JwtUtil,
                       HashPassword hash) {
        this.authRepository = authRepository;
        this.dateFormat = dateFormat;
        this.JwtUtil = JwtUtil;
        this.hash = hash;
    }

    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Optional<Users> userOptional = authRepository.findByEmail(loginRequestDTO.getEmail());
        if(userOptional.isEmpty()) {
          throw new EmailDontExistsException();
        }
        Users user = userOptional.get();
        boolean PasswordValid = hash.checkPassword(user.getPassword(), loginRequestDTO.getPassword());

        if (!PasswordValid) {
            throw new BadsCredentialsException();
        }

        String token = JwtUtil.GenerateToken(loginRequestDTO.getEmail());
        AuthResponseDTO loginResponseDTO = new AuthResponseDTO();
        loginResponseDTO.setTimeStamp(dateFormat.GetDate());
        loginResponseDTO.setCode("P-200");
        loginResponseDTO.setMessage("Login Successful");
        loginResponseDTO.setId_user(user.getId_user());
        loginResponseDTO.setToken(token);
        loginResponseDTO.setUri("api/v1/auth/login");
        return loginResponseDTO;
    }

    public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        Optional<Users> userOptional = authRepository.findByEmail(registerRequestDTO.getEmail());
        if (userOptional.isPresent()) {
         throw new UserAlreadyExistsException();
        }
        if(!registerRequestDTO.getPassword().equals(registerRequestDTO.getConfirmPassword())) {
            throw new BadsCredentialsException();
        }

        String passwordHashed = hash.hashPassword(registerRequestDTO.getPassword());
        Users user = new Users();
        user.setName(registerRequestDTO.getFirst_name() + " " + registerRequestDTO.getLast_name());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPhone(registerRequestDTO.getPhone());
        user.setPassword(passwordHashed);
        Users userSaved = authRepository.save(user);
        String token = JwtUtil.GenerateToken(registerRequestDTO.getEmail());
        AuthResponseDTO registerResponseDTO = new AuthResponseDTO();
        registerResponseDTO.setTimeStamp(dateFormat.GetDate());
        registerResponseDTO.setCode("P-200");
        registerResponseDTO.setMessage("Registration Successful");
        registerResponseDTO.setToken(token);
        registerResponseDTO.setId_user(userSaved.getId_user());
        registerResponseDTO.setUri("api/v1/auth/registers");
        return registerResponseDTO;
    }

    public Boolean validate(String token){
       if (JwtUtil.validateToken(token)){
           return true;
       } else{
          return false;
       }
    }
}
