package com.jam01.apr.adapter.web;

import com.jam01.alps.application.representation.AlpsRepresentation;
import com.jam01.apr.adapter.web.representation.EntryRepresentation;
import com.jam01.apr.application.RegistryService;
import com.jam01.apr.domain.registry.Entry;
import com.jam01.apr.domain.registry.EntryId;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by jam01 on 5/29/17.
 */
@RestController
public class AprController {
	private RegistryService registryService;

	public AprController(RegistryService registryService) {
		this.registryService = registryService;
	}

	@RequestMapping(
			method = RequestMethod.POST,
			value = "/api/",
			consumes = {"application/json", "application/xml"},
			produces = {"application/hal+json", "application/hal+xml"}
	)
	public EntryRepresentation addEntry(@RequestBody EntryRepresentation representation) {
		Entry toAdd = EntryRepresentation.mapFrom(representation);
		EntryId addedEntryId = registryService.add(toAdd);

		EntryRepresentation toReturn = EntryRepresentation.mappedFrom(toAdd);
		toReturn.add(linkTo(methodOn(AprController.class).getEntryOf(addedEntryId.id)).withSelfRel());
		toReturn.add(linkTo(methodOn(AprController.class).getAlpsOf(addedEntryId.id)).withRel("getAlps"));
		toReturn.add(linkTo(methodOn(AprController.class).putAlps(addedEntryId.id, null)).withRel("putAlps"));

		return toReturn;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/api/{id}",
			produces = {"application/hal+json", "application/hal+xml"}
	)
	public EntryRepresentation getEntryOf(@PathVariable String id) {
		EntryRepresentation toReturn = EntryRepresentation.mappedFrom(registryService.getById(id));
		toReturn.add(linkTo(methodOn(AprController.class).getEntryOf(id)).withSelfRel());
		toReturn.add(linkTo(methodOn(AprController.class).getAlpsOf(id)).withRel("getAlps"));
		toReturn.add(linkTo(methodOn(AprController.class).putAlps(id, null)).withRel("putAlps"));

		return toReturn;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/api/{id}/alps",
			produces = {"application/alps+xml", "application/alps+json"}
	)
	public AlpsRepresentation getAlpsOf(@PathVariable String id) {
		return (AlpsRepresentation.mapFrom(registryService.getAlpsById(id)));
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			value = "/api/{id}/alps",
			consumes = {"application/alps+json", "application/alps+xml"},
			produces = {"application/alps+json", "application/alps+xml"}
	)
	public AlpsRepresentation putAlps(@PathVariable String id, @RequestBody AlpsRepresentation representation) {
		registryService.add(id, representation);
		return (AlpsRepresentation.mapFrom(registryService.getAlpsById(id)));
	}
}