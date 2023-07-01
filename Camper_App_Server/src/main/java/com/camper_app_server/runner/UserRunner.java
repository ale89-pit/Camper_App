package com.camper_app_server.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.camper_app_server.security.payload.RegisterDto;
import com.camper_app_server.security.payload.UtenteDTO;
import com.camper_app_server.security.repository.UtenteDAO;
import com.camper_app_server.security.service.AuthServiceImpl;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;


@Component
public class UserRunner implements ApplicationRunner {

	@Autowired AuthServiceImpl authService;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
//		Faker f = new Faker();
//		
//		RegisterDto u = new RegisterDto();
//		u.setNome(f.name().firstName());
//		u.setCognome(f.name().lastName());
//		u.setEmail(u.getCognome()+f.internet().emailAddress());
//		u.setUserName(u.getCognome()+"."+ u.getNome());
//		u.setPassword("qwerty");
//		System.out.println(u);
//		authService.register(u);
	}

}
