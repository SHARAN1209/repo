package com.c2c.vehicle.adapter.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class VehicleAdapterDto {

	private String vin;
	
	private String tenantId;

	private String make;

	private String brand;

	private String engineType;

	private String manufacturingPlant;

	private String manufacturingCountry;

	private String manufacturingLocation;

}
