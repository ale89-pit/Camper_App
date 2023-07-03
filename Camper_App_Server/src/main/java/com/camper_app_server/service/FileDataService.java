package com.camper_app_server.service;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.camper_app_server.repositories.FileDataReposrtitory;
import com.camper_app_server.security.entity.FileData;

import io.jsonwebtoken.io.IOException;

@Service
public class FileDataService {

	
	@Autowired private FileDataReposrtitory fileDataRepository;
	private final String FOLDER_PATH = "C:/Users/Aless/Desktop/Camper_App/Camper_App_Server/src/main/resources/imageFacility/";
	
	public String uploadImageFromFileSystem(MultipartFile file) throws IOException, IllegalStateException, java.io.IOException{
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		
		FileData fileData = fileDataRepository.save(FileData.builder()
							.nome(file.getOriginalFilename())
							.type(file.getContentType())
							.filePath(filePath).build());
		file.transferTo(new File(filePath));
			if(fileData != null) {
				return filePath;
			}
		return null;
	}
	
	public String downloadImageFromFileSystem(String fileName) throws java.io.IOException {
		Optional<FileData>  fileData = fileDataRepository.findByNome(fileName);
		 String filePath = fileData.get().getFilePath();
		 
		byte [] image = Files.readAllBytes(new File(filePath).toPath());
		String encodeImage = Base64.getEncoder().encodeToString(image);
		return encodeImage;
	}
}
