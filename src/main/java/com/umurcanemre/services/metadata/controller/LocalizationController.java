package com.umurcanemre.services.metadata.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umurcanemre.services.metadata.requestbody.localization.LocalizationGetRequest;
import com.umurcanemre.services.metadata.requestbody.localization.LocalizationPostRequest;
import com.umurcanemre.services.metadata.service.LocalizationService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/localization")
public class LocalizationController {
	@Autowired
	LocalizationService service;
	
	@GetMapping(value = "/labels")
	@Operation(description = "Get values of requested labels in requested language. Readonly")
	public Map<String, String>  getLabels(@RequestParam LocalizationGetRequest request){
		return service.getLabelMap(request.getLangCode(), request.getLabels());
	}
	
	@PostMapping
	@Operation(description = "Post all labels given.")
	public void postLabels(@RequestBody LocalizationPostRequest request) {
		service.postLabels(request.getLangCode(), request.getValues());
	}
	
	@DeleteMapping
	@Operation(description = "Remove all labels given.")
	public void removeLabels(@RequestBody LocalizationGetRequest request) {
		service.removeLabels(request.getLangCode(), request.getLabels());
	}
}
