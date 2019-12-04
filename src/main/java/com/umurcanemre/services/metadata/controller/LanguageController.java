package com.umurcanemre.services.metadata.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umurcanemre.services.metadata.domain.Language;
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
	public Map<String, List<KeyValue<String, String>>> getAll(@PathVariable String code) {
		return service.get(code);
	}

	@GetMapping(value = "codes")
	public Set<String> getCodes() {
		return service.getAllCodes();
	}

	@PostMapping(value = "/put")
	public void putLanguage(@RequestBody Language language) {
		service.putLanguage(language);
	}

	@DeleteMapping
	public void removeLanguage(@RequestBody String langCode) {
		service.removeLanguage(langCode);
	}
}
