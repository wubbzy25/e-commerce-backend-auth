package com.ecommerce.auth.Repositories;

import com.ecommerce.auth.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
