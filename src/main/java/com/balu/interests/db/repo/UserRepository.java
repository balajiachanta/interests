package com.balu.interests.db.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.balu.interests.db.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}