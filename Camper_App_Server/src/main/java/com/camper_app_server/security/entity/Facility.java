package com.camper_app_server.security.entity;

import java.util.List;
import java.util.Set;

import com.camper_app_server.enumerated.FacilityType;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
@Getter
@Setter
@Table(name="facilities")
@NoArgsConstructor
@AllArgsConstructor
public class Facility {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(unique = true, nullable = false)
	private String phoneNumber;
	
	@Column(name="url")
	private String cover;
	
	@Column(name ="website")
	private String officialSite;
	@Enumerated(EnumType.STRING)
	private FacilityType facilityType;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<FacilityServicesEntity> serviceFacility;
	
	@OneToMany(fetch = FetchType.EAGER,targetEntity = Comment.class)
	private List<Comment> comment;
}
