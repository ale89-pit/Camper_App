package com.camper_app_server.runner;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.camper_app_server.repositories.AddressRepository;
import com.camper_app_server.repositories.ComuniRepository;
import com.camper_app_server.security.entity.Address;
import com.camper_app_server.security.entity.Comune;
import com.github.javafaker.Faker;


@Component
public class AdressRunner implements ApplicationRunner {

	@Autowired AddressRepository addressRepository;
	@Autowired ComuniRepository comuniRepository; 
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
//		for(int i=0 ; i<100 ; i++) {
//		Faker fake = new Faker(new Locale("it"));
//		Address a = new Address();
//		a.setStreet(fake.address().streetName());
//		a.setStreetNumber(Integer.parseInt(fake.address().buildingNumber()));
//		
//		Comune c =  comuniRepository.findById(fake.random().nextLong(7904)).get();
//		a.setComune( c);
//			System.out.println(a);
//		addressRepository.save(a);
//		}
	}

}
