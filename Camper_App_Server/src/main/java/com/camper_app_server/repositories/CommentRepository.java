package com.camper_app_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camper_app_server.security.entity.Comment;
import com.camper_app_server.security.entity.Facility;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
		
	List<Comment> findByFacility(Facility facility);
}
