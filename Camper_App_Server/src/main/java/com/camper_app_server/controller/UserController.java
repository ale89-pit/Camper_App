package com.camper_app_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camper_app_server.security.payload.UtenteDTO;
import com.camper_app_server.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/app/users")
public class UserController {
	
	@Autowired UserService userService;

	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(userService.getAll());
	}
	
	@GetMapping("/{user_id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getById(@PathVariable Long user_id){
		return ResponseEntity.ok(userService.getById(user_id));
	}
	@PutMapping("/{user_id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateUser(@PathVariable Long user_id,@RequestBody UtenteDTO u){
		return ResponseEntity.ok(userService.updateUser(user_id, u));
	}
}
