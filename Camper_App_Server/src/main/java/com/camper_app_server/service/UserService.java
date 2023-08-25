package com.camper_app_server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.camper_app_server.repositories.FacilityRepository;
import com.camper_app_server.security.entity.Facility;
import com.camper_app_server.security.entity.User;
import com.camper_app_server.security.exception.MyAPIException;
import com.camper_app_server.security.payload.UtenteDTO;
import com.camper_app_server.security.repository.UtenteDAO;

import jakarta.persistence.EntityExistsException;

@Service
public class UserService {

	@Autowired UtenteDAO userDAO;
	@Autowired FacilityService facilityService;
	@Autowired
	FacilityRepository facilityRepository;
	
	
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
	//metodo da vedere bene perchè devo aggiornare l'utente
	public String addImageProfile(Long userId,String urlPhotoUser) {
		User u = userDAO.findById(userId).get();
		u.setPhotoProfile(urlPhotoUser);
		userDAO.save(u);
		return "foto aggiunta";
	}
	
	public String addFacilityToPreference(Long id_user,Long idFacility) {
		if(!userDAO.existsById(id_user)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND ,"nessun utente trovato");
		}
		if (!facilityRepository.existsById(idFacility)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessuna struttura trovata");
		}
		Facility f = facilityService.getById(idFacility);
		
		User u = userDAO.findById(id_user).get();
		u.getPreference().add(f);
		userDAO.save(u);
		
		return "struttura aggiunta ai preferiti";
	}
	public String removeFacilityToPreference(Long id_user,Long idFacility) {
		
		if(!userDAO.existsById(id_user)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND ,"nessun utente trovato");
		}
		if (!facilityRepository.existsById(idFacility)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessuna struttura trovata");
		}
		
		Facility f = facilityService.getById(idFacility);
		
		User u = userDAO.findById(id_user).get();
		u.getPreference().remove(f);
		userDAO.save(u);
		
		return "struttura rimossa dai preferiti";
	}
	
	public ResponseEntity<?> updateUser(Long userid,UtenteDTO u){
		if(!userDAO.existsById(userid)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND ,"nessun utente trovato");
		}
		User user = userDAO.findById(userid).get();
		System.out.println(user);
		if(u.getCognome()!= null) {
			user.setCognome(u.getCognome());
		}
		if(u.getPhotoProfile() != null) {
			user.setPhotoProfile(u.getPhotoProfile());
			
		}
		if(u.getEmail() != null) {
		user.setEmail(u.getEmail());
		
		}
		if(u.getNome() != null) {
			user.setNome(u.getNome());
			
		}
		if(u.getUserName() != null) {
			
			user.setUserName(u.getUserName());
		}
		return ResponseEntity.ok(userDAO.save(user));
	}
	
}
