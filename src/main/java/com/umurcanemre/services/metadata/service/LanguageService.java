package com.umurcanemre.services.metadata.service;

import java.util.Set;

import com.umurcanemre.services.metadata.domain.Language;

public interface LanguageService {
	// QUERIES
	Set<Language> getAll();
	
	// CRUDs
	void putLanguage(Language language);
	void removeLanguage(Language language);
}
