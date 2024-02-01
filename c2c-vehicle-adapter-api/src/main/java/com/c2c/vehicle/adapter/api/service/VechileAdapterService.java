package com.c2c.vehicle.adapter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.c2c.vehicle.adapter.api.constants.ResponseCodes;
import com.c2c.vehicle.adapter.api.dto.OemResponseDto;
import com.c2c.vehicle.adapter.api.dto.Response;
import com.c2c.vehicle.adapter.api.dto.ResponseData;
import com.c2c.vehicle.adapter.api.dto.VehicleAdapterDto;
import com.c2c.vehicle.adapter.api.entity.DefaultData;
import com.c2c.vehicle.adapter.api.entity.VehicleAdapterEntity;
import com.c2c.vehicle.adapter.api.repo.DefaultDataRepo;
import com.c2c.vehicle.adapter.api.repo.VehicleAdapterRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VechileAdapterService {
	
	@Autowired
	private VehicleAdapterRepo vehicleAdapterRepo;

	@Autowired
	private DefaultDataRepo defaultDataRepo;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${is.oem.api.enable}")
	private boolean oemRequest;
	
	@Value("${oem.url.end.point}")
    private String urlEndPoint;

	public String saveDefaultData(DefaultData defaultData) {
		defaultDataRepo.save(defaultData);
		return "data saved";
	}
	
	public String saveVechileAdapterData(VehicleAdapterEntity adapterEntity) {
		vehicleAdapterRepo.save(adapterEntity);
		return "data saved";
	}

	public String deleteVechileAdapterData(Long id) {
		vehicleAdapterRepo.deleteById(id);
		return "Vechile Adapter Data removed !! " + id;
	}

	public Response getVechileAdapterData(String vin, String tenantId) throws JsonMappingException, JsonProcessingException {
		if (oemRequest) {
			return getDataFromOemServieOrDefaultData(vin, tenantId);
		} else {
			return getDataFromDb(vin, tenantId);
		}
	}

	public Response getDataFromDb(String vin, String tenantId) {
		VehicleAdapterEntity adapterEntity = vehicleAdapterRepo.findByVin(vin);
		if(adapterEntity != null) {
			VehicleAdapterDto data = new VehicleAdapterDto();
			data.setVin(vin);
			data.setTenantId(tenantId);
			data.setMake(adapterEntity.getMake());
			data.setBrand(adapterEntity.getBrand());
			data.setEngineType(adapterEntity.getEngineType());
			data.setManufacturingPlant(adapterEntity.getManufacturingPlant());
			data.setManufacturingCountry(adapterEntity.getManufacturingCountry());
			data.setManufacturingLocation(adapterEntity.getManufacturingLocation());
			
			
			Response response = new Response();
			response.setResponseCode(ResponseCodes.SUCCESS);
			ResponseData responseData = new ResponseData();
			response.setResponseData(responseData);
			responseData.setMessage(ResponseCodes.SUCCESSMEG);
			responseData.setVehicleAdapterDto(data);
			return response;
		}else {
			
			Response response = new Response();
			response.setResponseCode(ResponseCodes.SUCCESS);
			ResponseData responseData = new ResponseData();
			response.setResponseData(responseData);
			responseData.setMessage(ResponseCodes.SUCCESSMEG);
			responseData.setVehicleAdapterDto(null);
			return response;
		}
}
	public Response getDataFromOemServieOrDefaultData(String vin, String tenantId) throws JsonMappingException, JsonProcessingException {
		if(urlEndPoint.length()>0) {
			return getDataFromOemServie(vin,tenantId);
		}else {
			return getDefaultData(vin, tenantId);
		}
		
	}
	private Response getDefaultData(String vin, String tenantId) {
		System.out.println("inside defaultdata method");
		DefaultData defaultData = defaultDataRepo.findByTenantId(tenantId);
		VehicleAdapterDto data = new VehicleAdapterDto();
		data.setVin(vin);
		data.setTenantId(tenantId);
		data.setMake(defaultData.getMake());
		data.setBrand(defaultData.getBrand());
		data.setEngineType(defaultData.getEngineType());
		data.setManufacturingPlant(defaultData.getManufacturingPlant());
		data.setManufacturingCountry(defaultData.getManufacturingCountry());
		data.setManufacturingLocation(defaultData.getManufacturingLocation());

		Response response = new Response();
		response.setResponseCode(ResponseCodes.SUCCESS);
		ResponseData responseData = new ResponseData();
		response.setResponseData(responseData);
		responseData.setMessage(ResponseCodes.SUCCESSMEG);
		responseData.setVehicleAdapterDto(null);
		return response;
		
	}

	public Response getDataFromOemServie(String vin, String tenantId) throws JsonMappingException, JsonProcessingException {
		System.out.println("inside oem method");
		String url = urlEndPoint + vin + "/" + tenantId;
		RestTemplate restTemplate = new RestTemplate();

		try {
			String body = restTemplate.getForObject(url, String.class);
			if (body != null) {

				OemResponseDto oemBody = objectMapper.readValue(body, OemResponseDto.class);
				VehicleAdapterDto data = new VehicleAdapterDto();
				data.setVin(vin);
				data.setTenantId(tenantId);
				data.setMake(oemBody.getMake());
				data.setBrand(oemBody.getBrand());
				data.setEngineType(oemBody.getEngineType());
				data.setManufacturingPlant(oemBody.getManufacturingPlant());
				data.setManufacturingCountry(oemBody.getManufacturingCountry());
				data.setManufacturingLocation(oemBody.getManufacturingLocation());

				Response response = new Response();
				response.setResponseCode(ResponseCodes.SUCCESS);
				ResponseData responseData = new ResponseData();
				response.setResponseData(responseData);
				responseData.setMessage(ResponseCodes.SUCCESSMEG);
				responseData.setVehicleAdapterDto(data);

				return response;
			} else {
				Response response = new Response();
				response.setResponseCode(ResponseCodes.SUCCESS);
				ResponseData responseData = new ResponseData();
				response.setResponseData(responseData);
				responseData.setMessage(ResponseCodes.SUCCESSMEG);
				responseData.setVehicleAdapterDto(null);
				return response;
			}
		} catch (Exception e) {
			// Handle the exception (e.g., log an error)
			e.printStackTrace();
			return null; // Or throw an exception, depending on your requirements
		}

	}

}
