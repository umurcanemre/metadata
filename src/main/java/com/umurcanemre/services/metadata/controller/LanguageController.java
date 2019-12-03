package com.umurcanemre.services.metadata.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umurcanemre.services.metadata.domain.Language;
import com.umurcanemre.services.metadata.service.LanguageService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Language API's")
@RequestMapping("/language")
public class LanguageController {
	@Autowired
	LanguageService service;
	
	@GetMapping
	public Set<Language> getAll() {
		return service.getAll();
	}
	
	@PostMapping( value = "/put" )
	public void putLanguage(@RequestBody Language language) {
		service.putLanguage(language);
	}
	
	@DeleteMapping
	public void removeLanguage(@RequestBody Language language) {
		service.removeLanguage(language);
	}
}
