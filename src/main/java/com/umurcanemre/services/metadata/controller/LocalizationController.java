package com.umurcanemre.services.metadata.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umurcanemre.services.metadata.requestbody.localization.LocalizationGetRequest;
import com.umurcanemre.services.metadata.requestbody.localization.LocalizationPutRequest;
import com.umurcanemre.services.metadata.service.LocalizationService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Localization API's")
@RequestMapping("/localization")
public class LocalizationController {
	@Autowired
	LocalizationService service;
	
	@PostMapping(value = "/labels")
	public Map<String, String>  getLabels(@RequestBody LocalizationGetRequest request){
		return service.getLabelMap(request.getLangCode(), request.getLabels());
	}
	
	@PostMapping
	public void putLabels(@RequestBody LocalizationPutRequest request) {
		service.putLabels(request.getLangCode(), request.getValues());
	}
	
	@DeleteMapping
	public void removeLabels(@RequestBody LocalizationGetRequest request) {
		service.removeLabels(request.getLangCode(), request.getLabels());
	}
}
