package com.umurcanemre.services.metadata.service;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Set;

import com.umurcanemre.services.metadata.domain.Language;

public interface LanguageService {
	// QUERIES
	/**
	 * Get all listed languages, all languages if set is null or empty 
	 * 
	 * @param codes
	 * @return Language objects mapped by their codes
	 */
	Map<String,Map<String, String>> get(Set<String> codes);

	/**
	 * Get all language codes
	 * @return all language codes as a set of strings
	 */
	Set<String> getAllCodes();

	// CRUDs
	/**
	 * Puts given languages. If an exception is thrown, languages iterated before is still saved
	 * 
	 * @param languages
	 * @throws InvalidParameterException if a reserved keyword is given as a language code
	 */
	void putLanguage(Set<Language> languages);

	void removeLanguage(String code);

}
