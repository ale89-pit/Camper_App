package com.camper_app_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.camper_app_server.security.entity.Facility;
import com.camper_app_server.enumerated.FacilityType;


@Repository
//interfaccia per aggiungere l'Entita Facility al database
public interface FacilityRepository extends JpaRepository<Facility, Long> {


	public List<Facility> findByDescriptionIgnoreCaseContainingOrNameIgnoreCaseContaining(String desc,String tit);
	public List<Facility> findByFacilityType(FacilityType facilityType);
	
	
}
