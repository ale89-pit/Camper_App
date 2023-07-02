package com.camper_app_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camper_app_server.security.entity.Province;
import java.util.List;


public interface ProvinceRepository extends JpaRepository<Province, String> {
	
	
	public Province findByName(String name);
	}
