package com.camper_app_server.security.runner;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.camper_app_server.enumerated.ERole;
import com.camper_app_server.enumerated.EServiceFacility;
import com.camper_app_server.repositories.FacilityServiceRepository;

import com.camper_app_server.security.entity.FacilityServicesEntity;
import com.camper_app_server.security.entity.Role;
import com.camper_app_server.security.repository.RoleDAO;
import com.camper_app_server.security.repository.UtenteDAO;
import com.camper_app_server.security.service.AuthService;



@Component
public class AuthRunner implements ApplicationRunner {

	@Autowired
	RoleDAO roleRepository;
	@Autowired
	UtenteDAO userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthService authService;
	@Autowired
	FacilityServiceRepository facilitiServiceRepository;

	private Set<Role> adminRole;
	private Set<Role> moderatorRole;
	private Set<Role> userRole;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		// Metodo da lanciare solo la prima volta
		// Serve per salvare i ruoli nel DB
//		Riempio le tabelle role e facilityService con Enum gia predisposti
//		per facilitare l'inserimento nella parte frontEnd
		
		
		if(roleRepository.findAll().isEmpty()) {
			setRoleDefault();
		}
		if(facilitiServiceRepository.findAll().isEmpty()) {
			setServiceFacility();
		}



	}

	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);

		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);

		// adminRole = new HashSet<Role>();
		// adminRole.add(admin);
		// adminRole.add(moderator);
		// adminRole.add(user);
		//
		// moderatorRole = new HashSet<Role>();
		// moderatorRole.add(moderator);
		// moderatorRole.add(user);
		//
		// userRole = new HashSet<Role>();
		// userRole.add(user);
	}
	
	public void setServiceFacility() {
		FacilityServicesEntity loadWater = new FacilityServicesEntity();
		loadWater.setService(EServiceFacility.WATER_LOADING);
		facilitiServiceRepository.save(loadWater);
		
		FacilityServicesEntity elec = new FacilityServicesEntity();
		elec.setService(EServiceFacility.ELECTRICITY);
		facilitiServiceRepository.save(elec);
		
		FacilityServicesEntity shower = new FacilityServicesEntity();
		shower.setService(EServiceFacility.SHOWER);
		facilitiServiceRepository.save(shower);
		
		FacilityServicesEntity hotShower = new FacilityServicesEntity();
		hotShower.setService(EServiceFacility.HOT_SHOWER);
		facilitiServiceRepository.save(hotShower);
		
		FacilityServicesEntity toilette = new FacilityServicesEntity();
		toilette.setService(EServiceFacility.TOILETTE);
		facilitiServiceRepository.save(toilette);
		
		FacilityServicesEntity wifi = new FacilityServicesEntity();
		wifi.setService(EServiceFacility.WI_FI);
		facilitiServiceRepository.save(wifi);
		
		FacilityServicesEntity keptNight = new FacilityServicesEntity();
		keptNight.setService(EServiceFacility.KEPT_NIGHT);
		facilitiServiceRepository.save(keptNight );
		
		FacilityServicesEntity toiletteFlush = new FacilityServicesEntity();
		toiletteFlush.setService(EServiceFacility.TOLIETTE_FLUSH);
		facilitiServiceRepository.save(toiletteFlush);
		
		FacilityServicesEntity nauticFlush = new FacilityServicesEntity();
		nauticFlush.setService(EServiceFacility.NAUTICAL_TOILETTE_FLUSH);
		facilitiServiceRepository.save(nauticFlush);
		
		FacilityServicesEntity market = new FacilityServicesEntity();
		market.setService(EServiceFacility.MARKET);
		facilitiServiceRepository.save(market);
		
		
	}

}
