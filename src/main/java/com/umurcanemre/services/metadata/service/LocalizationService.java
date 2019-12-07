package com.umurcanemre.services.metadata.service;

import java.util.List;
import java.util.Map;

public interface LocalizationService {
	// QUERIES 
	Map<String, String>  getLabelMap(String langCode, List<String> labels);
	
	// CRUDs
	void putLabels(String langCode, Map<String,String> values);
	void removeLabels(String langCode, List<String> labels);
}
