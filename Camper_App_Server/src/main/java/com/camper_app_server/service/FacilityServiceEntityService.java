package com.camper_app_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camper_app_server.repositories.FacilityServiceRepository;
import com.camper_app_server.security.entity.FacilityServicesEntity;

@Service
public class FacilityServiceEntityService {

	
	@Autowired FacilityServiceRepository facilityServiceRepository;

 public	List<FacilityServicesEntity> getAll(){
		return facilityServiceRepository.findAll();
	}
}
