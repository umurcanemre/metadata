package com.umurcanemre.services.metadata.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.umurcanemre.services.metadata.domain.Language;
import com.umurcanemre.services.metadata.service.LanguageService;

import io.lettuce.core.KeyValue;
import io.lettuce.core.api.sync.RedisCommands;

@Service
public class LanguageServiceImpl implements LanguageService {
	@Autowired
	@Qualifier(value = "languageConnection")
	private RedisCommands<String, String> redis;
	private static final String SET_KEY = "language";
	private static final String HASH_KEY_PREFIX = "lang_";

	@Override
	public Map<String, List<KeyValue<String, String>>> get(String code) {
		Map<String, List<KeyValue<String, String>>> map = new HashMap<>();
		Set<String> codes = StringUtils.isNoneBlank(code) ? Sets.newHashSet(code) : getAllCodes();

		codes.forEach(c -> map.put(c, getOne(c)));

		return map;
	}

	private List<KeyValue<String, String>> getOne(String code) {
		return redis.hmget(HASH_KEY_PREFIX + code, Language.getFields());
	}

	@Override
	public Set<String> getAllCodes() {
		return redis.smembers(SET_KEY);
	}

	@Override
	public void putLanguage(Language language) {
		redis.sadd(SET_KEY, language.getCode());
		redis.hmset(HASH_KEY_PREFIX + language.getCode(), language.toMap());
	}

	@Override
	public void removeLanguage(String code) {
		redis.srem(SET_KEY, code);
		redis.del(HASH_KEY_PREFIX + code);
	}

}
