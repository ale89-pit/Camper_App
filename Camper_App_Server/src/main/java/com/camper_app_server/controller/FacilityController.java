package com.camper_app_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.camper_app_server.security.payload.FacilityDTO;
import com.camper_app_server.service.FacilityService;
import com.camper_app_server.service.FacilityServiceEntityService;
import com.camper_app_server.service.FileDataService;

import io.jsonwebtoken.io.IOException;
import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/app")
public class FacilityController {


	@Autowired FacilityService facilityService;
	@Autowired FacilityServiceEntityService facSerEntSer;
	@Autowired FileDataService fileDataService;
	
	@GetMapping
	
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(facilityService.getAll());
	}
	
	//questa rotta serve per il front-end per mostrare i servizi disponibili e quindi assegnarli alla strutture che si crea
	@GetMapping("/service")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> gettAllService(){
		return ResponseEntity.ok(facSerEntSer.getAll());
	}
	
	@GetMapping("/{facility_id}")
	
	public ResponseEntity<?> getById(@PathVariable Long facility_id){
		return ResponseEntity.ok(facilityService.getById(facility_id));
	}
	

	@GetMapping("/search")
	@PreAuthorize("hasRole('USER')")
//	http://localhost:8080/app/facilities/search?desc=camping&tit=camping
	public ResponseEntity<?> searchFacility(@PathParam (value = "desc") String desc,@PathParam (value = "tit") String tit){
		return ResponseEntity.ok(facilityService.searchFacility(desc, tit));
	}

	@PostMapping("/facilities")

	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> insertFacility(@RequestBody FacilityDTO f){
		return ResponseEntity.ok(facilityService.insertFacility(f));
		
	}
	
	@PostMapping("/facilities/image")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image")MultipartFile file) throws IOException, IllegalStateException, java.io.IOException{
		String uploadImage = fileDataService.uploadImageFromFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}
	@GetMapping("/facilities/image/{fileName}")
//	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws java.io.IOException{
		String imageData = fileDataService.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/jpg"))
				.contentType(MediaType.valueOf("image/jpeg"))
				.contentType(MediaType.valueOf("image/png"))
				.contentType(MediaType.valueOf("image/bpm"))
				.body(imageData);
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
