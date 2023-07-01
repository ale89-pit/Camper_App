package com.camper_app_server.service;

import java.util.List;
import java.util.Optional;

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
	
	public List<User> getAll(){
		return userDAO.findAll();
	}
	
	public User getById(Long userId){
		if(!userDAO.existsById(userId)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND ,"nessun utente trovato");
		}
		return userDAO.findById(userId).get();
	}
	public Optional<User> getByUsername(String userName) {
		if(!userDAO.existsByUserName(userName)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND ,"nessun utente trovato");
		}
		return userDAO.findByUserName(userName);
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
