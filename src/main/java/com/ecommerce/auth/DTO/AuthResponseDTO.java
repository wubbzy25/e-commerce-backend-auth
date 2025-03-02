package com.ecommerce.auth.DTO;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String timeStamp;
    private String code;
    private String message;
    private String Token;
    private Long id_user;
    private String uri;
}
