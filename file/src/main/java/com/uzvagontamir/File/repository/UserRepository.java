package com.uzvagontamir.File.repository;

import com.uzvagontamir.File.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
