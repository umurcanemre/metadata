package com.umurcanemre.services.metadata.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umurcanemre.services.metadata.service.CountryService;

import lombok.Setter;

@Setter
@RestController
@RequestMapping("/country")
public class CountryController {
	@Autowired
	private CountryService countryService;
	
	@GetMapping
	public List<Map<String,String>> getAll(){
		return countryService.getAll();
	}
	
	@PostMapping(value = "bulkinsert", consumes = "text/html")
	public void bulkInsert(@RequestBody String values) {
		countryService.bulkInsert(values);
	}
}
