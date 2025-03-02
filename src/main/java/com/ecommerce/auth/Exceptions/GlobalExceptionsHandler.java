package com.ecommerce.auth.Exceptions;

import com.ecommerce.auth.DTO.ExceptionResponseDTO;
import com.ecommerce.auth.Utils.DateFormatted;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    private final DateFormatted dateFormat;
    @Autowired
    public GlobalExceptionsHandler(DateFormatted dateFormat) {
        this.dateFormat = dateFormat;
    }


    @ExceptionHandler(BadsCredentialsException.class)
     public ResponseEntity<ExceptionResponseDTO> handleBadsCredentialsException(BadsCredentialsException e){
     return new ResponseEntity<>(getExceptionResponseDTO("P-400", e.getMessage(), "BadCredentials"), HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(EmailDontExistsException.class)
     public ResponseEntity<ExceptionResponseDTO> handleEmailDontExistsException(EmailDontExistsException e){
        return new ResponseEntity<>(getExceptionResponseDTO("P-400", e.getMessage(), "EmailDontExists"), HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(UserAlreadyExistsException.class)
     public ResponseEntity<ExceptionResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        return new ResponseEntity<>(getExceptionResponseDTO("P-400", e.getMessage(), "UserAlreadyExists"), HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(ConstraintViolationException.class)
     public ResponseEntity<ExceptionResponseDTO> handleConstraintViolationException(ConstraintViolationException e){
        return new ResponseEntity<>(getExceptionResponseDTO("P-400", e.getMessage(), "ConstraintViolation"), HttpStatus.BAD_REQUEST);
     }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponseDTO> handleExpiredJwtException(ExpiredJwtException e) {
        return new ResponseEntity<>(getExceptionResponseDTO("P-401", e.getMessage(), "TokenExpired"), HttpStatus.UNAUTHORIZED); }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUnsupportedJwtException(UnsupportedJwtException e) {
        return new ResponseEntity<>(getExceptionResponseDTO("P-400", e.getMessage(), "TokenUnsupported"), HttpStatus.BAD_REQUEST);
    }




     public ExceptionResponseDTO  getExceptionResponseDTO(String code, String message, String Exception){

        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO();
        exceptionResponseDTO.setTimeStamp(dateFormat.GetDate());
        exceptionResponseDTO.setCode(code);
        exceptionResponseDTO.setMessage(message);
        exceptionResponseDTO.setException(Exception);


        return exceptionResponseDTO;
     }

}
