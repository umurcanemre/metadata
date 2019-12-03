package com.umurcanemre.services.metadata.service;

import java.util.List;
import java.util.Map;

import io.lettuce.core.KeyValue;

public interface LocalizationService {
	// QUERIES 
	List<KeyValue<String, String>> getLabelMap(String langCode, List<String> labels);
	
	// CRUDs
	void putLabels(String langCode, Map<String,String> values);
	void removeLabels(String langCode, List<String> labels);
}
