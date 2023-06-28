//package com.camper_app_server.repositories;
//
//import java.awt.print.Pageable;
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.repository.PagingAndSortingRepository;
//
//import com.camper_app_server.security.entity.Comment;
//import com.camper_app_server.security.entity.Facility;
//
//public interface CommentPageableRepository extends PagingAndSortingRepository<Comment, Long> {
//	
//	Page<Comment> findByFacility(Facility facility,Pageable pageable);
//}
