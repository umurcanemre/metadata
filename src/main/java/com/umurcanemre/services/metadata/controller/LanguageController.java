package com.umurcanemre.services.metadata.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umurcanemre.services.metadata.domain.Language;
import com.umurcanemre.services.metadata.service.LanguageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/language")
public class LanguageController {
	@Autowired
	LanguageService service;

	@GetMapping(value = "codes")
	@Operation(description = "Get all the given codes' languages. All if no codes given. Readonly")
	public Map<String, Map<String, String>> get(@RequestParam(required = false) List<String> code) {
		return service.get(code);
	}

	@PutMapping
	@Operation(description = "Put all langueages given. If a reserved word is given as a language exception is thrown but itareted languages still written", 
			responses = { 
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "5XX", description = "_reserved_code_ cannot be a language code") })
	public void putLanguage(@RequestBody Set<Language> languages) {
		service.putLanguage(languages);
	}

	@DeleteMapping
	@Operation(description = "Delete given langueage", 
			responses = {
					@ApiResponse(responseCode = "200", description = "Success") })
	public void removeLanguage(@RequestBody String langCode) {
		service.removeLanguage(langCode);
	}
}
