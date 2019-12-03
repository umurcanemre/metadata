package com.umurcanemre.services.metadata.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umurcanemre.services.metadata.service.LocalizationService;

import io.lettuce.core.KeyValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Localization API's")
@RequestMapping("/localization")
public class LocalizationController {
	@Autowired
	LocalizationService service;
	
	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	@GetMapping
	@ApiOperation(value = "test api", notes = "to be removed")
	public String test() {
		return "WORKS! on " + activeProfile.toUpperCase();
	}

	
	@GetMapping(value = "/set")
	public List<KeyValue<String, String>> getLabelSet(){
		return service.getLabelMap("labelset_tr", Arrays.asList("welcome","goodbye"));
	}
	
	@PostMapping
	public void putLabels(@PathVariable String langCode, @RequestBody Map<String,String> values) {
		service.putLabels(langCode, values);
	}
	
	@DeleteMapping
	void removeLabels(@PathVariable String langCode, @RequestBody List<String> labels) {
		service.removeLabels(langCode, labels);
	}
}
