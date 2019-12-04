package com.umurcanemre.services.metadata.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.umurcanemre.services.metadata.service.LocalizationService;

import io.lettuce.core.KeyValue;
import io.lettuce.core.api.sync.RedisCommands;

public class LocalizationServiceImpl implements LocalizationService {
	@Autowired
	@Qualifier(value = "localizationConnection")
	private RedisCommands<String, String> redis;

	@Override
	public List<KeyValue<String, String>> getLabelMap(String langCode, List<String> labels) {
		return redis.hmget(langCode, labels.toArray(new String[labels.size()]));
	}

	@Override
	public void putLabels(String langCode, Map<String, String> values) {
		// TODO: check if language exists 
		redis.hmset(langCode, values);
	}

	@Override
	public void removeLabels(String langCode, List<String> labels) {
		redis.hdel(langCode, labels.toArray(new String[labels.size()]));
	}
}
