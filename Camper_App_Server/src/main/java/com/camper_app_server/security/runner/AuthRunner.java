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
import com.camper_app_server.security.entity.FacilityServices;
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
		FacilityServices elec = new FacilityServices();
		elec.setService(EServiceFacility.ELECTRICITY);
		facilitiServiceRepository.save(elec);
		
		FacilityServices shower = new FacilityServices();
		shower.setService(EServiceFacility.SHOWER);
		facilitiServiceRepository.save(shower);
		
		FacilityServices hotShower = new FacilityServices();
		hotShower.setService(EServiceFacility.HOT_SHOWER);
		facilitiServiceRepository.save(hotShower);
		
		FacilityServices toilette = new FacilityServices();
		toilette.setService(EServiceFacility.TOILETTE);
		facilitiServiceRepository.save(toilette);
		
		FacilityServices wifi = new FacilityServices();
		wifi.setService(EServiceFacility.WI_FI);
		facilitiServiceRepository.save(wifi);
		
		FacilityServices keptNight = new FacilityServices();
		keptNight.setService(EServiceFacility.KEPT_NIGHT);
		facilitiServiceRepository.save(keptNight );
		
		FacilityServices toiletteFlush = new FacilityServices();
		toiletteFlush.setService(EServiceFacility.TOLIETTE_FLUSH);
		facilitiServiceRepository.save(toiletteFlush);
		
		FacilityServices nauticFlush = new FacilityServices();
		nauticFlush.setService(EServiceFacility.NAUTICAL_TOILETTE_FLUSH);
		facilitiServiceRepository.save(nauticFlush);
		
		FacilityServices market = new FacilityServices();
		market.setService(EServiceFacility.MARKET);
		facilitiServiceRepository.save(market);

	}

}
