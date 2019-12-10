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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/localization")
public class LocalizationController {
	@Autowired
	LocalizationService service;
	
	@PostMapping(value = "/labels")
	@Operation(description = "Get requested labels in requested language. Readonly", 
			tags = {"label", "localized labels","read"})
	public Map<String, String>  getLabels(@RequestBody LocalizationGetRequest request){
		return service.getLabelMap(request.getLangCode(), request.getLabels());
	}
	
	@PostMapping
	@Operation(description = "Put all labels given.", 
			tags = {"label","localized labels","put" })
	public void putLabels(@RequestBody LocalizationPutRequest request) {
		service.putLabels(request.getLangCode(), request.getValues());
	}
	
	@DeleteMapping
	@Operation(description = "Remove all labels given.", 
	tags = {"label","localized labels", "delete" })
	public void removeLabels(@RequestBody LocalizationGetRequest request) {
		service.removeLabels(request.getLangCode(), request.getLabels());
	}
}
