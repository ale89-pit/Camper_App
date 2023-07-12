package com.camper_app_server.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.camper_app_server.enumerated.FacilityType;
import com.camper_app_server.repositories.AddressRepository;
import com.camper_app_server.repositories.ComuniRepository;
import com.camper_app_server.repositories.FacilityRepository;
import com.camper_app_server.repositories.FacilityServiceRepository;
import com.camper_app_server.security.entity.Address;
import com.camper_app_server.security.entity.Comune;
import com.camper_app_server.security.entity.Facility;
import com.camper_app_server.security.entity.FacilityServicesEntity;
import com.camper_app_server.security.entity.FileData;
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
	
	@Autowired 
	ComuniRepository comuniDAO;
	
	@Autowired
	AddressService  addressService;

	public List<Facility> getAll() {
		return facilityRepository.findAll();
	}

	public Facility getById(Long facilityId) {
		if (!facilityRepository.existsById(facilityId)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessuna struttura trovata");
		}
		return (facilityRepository.findById(facilityId).get());
	}

	public Facility insertFacility(FacilityDTO f) {
		Facility insert = new Facility();
		Set<FacilityServicesEntity> service = new HashSet<>();
		insert.setCover(f.getCover());
		insert.setName(f.getName());
		insert.setEmail(f.getEmail());
		insert.setDescription(f.getDescription());
		insert.setPhoneNumber(f.getPhoneNumber());
		insert.setFacilityType(f.getFacilityType().equals("CAMPING")? FacilityType.CAMPING: f.getFacilityType().equals("PARKING_AREA")? FacilityType.PARKING_AREA:FacilityType.FREE_PARKING_AREA);
		insert.setOfficialSite(f.getOfficialSite());
		
		
		//inserimento dell'indirizzo tramite addressDTO
		
		Address addr = new Address();
		addr.setStreet(f.getAddress().getStreet());
		addr.setStreetNumber(f.getAddress().getStreetNumber());
		if(comuniDAO.existsById(f.getAddress().getComune())){
		 Comune  c= comuniDAO.findById(f.getAddress().getComune()).get();
		 addr.setComune(c);
		}
		Address save = addressService.saveAddress(addr);
		insert.setAddress(save);
		
//		Qui inserisco solo l'id del servizio assiociato alla tabella service per implementarlo al set di servizi di ogni struttura
		
		
		f.getService().forEach(ser -> {
			FacilityServicesEntity s = facilityServiceRepository.findById(ser).get();
			service.add(s);
		});
		insert.setServiceFacility(service);
		System.out.println(insert);
		return facilityRepository.save(insert);
		
	}

	public Facility updateFacility(Long id, FacilityDTO f) {
		if (!facilityRepository.existsById(id)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessuna struttura trovata");
		}

		Facility old = facilityRepository.findById(id).get();
		if(f.getCover()!= null) {
			old.setCover(f.getCover());
		}
		old.setCover(old.getCover());
		
		if(f.getName()!= null) {
			
			old.setName(f.getName());
		}
		old.setName(old.getName());
		if(f.getDescription()!= null) {
			old.setDescription(f.getDescription());
		}
		if(f.getPhoneNumber()!= null) {
			old.setPhoneNumber(f.getPhoneNumber());
		}
		old.setPhoneNumber(old.getPhoneNumber());
		if(f.getOfficialSite()!= null) {
			old.setOfficialSite(f.getOfficialSite());
			
		}
		old.setOfficialSite(old.getOfficialSite());
		if(f.getFacilityType()!= null) {
			old.setFacilityType(f.getFacilityType().equals("CAMPING")? FacilityType.CAMPING: f.getFacilityType().equals("PARKING_AREA")? FacilityType.PARKING_AREA:FacilityType.FREE_PARKING_AREA);
			
		}
		if(f.getEmail()!= null) {
			old.setEmail(f.getEmail());
		}
		
		old.setEmail(old.getEmail());
		
		old.setFacilityType(old.getFacilityType());
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
		
			
		
		Address addr = addressService.getAddress(old.getAddress().getAddress_id()); 
		if(f.getAddress()!=null) {
			addr.setStreet(f.getAddress().getStreet());
			
		}
		addr.setStreet(old.getAddress().getStreet());
		
		if(f.getAddress().getStreet()!= null) {
			addr.setStreetNumber(f.getAddress().getStreetNumber());
			
		}
		addr.setStreetNumber(old.getAddress().getStreetNumber());
		if(f.getAddress().getComune()!=null) {
			
			if(comuniDAO.existsById(f.getAddress().getComune())){
				Comune  c= comuniDAO.findById(f.getAddress().getComune()).get();
				addr.setComune(c);
			}
		}
		addr.setComune(old.getAddress().getComune());
		
		Address save = addressService.updateAddress(addr.getAddress_id(),addr);
		old.setAddress(save);
		
//			if(!f.getService().containsAll(actual)){
//		 Controllo quale lista ha più elementi e a seconda di quale sia più grande aumento o diminuisco i servizi confrontandoli
		if (actualService.size() > oldService.size()) {
			oldService.addAll(actualService);
			old.setServiceFacility(oldService);
			facilityRepository.save(old);
		} else if (actualService.size() < oldService.size()) {
			oldService.retainAll(actualService);
			old.setServiceFacility(oldService);
		facilityRepository.save(old);
		}
		
		return old;
	}
	
	public Facility addPhotoByUser(Long facility_Id,FileData photo) {
		Facility f = facilityRepository.findById(facility_Id).get();
		
		f.getFotoUpLoadFromUser().add(photo);
		facilityRepository.save(f);
		return null;
	}

	public ResponseEntity<String> deleteFacility(Long facilityId) {
		if (!facilityRepository.existsById(facilityId)) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "nessuna struttura trovata");
		}

		Facility f = facilityRepository.findById(facilityId).get();
		facilityRepository.delete(f);
		return ResponseEntity.ok("Struttura eliminata");
	}
	
	public List<Facility> searchFacility(String desc,String title){
		
		List<Facility> querySearch = facilityRepository.findByDescriptionContainingOrNameContaining(desc, title);
		
		if(querySearch.isEmpty()) {
			 throw new MyAPIException(HttpStatus.NOT_FOUND, "nessuna struttura trovata");
		}else {
			return querySearch;
		}
		
	}


}