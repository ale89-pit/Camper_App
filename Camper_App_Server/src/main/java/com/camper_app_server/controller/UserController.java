package com.camper_app_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.camper_app_server.message.ResponseMessage;
import com.camper_app_server.repositories.FileDataRepository;
import com.camper_app_server.security.entity.FileData;
import com.camper_app_server.security.payload.UtenteDTO;
import com.camper_app_server.service.FileDataService;
import com.camper_app_server.service.UserService;

import jakarta.websocket.server.PathParam;




@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/app/users")
public class UserController {
	
	@Autowired UserService userService;

@Autowired FileDataService fileDataService;
@Autowired FileDataRepository fileDataRepository;



	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(userService.getAll());
	}
	@GetMapping("/search")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getByUserName(@PathParam(value="userName") String userName){
		return ResponseEntity.ok(userService.getByUsername(userName));
	}
	
	@GetMapping("/{user_id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getById(@PathVariable Long user_id){
		return ResponseEntity.ok(userService.getById(user_id));
	}
	@PutMapping("/{user_id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateUser(@PathVariable Long user_id,@RequestBody UtenteDTO u){
		return ResponseEntity.ok(userService.updateUser(user_id, u));
	}
	
	@PostMapping("/{user_id}/image")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addPhotoProfile(@PathVariable Long user_id,@RequestParam("image") MultipartFile image){
		  String message = "";
		    try {
//		    	salvo la foto in locale 
		      fileDataService.save(image);
		      FileData f = new FileData();
		      
		      
		      f.setNome(image.getOriginalFilename());
//		      recupero la foto in locale con il metodo load() del fileDataLocal 
		      Resource fileTrovato =  fileDataService.load(image.getOriginalFilename());
		      
//		      recuperanto la foto eseguo questa funzione per recuperare il path giusto che si userà poi nel frontEnd
		      String url = MvcUriComponentsBuilder
			          .fromMethodName(FacilityController.class, "getFile", fileTrovato.getFilename().toString()).build().toString();

		      f.setFilePath(url);
		      
		      //salvo il FileData, associato alla foto appena inserita, nel db
		      fileDataRepository.save(f);
		      userService.addImageProfile(user_id, url);
		      
		      message = "Uploaded the file successfully: " + image.getOriginalFilename();
		      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message + f+image.getOriginalFilename()));
		    } catch (Exception e) {
		      message = "Could not upload the file: " + image.getOriginalFilename() + ". Error: " + e.getMessage();
		      
		      
		      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		    }
	}
	
}
