package com.camper_app_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camper_app_server.security.entity.FacilityServicesEntity;


@Repository
//interfaccia per aggiungere serviziStruttura al database cosi che possano avere un loro id univico
public interface FacilityServiceRepository extends JpaRepository<FacilityServicesEntity, Long> {

}
