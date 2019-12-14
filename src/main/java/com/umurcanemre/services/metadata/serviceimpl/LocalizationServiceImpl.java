package com.umurcanemre.services.metadata.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.umurcanemre.services.metadata.service.LocalizationService;

import io.lettuce.core.KeyValue;
import io.lettuce.core.api.sync.RedisCommands;

@Service
public class LocalizationServiceImpl implements LocalizationService {
	@Autowired
	@Qualifier(value = "localizationConnection")
	private RedisCommands<String, String> redis;
	private static final String LABEL_PREFIX = "labelhash_";

	@Override
	public Map<String, String> getLabelMap(String langCode, List<String> labels) {
		List<KeyValue<String, String>> dbResult = redis.hmget(LABEL_PREFIX+langCode, labels.toArray(new String[labels.size()]));
		Map<String, String> result = new HashMap<>(); 
		for(KeyValue<String, String> r : dbResult) {
			result.put(r.getKey(), r.getValueOrElse(r.getKey()));
		}
		
		return result;
	}

	@Override
	public void postLabels(String langCode, Map<String,String> values) { 
		redis.hmset(langCode, values);
	}

	@Override
	public void removeLabels(String langCode, List<String> labels) {
		redis.hdel(langCode, labels.toArray(new String[labels.size()]));
	}
}
