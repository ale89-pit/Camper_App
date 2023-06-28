package com.camper_app_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camper_app_server.security.payload.FacilityDTO;
import com.camper_app_server.service.FacilityService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/app/facilities")
public class FacilityController {


	@Autowired FacilityService facilityService;
	
	
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(facilityService.getAll());
	}
	
	@GetMapping("/{facility_id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getById(@PathVariable Long facility_id){
		return ResponseEntity.ok(facilityService.getById(facility_id));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> insertFacility(@RequestBody FacilityDTO f){
		return ResponseEntity.ok(facilityService.insertFacility(f));
		
	}
	@PutMapping("/{facility_id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateFacility(@PathVariable Long facility_id,@RequestBody FacilityDTO f){
		return ResponseEntity.ok(facilityService.updateFacility(facility_id, f));
	}
	@DeleteMapping("/{facility_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteFacility(@PathVariable Long facility_id){
		facilityService.deleteFacility(facility_id);
		return ResponseEntity.ok("Struttura Eliminata");
	}
}
