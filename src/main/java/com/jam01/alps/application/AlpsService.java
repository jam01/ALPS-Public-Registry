package com.jam01.alps.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.jam01.alps.application.representation.AlpsMapper;
import com.jam01.alps.application.representation.AlpsRepresentation;
import com.jam01.alps.domain.Alps;

import java.io.IOException;

/**
 * Created by jam01 on 4/9/17.
 */
public class AlpsService {
	private final ObjectMapper jsonMapper;
	private final XmlMapper xmlMapper;

	public AlpsService() {
		jsonMapper = new ObjectMapper();
		jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		jsonMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		/**
		 * Currently there's no way to selectively wrap json objects in a root element.
		 * See: https://github.com/FasterXML/jackson-annotations/issues/33
		 * This works around that.
		 */
		jsonMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		jsonMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

		xmlMapper = new XmlMapper();
		xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		xmlMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	}

	public ObjectMapper getJsonMapper() {
		return jsonMapper;
	}

	public String generate(Alps alps, DataType type) {
		AlpsRepresentation representation = AlpsMapper.mapFrom(alps);
		try {
			return type == DataType.JSON ? jsonMapper.writeValueAsString(representation)
					: xmlMapper.writeValueAsString(representation);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public Alps parse(String alps, DataType type) {
		try {
			return type == DataType.JSON ? AlpsMapper.mapFrom(jsonMapper.readValue(alps, AlpsRepresentation.class))
					: AlpsMapper.mapFrom(xmlMapper.readValue(alps, AlpsRepresentation.class));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public AlpsRepresentation parseRepresentation(String alpsRepresentation, DataType type) {
		try {
			return type == DataType.JSON ? jsonMapper.readValue(alpsRepresentation, AlpsRepresentation.class)
					: xmlMapper.readValue(alpsRepresentation, AlpsRepresentation.class);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}


	public String generateRepresentation(AlpsRepresentation alps, DataType type) {
		try {
			return type == DataType.JSON ? jsonMapper.writeValueAsString(alps) : xmlMapper.writeValueAsString(alps);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public enum DataType {
		JSON, XML
	}
}
