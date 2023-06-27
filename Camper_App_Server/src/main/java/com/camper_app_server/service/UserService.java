package com.camper_app_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.camper_app_server.security.entity.User;
import com.camper_app_server.security.exception.MyAPIException;
import com.camper_app_server.security.payload.UtenteDTO;
import com.camper_app_server.security.repository.UtenteDAO;

import jakarta.persistence.EntityExistsException;

@Service
public class UserService {

	@Autowired UtenteDAO userDAO;
	
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(userDAO.findAll());
	}
	
	public ResponseEntity<?> getById(Long userId){
		if(!userDAO.existsById(userId)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND ,"nessun utente trovato");
		}
		return ResponseEntity.ok(userDAO.findById(userId).get());
	}
	
	public ResponseEntity<?> updateUser(Long userid,UtenteDTO u){
		if(!userDAO.existsById(userid)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND ,"nessun utente trovato");
		}
		User user = userDAO.findById(userid).get();
		System.out.println(user);
		System.err.println(u.getCognome());
		user.setCognome(u.getCognome());
		user.setEmail(u.getEmail());
		user.setNome(u.getNome());
		user.setUserName(u.getUserName());
		return ResponseEntity.ok(userDAO.save(user));
	}
	
}
