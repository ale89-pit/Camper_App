package com.camper_app_server.controller;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.camper_app_server.message.ResponseMessage;
import com.camper_app_server.repositories.FileDataRepository;
import com.camper_app_server.security.entity.FileData;
import com.camper_app_server.security.payload.FacilityDTO;
import com.camper_app_server.service.FacilityService;
import com.camper_app_server.service.FacilityServiceEntityService;
import com.camper_app_server.service.FileDataService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/app")
public class FacilityController {


	@Autowired FacilityService facilityService;
	@Autowired FacilityServiceEntityService facSerEntSer;
	@Autowired FileDataService fileDataService;
	@Autowired FileDataRepository fileDataRepository;
	
	@GetMapping
	
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(facilityService.getAll());
	}
	
	//questa rotta serve per il front-end per mostrare i servizi disponibili e quindi assegnarli alla strutture che si crea
	@GetMapping("/service")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getAllService(){
		return ResponseEntity.ok(facSerEntSer.getAll());
	}
	
	@GetMapping("/{facility_id}")
	
	public ResponseEntity<?> getById(@PathVariable Long facility_id){
		return ResponseEntity.ok(facilityService.getById(facility_id));
	}
	

	@GetMapping("/search")
	
//	http://localhost:8080/app/facilities/search?desc=camping&tit=camping
	public ResponseEntity<?> searchFacility(@PathParam (value = "desc") String desc,@PathParam (value = "tit") String tit){
		return ResponseEntity.ok(facilityService.searchFacility(desc, tit));
	}

	@PostMapping("/facilities")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> insertFacility(@RequestBody FacilityDTO f){
		return ResponseEntity.ok(facilityService.insertFacility(f));
		
	}
	
//	Sezione per l'inserimento delle immagini
	
	@PostMapping("/facilities/image")
	@PreAuthorize("hasRole('USER')")
	
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
//	    	salvo la foto in locale 
	      fileDataService.save(file);
	      FileData f = new FileData();
	      
	      
	      f.setNome(file.getOriginalFilename());
//	      recupero la foto in locale con il metodo load() del fileDataLocal 
	      Resource fileTrovato =  fileDataService.load(file.getOriginalFilename());
	      
//	      recuperanto la foto eseguo questa funzione per recuperare il path giusto che si userà poi nel frontEnd
	      String url = MvcUriComponentsBuilder
		          .fromMethodName(FacilityController.class, "getFile", fileTrovato.getFilename().toString()).build().toString();

	      f.setFilePath(url);
	      
	      //salvo il FileData, associato alla foto appena inserita, nel db
	      fileDataRepository.save(f);
	      
	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message + f+file.getOriginalFilename()));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
	      
	      
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
	
	//questo mapping trova tutte le foto che sono state salvate in locale e ne restituisce per ognuna un FileData con nome e url
		@GetMapping("/facilities/image")
		  public ResponseEntity<List<FileData>> getListFiles() {
		    List<FileData> fileInfos = fileDataService.loadAll().map((path) -> {
		      String filename = path.getFileName().toString();
		      String url = MvcUriComponentsBuilder
		          .fromMethodName(FacilityController.class, "getFile", path.getFileName().toString()).build().toString();

		      return new FileData(filename, url);
		    }).collect(Collectors.toList());
		    
		    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
		  }
		
		
		//questo mapping trova la foto specifica nel db e torna l'id, il nome e l'url, cosi da poter essere usato immediatamente dak frontEnd
		//per impostare la cover alla struttura che si sta creando
		  @GetMapping("/facilities/image/{filename:.+}")
		  @ResponseBody
		  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
			  Resource fileTrovato =  fileDataService.load(filename);
		  return ResponseEntity.ok(fileDataService.load(filename)) ;
		  }
		
//fine sezione foto
	
	@PutMapping("/facilities/{facility_id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateFacility(@PathVariable Long facility_id,@RequestBody FacilityDTO f){
		return ResponseEntity.ok(facilityService.updateFacility(facility_id, f));
	}
	@DeleteMapping("/facilities/{facility_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteFacility(@PathVariable Long facility_id){
		facilityService.deleteFacility(facility_id);
		return ResponseEntity.ok("Struttura Eliminata");
	}
}
