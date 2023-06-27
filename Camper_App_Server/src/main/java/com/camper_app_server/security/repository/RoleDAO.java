package com.camper_app_server.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camper_app_server.enumerated.ERole;
import com.camper_app_server.security.entity.Role;



public interface RoleDAO extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(ERole roleName);
}
