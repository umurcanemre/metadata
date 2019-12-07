package com.umurcanemre.services.metadata.requestbody.localization;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LocalizationGetRequest extends LocalizationRequest {
	private List<String> labels;
}
