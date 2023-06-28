package com.camper_app_server.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.camper_app_server.repositories.CommentRepository;
import com.camper_app_server.repositories.FacilityRepository;
import com.camper_app_server.security.entity.Comment;
import com.camper_app_server.security.entity.Facility;
import com.camper_app_server.security.entity.User;
import com.camper_app_server.security.exception.MyAPIException;
import com.camper_app_server.security.payload.CommentDTO;
import com.camper_app_server.security.repository.UtenteDAO;

@Service
public class CommentService {

	@Autowired CommentRepository commentRepository;
	@Autowired FacilityRepository facilityRepository;
	@Autowired FacilityService facilityService;
	@Autowired UtenteDAO userRepository;
	
	public ResponseEntity<?> getById(Long id){
		if(!commentRepository.existsById(id)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessun commento trovato");
		}
		;
		return ResponseEntity.ok(commentRepository.findById(id));
		}
//	public ResponseEntity<?> insertComments(CommentDTO commentdto){
//		if(!facilityRepository.existsById(commentdto.getFacility_id())) {
//			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessun commento trovato");
//		}
//		if(!userRepository.existsById(commentdto.getUser_id())) {
//			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessun commento trovato");
//		}
//		User u = userRepository.findById(commentdto.getUser_id()).get();
//		Facility f = facilityRepository.findById(commentdto.getFacility_id()).get();
//		Comment c = new Comment();
//		c.setDate(LocalDate.now());
//		c.setBody(commentdto.getBody());
//		c.setTitle(commentdto.getTitle());
//		c.setUser(u);
//		c.setFacility(f);
//		facilityService.addComment(commentdto.getFacility_id(), c);
//		return ResponseEntity.ok("commento aggiunto");
//	}
	
}
