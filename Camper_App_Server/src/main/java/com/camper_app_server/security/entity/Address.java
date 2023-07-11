package com.camper_app_server.security.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Address {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    long address_id;
	    @Column(nullable = false)
	    String street;
	    @Column(nullable = false)
	    Integer streetNumber;
	    @ManyToOne(fetch = FetchType.EAGER)
	    Comune comune;
}
