package com.umurcanemre.services.metadata.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrative")
public class AdministrativeController {
	
	@PostMapping(value = "/shutdown")
	public void shutdownService(/*take a max wait time as optional*/) {
		throw new UnsupportedOperationException("This operation not yet implemented");
	}
}
