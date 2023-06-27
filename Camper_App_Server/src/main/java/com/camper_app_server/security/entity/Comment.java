package com.camper_app_server.security.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long user_id;
	@Column(nullable = false)
	private Long facility_id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String body;
	@Column(nullable = false)
	private LocalDate date;
}
