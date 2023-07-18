package com.camper_app_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.camper_app_server.security.entity.Facility;
import com.camper_app_server.enumerated.FacilityType;


@Repository
//interfaccia per aggiungere l'Entita Facility al database
public interface FacilityRepository extends JpaRepository<Facility, Long> {


	public List<Facility> findByDescriptionIgnoreCaseContainingOrNameIgnoreCaseContaining(String desc,String tit);
	public List<Facility> findByFacilityType(FacilityType facilityType);
	
//	select * from facilities where address_address_id in ( select address_id from addresses where comune_id = (select id from comuni where name='Barga'));

	@Query("SELECT f FROM Facility f WHERE f.address.address_id IN"
				+ "(SELECT a.address_id FROM Address a WHERE a.comune.id ="
				+ "(SELECT c.id FROM Comune c where c.name= :name))")
	public List<Facility> searchFacilityByComuneName(String name);
	


}
