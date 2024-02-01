package com.c2c.vehicle.adapter.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@ToString
@JsonInclude(Include.NON_NULL)
public class Response {

	@JsonProperty("responseCode")
	private String responseCode;
	@JsonProperty("responseData")
	private ResponseData responseData;
}
