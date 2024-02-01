package com.c2c.vehicle.adapter.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c2c.vehicle.adapter.api.entity.DefaultData;

public interface DefaultDataRepo extends JpaRepository<DefaultData, Integer>{
	DefaultData findByTenantId(String tenantId);
}
