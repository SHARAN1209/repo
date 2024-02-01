package com.c2c.vehicle.adapter.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OemResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3070666970282038639L;

	private String vin;
	
	private String tenantId;

	private String make;

	private String brand;

	private String engineType;

	private String manufacturingPlant;

	private String manufacturingCountry;

	private String manufacturingLocation;
}
