package com.umurcanemre.services.metadata.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.umurcanemre.services.metadata.domain.Language;
import com.umurcanemre.services.metadata.service.LanguageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController(value = "/language")
public class LanguageController {
	@Autowired
	LanguageService service;

	@PostMapping
	@Operation(description = "Get all the given codes' languages. All if no codes given. Readonly", 
			tags = {"language" })
	public Map<String, Map<String, String>> get(@RequestBody(required = false) Set<String> code) {
		return service.get(code);
	}

	@GetMapping(value = "codes")
	@Operation(description = "Get all language codes that exist. Readonly", tags = { "language code" })
	public Set<String> getCodes() {
		return service.getAllCodes();
	}

	@PostMapping(value = "/put")
	@Operation(description = "Put all langueages given. If a reserved word is given as a language exception is thrown but itareted languages still written", 
			tags = {"language" }, 
			responses = { 
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "5XX", description = "_reserved_code_ cannot be a language code") })
	public void putLanguage(@RequestBody Set<Language> languages) {
		service.putLanguage(languages);
	}

	@DeleteMapping
	@Operation(description = "Delete given langueage", tags = { "language" }, 
			responses = {
					@ApiResponse(responseCode = "200", description = "Success") })
	public void removeLanguage(@RequestBody String langCode) {
		service.removeLanguage(langCode);
	}
}
