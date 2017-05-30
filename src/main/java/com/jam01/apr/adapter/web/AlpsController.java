package com.jam01.apr.adapter.web;

import com.jam01.alps.application.AlpsService;
import com.jam01.alps.application.representation.AlpsMapper;
import com.jam01.alps.application.representation.AlpsRepresentation;
import com.jam01.apr.application.RegistryService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jam01 on 5/29/17.
 */
@RestController
public class AlpsController {
	private RegistryService registryService;
	private AlpsService alpsService;

	public AlpsController(RegistryService registryService, AlpsService alpsService) {
		this.registryService = registryService;
		this.alpsService = alpsService;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/api/entry/{id}/alps",
			produces = {"application/alps+xml", "application/alps+json"}
	)
	public AlpsRepresentation getAlpsOf(@PathVariable String id) {
		return (AlpsMapper.mapFrom(registryService.getAlpsById(id)));
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			value = "/api/entry/{id}/alps",
			consumes = {"application/alps+json", "application/alps+xml"},
			produces = {"application/alps+json", "application/alps+xml"}
	)
	public AlpsRepresentation putAlps(@PathVariable String id, @RequestBody AlpsRepresentation representation) {
		registryService.add(id, representation);
		return (AlpsMapper.mapFrom(registryService.getAlpsById(id)));
	}
}