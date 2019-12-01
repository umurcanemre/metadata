package com.umurcanemre.services.metadata.service;

import java.util.List;
import java.util.Set;

import io.lettuce.core.KeyValue;

public interface LanguageService {
	Set<String> getAll();
	List<KeyValue<String, String>> getLabelMap(String langCode, List<String> labels);
}
