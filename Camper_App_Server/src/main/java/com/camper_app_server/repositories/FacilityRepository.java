package com.camper_app_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camper_app_server.security.entity.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

}
