package com.camper_app_server.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "provinces")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Province {
	@Id
    String sign;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String region;
}
