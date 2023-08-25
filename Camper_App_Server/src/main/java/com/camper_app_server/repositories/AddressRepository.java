package com.camper_app_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camper_app_server.security.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>  {

}
