package com.camper_app_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camper_app_server.security.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>  {

}
