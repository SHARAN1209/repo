package com.c2c.vehicle.adapter.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data

public class VehicleAdapterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "vin")
	private String vin;

	@Column(name = "make")
	private String make;

	@Column(name = "brand")
	private String brand;

	@Column(name = "engine_type")
	private String engineType;

	@Column(name = "manufacturing_plant")
	private String manufacturingPlant;

	@Column(name = "manufacturing_country")
	private String manufacturingCountry;

	@Column(name = "manufacturing_location")
	private String manufacturingLocation;

}
