package com.umurcanemre.services.metadata.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umurcanemre.services.metadata.service.LanguageService;

import io.lettuce.core.KeyValue;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.Data;

@Service
@Data
public class LanguageServiceImpl implements LanguageService {
	@Autowired
	private RedisCommands<String, String> redis;

	@Override
	public Set<String> getAll() {
		// TODO Auto-generated method stub
		return Collections.emptySet();
	}

	@Override
	public List<KeyValue<String, String>> getLabelMap(String langCode, List<String> labels) {
		return redis.hmget(langCode, labels.toArray(new String[labels.size()]));
	}

}
