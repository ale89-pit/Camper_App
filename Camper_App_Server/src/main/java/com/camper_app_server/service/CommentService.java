package com.camper_app_server.service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	@Autowired
	CommentRepository commentRepository;
	@Autowired
	FacilityRepository facilityRepository;
	@Autowired
	FacilityService facilityService;
	@Autowired
	UtenteDAO userRepository;
//	@Autowired
//	CommentPageableRepository commentPageableRepository;

	public ResponseEntity<?> getById(Long id) {
		if (!commentRepository.existsById(id)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessun commento trovato");
		}
		;
		return ResponseEntity.ok(commentRepository.findById(id));
	}
	
	public ResponseEntity<?>getAll(){
		return ResponseEntity.ok(commentRepository.findAll());
	}

	public ResponseEntity<?> insertComments(CommentDTO commentdto) {
		if (!facilityRepository.existsById(commentdto.getFacility_id())) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessun commento trovato");
		}
		if (!userRepository.existsById(commentdto.getUser_id())) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessun commento trovato");
		}
		User u = userRepository.findById(commentdto.getUser_id()).get();
		Facility f = facilityRepository.findById(commentdto.getFacility_id()).get();
		Comment c = new Comment();
		c.setDate(LocalDate.now());
		c.setBody(commentdto.getBody());
		c.setTitle(commentdto.getTitle());
		c.setUser(u);
		c.setFacility(f);
		Comment save = commentRepository.save(c);
//		facilityService.addComment(commentdto.getFacility_id(), save);
		return ResponseEntity.ok("commento aggiunto");
	}
	
	
	public List<Comment> getCommentByFacility(Facility f){
		
		return commentRepository.findByFacility(f);
		
	
	
	}
	
	
	public String deleteComment(Long id) {
		if (!commentRepository.existsById(id)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessun commento trovato");
		}
		commentRepository.deleteById(id);
		
		return "commento eliminato";
	}
}
