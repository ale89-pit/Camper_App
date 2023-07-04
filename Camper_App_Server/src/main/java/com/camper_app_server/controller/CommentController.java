package com.camper_app_server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camper_app_server.security.entity.Comment;
import com.camper_app_server.security.entity.Facility;
import com.camper_app_server.security.payload.CommentDTO;
import com.camper_app_server.service.CommentService;
import com.camper_app_server.service.FacilityService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/app/comments")
public class CommentController {

	
	@Autowired CommentService commentService;
	@Autowired FacilityService facilityService;
	
	
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> allComments(){
		return ResponseEntity.ok(commentService.getAll());
	}
	
	
	@GetMapping("/{comment_id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> commentById(@PathVariable Long comment_id){
		return ResponseEntity.ok(commentService.getById(comment_id));
	}
	
	@GetMapping("/allBy" )
//	http://localhost:8080/app/comments/allBy?facility_id=1
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> commetByFacility(@PathParam(value = "facility_id") Long facility_id){
	 Facility f = facilityService.getById(facility_id);
	
	 List<Comment> list = commentService.getCommentByFacility(f);
		
		return ResponseEntity.ok(list);
	}
	
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createComment(@RequestBody CommentDTO c){
		return ResponseEntity.ok(commentService.insertComments(c));
	}
	
	@DeleteMapping("/{id}") 
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> deleteComment(@PathVariable Long id){
		return ResponseEntity.ok(commentService.deleteComment(id));
	}
}
