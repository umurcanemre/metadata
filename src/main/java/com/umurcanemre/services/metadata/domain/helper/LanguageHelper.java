package com.umurcanemre.services.metadata.domain.helper;

import java.util.HashMap;
import java.util.Map;

import com.umurcanemre.services.metadata.domain.Language;

public abstract class LanguageHelper {

	public static Map<String, String> toMap(Language l) {
		Map<String, String> map = new HashMap<>();

		map.put(Language.CODE, l.getCode());
		map.put(Language.NAME, l.getName());
		map.put(Language.FORMATTEDNAME, l.getFormattedName());

		return map;
	}
}
