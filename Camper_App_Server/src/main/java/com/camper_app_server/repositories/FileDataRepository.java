package com.camper_app_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camper_app_server.security.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

	
	public FileData findByNome(String nome);
}
