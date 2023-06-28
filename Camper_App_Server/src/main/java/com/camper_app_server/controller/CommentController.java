package com.camper_app_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camper_app_server.security.payload.CommentDTO;
import com.camper_app_server.service.CommentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/app/comments")
public class CommentController {

	
	@Autowired CommentService commentService;
	
	
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
	
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createComment(@RequestBody CommentDTO c){
		return ResponseEntity.ok(commentService.insertComments(c));
	}
	
}
