package com.jam01.apr.adapter.web;

import com.jam01.apr.adapter.web.representation.EntryRepresentation;
import com.jam01.apr.application.RegistryService;
import com.jam01.apr.domain.registry.Entry;
import com.jam01.apr.domain.registry.EntryId;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by jam01 on 4/4/17.
 */

@RestController
public class EntryController {
	private RegistryService registryService;

	public EntryController(RegistryService registryService) {
		this.registryService = registryService;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/api/entry/{id}",
			produces = {"application/hal+json", "application/hal+xml"}
	)
	public EntryRepresentation getEntryOf(@PathVariable String id) {
		EntryRepresentation toReturn = EntryRepresentation.mappedFrom(registryService.getById(id));
		toReturn.add(linkTo(methodOn(EntryController.class).getEntryOf(id)).withSelfRel());
		toReturn.add(linkTo(methodOn(AlpsController.class).getAlpsOf(id)).withRel("getAlps"));
//		toReturn.add(linkTo(methodOn(AlpsController.class).putJsonAlps(id, null)).withRel("putAlps"));

		return toReturn;
	}


	@RequestMapping(
			method = RequestMethod.POST,
			value = "/api/entry",
			consumes = {"application/json", "application/xml"},
			produces = {"application/hal+json", "application/hal+xml"}
	)
	public EntryRepresentation addEntry(@RequestBody EntryRepresentation representation) {
		Entry toAdd = EntryRepresentation.mapFrom(representation);
		EntryId addedEntryId = registryService.add(toAdd);

		EntryRepresentation toReturn = EntryRepresentation.mappedFrom(toAdd);
		toReturn.add(linkTo(methodOn(EntryController.class).getEntryOf(addedEntryId.id)).withSelfRel());
		toReturn.add(linkTo(methodOn(AlpsController.class).getAlpsOf(addedEntryId.id)).withRel("getAlps"));
//		toReturn.add(linkTo(methodOn(AlpsController.class).putJsonAlps(addedEntryId.id, null)).withRel("putAlps"));

		return toReturn;
	}
}