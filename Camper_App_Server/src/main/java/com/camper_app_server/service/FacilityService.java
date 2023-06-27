package com.camper_app_server.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.camper_app_server.repositories.FacilityRepository;
import com.camper_app_server.repositories.FacilityServiceRepository;
import com.camper_app_server.security.entity.Facility;
import com.camper_app_server.security.entity.FacilityServicesEntity;
import com.camper_app_server.security.exception.MyAPIException;
import com.camper_app_server.security.payload.FacilityDTO;

@Service
public class FacilityService {

	// Repository per trovare i servizi salvati nel db
	@Autowired
	FacilityServiceRepository facilityServiceRepository;
	// repository per il CRUD delle strutture
	@Autowired
	FacilityRepository facilityRepository;

	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(facilityRepository.findAll());
	}

	public ResponseEntity<?> getById(Long facilityId) {
		if (!facilityRepository.existsById(facilityId)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessuna struttura trovata");
		}
		return ResponseEntity.ok(facilityRepository.findById(facilityId).get());
	}

	public ResponseEntity<String> insertFacility(FacilityDTO f) {
		Facility insert = new Facility();
		Set<FacilityServicesEntity> service = new HashSet<>();

		insert.setName(f.getName());
		insert.setDescription(f.getDescription());
		insert.setPhoneNumber(f.getPhoneNumber());
		insert.setFacilityType(f.getType());

//		Qui inserisco solo l'id del servizio assiociato alla tabella service per implementarlo al set di servizi di ogni struttura
		f.getService().forEach(a -> {
			FacilityServicesEntity s = facilityServiceRepository.findById(a).get();
			service.add(s);
		});
		insert.setServiceFacility(service);

		facilityRepository.save(insert);
		return ResponseEntity.ok("Struttura aggiunta correttamente");
	}
	
//	public ResponseEntity<String> updateFacility(Long id,FacilityDTO f){
//		
//		
//	}

}
