package com.umurcanemre.services.metadata.service;

import java.util.List;
import java.util.Map;

public interface CountryService {
	//QUERY
	List<Map<String,String>> getAll();
	
	void bulkInsert(String value);
}
