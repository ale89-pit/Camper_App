package com.camper_app_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camper_app_server.security.entity.Facility;


//interfaccia per aggiungere l'Entita Facility al database
public interface FacilityRepository extends JpaRepository<Facility, Long> {

	public List<Facility> findByDescriptionContainingOrNameContaining(String desc,String title);
}
