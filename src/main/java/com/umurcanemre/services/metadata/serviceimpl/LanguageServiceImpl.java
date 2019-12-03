package com.umurcanemre.services.metadata.serviceimpl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.umurcanemre.services.metadata.domain.Language;
import com.umurcanemre.services.metadata.service.LanguageService;

import lombok.Data;

@Service
@Data
public class LanguageServiceImpl implements LanguageService {
	
	@Override
	public Set<Language> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putLanguage(Language language) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLanguage(Language language) {
		// TODO Auto-generated method stub
		
	}

}
