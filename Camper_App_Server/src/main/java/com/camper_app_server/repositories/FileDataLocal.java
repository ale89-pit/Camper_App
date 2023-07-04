package com.camper_app_server.repositories;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;

import org.springframework.web.multipart.MultipartFile;

import com.camper_app_server.security.entity.FileData;



public interface FileDataLocal  {

	  public void init();

	  public void save(MultipartFile file);

	  public Resource load(String filename);

	  public void deleteAll();

	  public Stream<Path> loadAll();
}
