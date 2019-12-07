package com.umurcanemre.services.metadata.requestbody.localization;

import lombok.Data;

@Data
public abstract class LocalizationRequest {
	private String langCode;
}
