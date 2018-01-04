package com.balu.interests.db.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.balu.interests.db.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}