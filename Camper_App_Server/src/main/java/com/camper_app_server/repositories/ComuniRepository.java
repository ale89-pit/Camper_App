package com.camper_app_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camper_app_server.security.entity.Comune;
import com.camper_app_server.security.entity.Province;

import java.util.List;


@Repository
public interface ComuniRepository extends JpaRepository<Comune, Long> {

	List<Comune> findByProvincename(Province provincename);
}
