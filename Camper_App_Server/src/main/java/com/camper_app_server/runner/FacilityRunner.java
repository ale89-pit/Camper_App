package com.camper_app_server.runner;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.camper_app_server.enumerated.FacilityType;
import com.camper_app_server.repositories.AddressRepository;
import com.camper_app_server.repositories.FacilityRepository;
import com.camper_app_server.repositories.FacilityServiceRepository;
import com.camper_app_server.security.entity.Address;
import com.camper_app_server.security.entity.Facility;
import com.camper_app_server.security.entity.FacilityServicesEntity;
import com.camper_app_server.security.payload.AddressDTO;
import com.camper_app_server.security.payload.FacilityDTO;
import com.camper_app_server.service.FacilityService;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityManager;

@Component

public class FacilityRunner implements ApplicationRunner {

	@Autowired FacilityService facilityService;
	@Autowired FacilityRepository facilityRepository;
	@Autowired FacilityServiceRepository facilityServiceRepository;
	@Autowired AddressRepository addressRepository;
	@Autowired
	private EntityManager entityManager;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		//inserimento di 21 STRUTTURE
//		for(int i =0;i<21;i++) {
		Faker fake = new Faker(new Locale("it"));
		Facility fac = new Facility();
		fac.setCover(fake.internet().image());
		fac.setDescription(fake.lorem().sentence());
		fac.setName(fake.name().firstName());
		fac.setPhoneNumber(fake.phoneNumber().phoneNumber());
		int random = fake.random().nextInt(1, 3);
		fac.setFacilityType(random == 1? FacilityType.CAMPING: random == 2? FacilityType.PARKING_AREA: FacilityType.FREE_PARKING_AREA);
		Set<Long> service = new HashSet<>();
		service.add(1l);
		service.add(2l);
		service.add(3l);
		service.add(4l);
		service.add(5l);
		service.add(6l);
		service.add(7l);
		service.add(8l);
		service.add(9l);
		System.out.println(service);
		Set<FacilityServicesEntity> entityService = new HashSet<>();
//		 service.forEach(a -> {
//		        FacilityServicesEntity s = facilityServiceRepository.findById(a).get();
//		        System.out.println(s);
//		        entityService.add(s);
//		    });
		
//		service.forEach(a -> {
//		    FacilityServicesEntity s = facilityServiceRepository.findById(a).orElse(null);
//		    if (s != null) {
//		        FacilityServicesEntity mergedEntity = entityManager.merge(s);
//		        entityService.add(mergedEntity);
//		    }
//		});
		
		 fac.setServiceFacility(entityService);
		 
		fac.setOfficialSite(fake.internet().url());
		fac.setEmail(fac.getName()+"@email.com");
//		Long rd = fake.random().nextLong(25);
//		Address a =  addressRepository.findById(rd).get();
//		
//		fac.setAddress(a);
		System.out.println(fac);
//		facilityRepository.save(fac);
//		insertFacility(fac);
//		System.out.println(fac);
//		}

	}
	public Facility insertFacility(FacilityDTO f) {
	    Facility insert = new Facility();
	    insert.setCover(f.getCover());
	    insert.setName(f.getName());
	    insert.setDescription(f.getDescription());
	    insert.setPhoneNumber(f.getPhoneNumber());
	    insert.setFacilityType(f.getFacilityType().equals("CAMPING")? FacilityType.CAMPING: f.getFacilityType().equals("PARKING_AREA")? FacilityType.PARKING_AREA:FacilityType.FREE_PARKING_AREA);
	    insert.setOfficialSite(f.getOfficialSite());
	    Set<FacilityServicesEntity> service = new HashSet<>();
//	    f.getService().forEach(a -> {
//	        FacilityServicesEntity s = facilityServiceRepository.findById(a).get();
//	        service.add(s);
//	    });
	    insert.setServiceFacility(service);
	    return facilityRepository.save(insert);
	}
}
