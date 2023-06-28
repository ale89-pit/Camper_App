package com.camper_app_server.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.camper_app_server.repositories.FacilityRepository;
import com.camper_app_server.repositories.FacilityServiceRepository;
import com.camper_app_server.security.entity.Comment;
import com.camper_app_server.security.entity.Facility;
import com.camper_app_server.security.entity.FacilityServicesEntity;
import com.camper_app_server.security.exception.MyAPIException;
import com.camper_app_server.security.payload.FacilityDTO;

@Service
public class FacilityService {

	// Repository per trovare i servizi salvati nel db
	@Autowired
	FacilityServiceRepository facilityServiceRepository;
	// repository per il CRUD delle strutture
	@Autowired
	FacilityRepository facilityRepository;

	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(facilityRepository.findAll());
	}

	public ResponseEntity<?> getById(Long facilityId) {
		if (!facilityRepository.existsById(facilityId)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessuna struttura trovata");
		}
		return ResponseEntity.ok(facilityRepository.findById(facilityId).get());
	}

	public ResponseEntity<String> insertFacility(FacilityDTO f) {
		Facility insert = new Facility();
		Set<FacilityServicesEntity> service = new HashSet<>();

		insert.setName(f.getName());
		insert.setDescription(f.getDescription());
		insert.setPhoneNumber(f.getPhoneNumber());
		insert.setFacilityType(f.getType());
		insert.setOfficialSite(f.getWebSite());

//		Qui inserisco solo l'id del servizio assiociato alla tabella service per implementarlo al set di servizi di ogni struttura
		f.getService().forEach(a -> {
			FacilityServicesEntity s = facilityServiceRepository.findById(a).get();
			service.add(s);
		});
		insert.setServiceFacility(service);

		facilityRepository.save(insert);
		return ResponseEntity.ok("Struttura aggiunta correttamente");
	}

	public ResponseEntity<String> updateFacility(Long id, FacilityDTO f) {
		if (!facilityRepository.existsById(id)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessuna struttura trovata");
		}

		Facility old = facilityRepository.findById(id).get();

		old.setName(f.getName());
		old.setDescription(f.getDescription());
		old.setPhoneNumber(f.getPhoneNumber());
		old.setOfficialSite(f.getWebSite());
		old.setFacilityType(f.getType());
		// Confronto la lista di servizi salvata con quella che gli passa l'utente
		// se ha gli stessi id rimane invariata, mentre se ce ne sono di meno vengono
		// eliminati e se ce ne sono di piu aggiunti.
		Set<FacilityServicesEntity> oldService = old.getServiceFacility();
		System.out.println(oldService);

		Set<FacilityServicesEntity> actualService = new HashSet<>();
		f.getService().forEach(a -> {
			FacilityServicesEntity s = facilityServiceRepository.findById(a).get();
			actualService.add(s);
		});
		System.out.println(actualService);
//			if(!f.getService().containsAll(actual)){
//		 Controllo quale lista ha più elementi e a seconda di quale sia più grande aumento o diminuisco i servizi
		if (actualService.size() > oldService.size()) {
			oldService.addAll(actualService);
			old.setServiceFacility(oldService);
			facilityRepository.save(old);
		} else if (actualService.size() < oldService.size())
			oldService.retainAll(actualService);
		old.setServiceFacility(oldService);
		facilityRepository.save(old);
//				

		return ResponseEntity.ok("Struttura aggiornata");
	}
	
//	public ResponseEntity<String> deleteFacility(Long facilityId){
//		if (!facilityRepository.existsById(facilityId)) {
//			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessuna struttura trovata");
//		}
//		
//		Facility f = facilityRepository.findById(facilityId).get();
//		facilityRepository.delete(f);
//		return ResponseEntity.ok("Struttura eliminata");
//	}
//	public ResponseEntity<?> addComment(Long facilityId,Comment c){
//		Facility f = facilityRepository.findById(facilityId).get();
//		f.getComment().add(c);
//		facilityRepository.save(f);
//		return ResponseEntity.ok("commento aggiunto");
//	}
}
