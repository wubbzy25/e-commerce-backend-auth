package com.ecommerce.auth.Utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class HashPassword {
    public String hashPassword(String password) {
        // Implement your hashing logic here.
        // For example, you can use BCryptPasswordEncoder from Spring Security
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String hashedPassword, String plainTextPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
