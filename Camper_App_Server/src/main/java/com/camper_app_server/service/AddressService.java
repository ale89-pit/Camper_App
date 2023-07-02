package com.camper_app_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camper_app_server.repositories.AddressRepository;
import com.camper_app_server.security.entity.Address;

import jakarta.persistence.EntityExistsException;

@Service
public class AddressService {
	   @Autowired
	    private AddressRepository addressRepository;
	   
	   
    public Address saveAddress(Address c) {
        return addressRepository.save(c);
    }
      public Address updateAddress(long id,Address a) {
        if(!addressRepository.existsById(id)){
          throw new EntityExistsException("Address do not exists");
        }
        Address c = addressRepository.findById(id).get();
        return addressRepository.save(c);
    }
      public Address getAddress(long id) {
          return addressRepository.findById(id).get();
      }
}
