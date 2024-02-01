package com.c2c.vehicle.adapter.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c2c.vehicle.adapter.api.entity.VehicleAdapterEntity;

public interface VehicleAdapterRepo extends JpaRepository<VehicleAdapterEntity, Long> {
	VehicleAdapterEntity findByVin(String vin);
}
