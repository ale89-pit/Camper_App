package com.camper_app_server.security.payload;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	private Long user_id;
	
	private Long facility_id;
	
	private String title;
	
	private String body;
	
}
