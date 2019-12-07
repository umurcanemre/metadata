package com.umurcanemre.services.metadata.serviceimpl;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.umurcanemre.services.metadata.domain.Language;
import com.umurcanemre.services.metadata.service.LanguageService;

import io.lettuce.core.KeyValue;
import io.lettuce.core.api.sync.RedisCommands;

@Service
public class LanguageServiceImpl implements LanguageService {
	@Autowired
	@Qualifier(value = "languageConnection")
	private RedisCommands<String, String> redis;
	private static final String SET_KEY = "languages";
	private static final String HASH_KEY_PREFIX = "lang_";

	@Override
	public Map<String,Map<String, String>> get(Set<String> codes) {
		Map<String, Map<String, String>> map = new HashMap<>();
		codes = CollectionUtils.isEmpty(codes) ? getAllCodes() : codes;

		codes.forEach(c -> map.put(c, getOne(c)));

		return map;
	}

	private Map<String, String> getOne(String code) {
		List<KeyValue<String, String>> dbResult = redis.hmget(HASH_KEY_PREFIX + code, Language.getFields());
		Map<String, String> result = new HashMap<>();
		
		dbResult.stream().filter(KeyValue::hasValue).forEach(r-> result.put(r.getKey(), r.getValue()));
		
		return result;
	}

	@Override
	public Set<String> getAllCodes() {
		return redis.smembers(SET_KEY);
	}

	@Override
	public void putLanguage(Language language) {
		if(language.getCode().equals(SET_KEY)) {
			throw new InvalidParameterException(SET_KEY + "cannot be a language code");
		}
		redis.sadd(SET_KEY, language.getCode());
		redis.hmset(HASH_KEY_PREFIX + language.getCode(), language.toMap());
	}

	@Override
	public void removeLanguage(String code) {
		if(code.equals(SET_KEY)) {
			throw new InvalidParameterException(SET_KEY + "cannot be a language code");
		}
		redis.srem(SET_KEY, code);
		redis.del(HASH_KEY_PREFIX + code);
	}

}
