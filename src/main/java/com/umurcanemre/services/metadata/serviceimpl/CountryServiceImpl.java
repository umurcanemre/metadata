package com.umurcanemre.services.metadata.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umurcanemre.services.metadata.service.CountryService;

import io.lettuce.core.api.sync.RedisCommands;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	@Qualifier(value = "countryConnection")
	private RedisCommands<String, String> redis;
	private static final String COUNTRY_KEY = "countries";
	private static final Logger logger = LogManager.getLogger();
	private List<Map<String,String>> allCountries = null;
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,String>> getAll(){
		if(allCountries != null)
			return allCountries;
		
		List<String> result = redis.lrange(COUNTRY_KEY, 0, -1);
		allCountries = new ArrayList<>();
		
		result.forEach(r -> {
			try {
				allCountries.add(objectMapper.readValue(r, Map.class));
			} catch (JsonProcessingException e) {
				logger.error("JSON mapping error : {}",r);
			}
		});
		
		return allCountries;
	}

	@Override
	public void bulkInsert(String value) {
		String[] lines = value.split("\\n");
		int count = 0;
		for(String line : lines) {
			String[] words = line.split("\\-");
			String insert = "{\"name\":\""+words[0]+"\",\"code\":\""+words[1]+"\"}";
			redis.lpush(COUNTRY_KEY, insert);
			logger.debug("Insertion {} of {}", ++count, lines.length);
		}
	}

}
