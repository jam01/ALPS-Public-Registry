package com.jam01.apr;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.jam01.alps.application.AlpsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Collections;

@SpringBootApplication
public class PublicRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicRegistryApplication.class, args);
	}

	@Bean
	AlpsService alpsService() {
		return new AlpsService();
	}

	@Bean
	Module jaxbAnnotationModule() {
		return new JaxbAnnotationModule();
	}

	@Bean
	public MappingJackson2HttpMessageConverter addAlpsConverter(AlpsService alpsService) {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setSupportedMediaTypes(Collections.singletonList(new MediaType("application", "alps+json")));
		jsonConverter.setObjectMapper(alpsService.getJsonMapper());

		return jsonConverter;
	}
}
