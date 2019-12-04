package com.umurcanemre.services.metadata.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.umurcanemre.services.metadata.domain.Language;

import io.lettuce.core.KeyValue;

public interface LanguageService {
	// QUERIES
	Map<String,List<KeyValue<String, String>>> get(String code);

	Set<String> getAllCodes();

	// CRUDs
	void putLanguage(Language language);

	void removeLanguage(String code);

}
