package com.camper_app_server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camper_app_server.security.entity.FileData;

public interface FileDataReposrtitory extends JpaRepository<FileData,Long> {

	Optional<FileData> findByNome(String fileName);

}
