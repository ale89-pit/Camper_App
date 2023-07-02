package com.camper_app_server.security.payload;
import java.util.List;
import java.util.Set;

import com.camper_app_server.enumerated.FacilityType;
import com.camper_app_server.security.entity.Comune;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
	 	
	    @Column(nullable = false)
	    String street;
	    @Column(nullable = false)
	    Integer streetNumber;
	    @ManyToOne(fetch = FetchType.EAGER)
	    Long comune;
}
