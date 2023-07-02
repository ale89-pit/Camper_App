package com.camper_app_server.security.payload;

import java.util.List;
import java.util.Set;

import com.camper_app_server.enumerated.FacilityType;

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
public class FacilityDTO {
	
	private String cover;
	private String name;
	private String description;
	private String phoneNumber;
	private String officialSite;
	private Set<Long> service;
	private String facilityType;
	private AddressDTO address;
	

}
