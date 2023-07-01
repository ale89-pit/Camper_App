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

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/app")
public class FacilityController {


	@Autowired FacilityService facilityService;
	
	
	@GetMapping
	
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(facilityService.getAll());
	}
	
	@GetMapping("/{facility_id}")
	
	public ResponseEntity<?> getById(@PathVariable Long facility_id){
		return ResponseEntity.ok(facilityService.getById(facility_id));
	}
	
<<<<<<< HEAD
	@GetMapping("/search")
	@PreAuthorize("hasRole('USER')")
//	http://localhost:8080/app/facilities/search?desc=camping&tit=camping
	public ResponseEntity<?> searchFacility(@PathParam (value = "desc") String desc,@PathParam (value = "tit") String tit){
		return ResponseEntity.ok(facilityService.searchFacility(desc, tit));
	}
	@PostMapping
=======
	@PostMapping("/facilities")
>>>>>>> 9f5a6d6674080130015ba82d2ed35a77e6cb0cf7
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> insertFacility(@RequestBody FacilityDTO f){
		return ResponseEntity.ok(facilityService.insertFacility(f));
		
	}
	@PutMapping("/facilities/{facility_id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateFacility(@PathVariable Long facility_id,@RequestBody FacilityDTO f){
		return ResponseEntity.ok(facilityService.updateFacility(facility_id, f));
	}
	@DeleteMapping("/facilities/{facility_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteFacility(@PathVariable Long facility_id){
		facilityService.deleteFacility(facility_id);
		return ResponseEntity.ok("Struttura Eliminata");
	}
}
