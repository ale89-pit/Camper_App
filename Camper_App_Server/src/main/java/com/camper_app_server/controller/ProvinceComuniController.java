package com.camper_app_server.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camper_app_server.repositories.ComuniRepository;
import com.camper_app_server.repositories.ProvinceRepository;
import com.camper_app_server.security.entity.Province;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/app")
public class ProvinceComuniController {

	@Autowired ProvinceRepository provinceRepository;
	@Autowired ComuniRepository comuniRepository;
	
	@GetMapping("/province")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(provinceRepository.findAll());
	}
	
	@GetMapping("/comuni")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getComuniByProvince(@RequestParam (value="sign") String provinceSign){
		Province p = provinceRepository.findById(provinceSign).get();
				return ResponseEntity.ok(comuniRepository.findByProvincename(p));
	}
}
