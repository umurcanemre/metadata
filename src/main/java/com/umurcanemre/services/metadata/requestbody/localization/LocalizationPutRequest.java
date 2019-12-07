package com.umurcanemre.services.metadata.requestbody.localization;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LocalizationPutRequest extends LocalizationRequest {
	Map<String,String> values;
}
