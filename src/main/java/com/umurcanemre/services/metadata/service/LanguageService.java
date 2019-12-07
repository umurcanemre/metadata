package com.umurcanemre.services.metadata.service;

import java.util.Map;
import java.util.Set;

import com.umurcanemre.services.metadata.domain.Language;

public interface LanguageService {
	// QUERIES
	Map<String,Map<String, String>> get(Set<String> codes);

	Set<String> getAllCodes();

	// CRUDs
	void putLanguage(Language language);

	void removeLanguage(String code);

}
