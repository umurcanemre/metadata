package com.umurcanemre.services.metadata.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umurcanemre.services.metadata.service.LanguageService;

import io.lettuce.core.KeyValue;
import io.swagger.annotations.Api;

@RestController
@Api(value = "Language API's")
@RequestMapping("/language")
public class LanguageController {
	@Autowired
	LanguageService service;
	
	@GetMapping
	public Set<String> getAll() {
		return service.getAll();
	}
	
	@GetMapping(value = "/set")
	public List<KeyValue<String, String>> getLabelSet(){
		return service.getLabelMap("labelset_tr", Arrays.asList("welcome","goodbye"));
	}
}
