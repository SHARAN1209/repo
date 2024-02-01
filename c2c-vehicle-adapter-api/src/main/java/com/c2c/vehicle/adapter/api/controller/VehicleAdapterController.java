package com.c2c.vehicle.adapter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.c2c.vehicle.adapter.api.dto.Response;
import com.c2c.vehicle.adapter.api.entity.DefaultData;
import com.c2c.vehicle.adapter.api.entity.VehicleAdapterEntity;
import com.c2c.vehicle.adapter.api.service.VechileAdapterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class VehicleAdapterController {

	@Autowired
	private VechileAdapterService vechileAdapterService;

	@PostMapping("/save/vechile-adapter-data")
	public String addVechileAdapterData(@RequestBody VehicleAdapterEntity vehicleAdapterEntity) {
		return vechileAdapterService.saveVechileAdapterData(vehicleAdapterEntity);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteVechileData(@PathVariable Long id) {
		return vechileAdapterService.deleteVechileAdapterData(id);
	}

	@PostMapping("/save/default-values")
	public String addDefaultData(@RequestBody DefaultData defaultData) {
		return vechileAdapterService.saveDefaultData(defaultData);
	}

	@GetMapping("/vechile-adapterdata2/{vin}/{tenantId}")
	public Response getData(@PathVariable String vin, @PathVariable String tenantId)
			throws JsonMappingException, JsonProcessingException {
		return vechileAdapterService.getVechileAdapterData(vin, tenantId);
	}
}
