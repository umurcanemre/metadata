package com.umurcanemre.services.metadata.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Localizations API's")
@RequestMapping("/localization")
public class LocalizationController {
	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	@GetMapping
	@ApiOperation(value = "test api", notes = "to be removed")
	public String test() {
		return "WORKS! on " + activeProfile.toUpperCase();
	}
}
