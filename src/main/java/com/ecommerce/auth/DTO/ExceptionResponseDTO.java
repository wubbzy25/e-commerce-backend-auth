package com.ecommerce.auth.DTO;

import lombok.Data;

@Data
public class ExceptionResponseDTO {
    private String timeStamp;
    private String code;
    private String Exception;
    private String message;
}
