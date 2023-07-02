package com.camper_app_server.runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.camper_app_server.repositories.ComuniRepository;
import com.camper_app_server.repositories.ProvinceRepository;
import com.camper_app_server.security.entity.Comune;
import com.camper_app_server.security.entity.Province;


@Component
public class ProvinceComuniRunner implements ApplicationRunner{
	 @Autowired
   ProvinceRepository provinceDAO;
    @Autowired
    ComuniRepository municDAO;
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		if(provinceDAO.findAll().isEmpty()) {
		 List<List<String>> recordsProvince = new ArrayList<>();
	     BufferedReader brProvince = new BufferedReader(
	     new
	     FileReader("C:\\Users\\Aless\\Desktop\\Camper_App\\Camper_App_Server\\src\\main/resources/province-italiane.csv"));
	     String lineProvince;
	     while ((lineProvince = brProvince.readLine()) != null) {
	     String[] values = lineProvince.split(";");
     recordsProvince.add(Arrays.asList(values));
     Province p = new Province();
	     p.setSign(values[0]);
	     p.setName(values[1]);
	     p.setRegion(values[2]);
//	     System.out.println(p);
	     provinceDAO.save(p);
	     // log.info(values[0].toString() + " " + values[1].toString());
//	      records.forEach(el -> log.info(el.toString()));
	     }
		}
	
	if(municDAO.findAll().isEmpty()) {
	 List<List<String>> records = new ArrayList<>();
   BufferedReader br = new BufferedReader(
           new FileReader("C:\\\\Users\\\\Aless\\\\Desktop\\\\Camper_App\\\\Camper_App_Server\\\\src\\\\main/resources//comuni-italiani.csv"));
		String line;
		while ((line = br.readLine()) != null) {
		    String[] values = line.split(";");
		     Comune m = new Comune();  			     
		     m.setName(values[2]);
		     m.setProvincename(provinceDAO.findByName(values[3]));
		     m.setProvince_id(values[0]);
		     m.setMunicipality_id(values[1]);  			    
		     municDAO.save(m);
	    records.add(Arrays.asList(values));
//	    System.out.println(records);
//	   // log.info(values[0].toString() + " " + values[1].toString() + " "
//	   //         + values[2].toString() + " ");		   
	     }		     
	}
	
}
}
