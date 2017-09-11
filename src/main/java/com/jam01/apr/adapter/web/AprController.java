package com.jam01.apr.adapter.web;

import com.jam01.alps.application.representation.AlpsRepresentation;
import com.jam01.alps.domain.Alps;
import com.jam01.alps.domain.exception.AlpsValidationException;
import com.jam01.apr.adapter.web.exception.ResourceNotFoundException;
import com.jam01.apr.adapter.web.representation.AprRepresentation;
import com.jam01.apr.adapter.web.representation.EntryRepresentation;
import com.jam01.apr.adapter.web.representation.SearchResultRepresentation;
import com.jam01.apr.application.RegistryService;
import com.jam01.apr.domain.registry.Entry;
import com.jam01.apr.exception.EntryDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
			method = RequestMethod.GET,
			value = "/apr",
			produces = {"application/hal+json", "application/hal+xml"}
	)
	public AprRepresentation getApr() {
		AprRepresentation toReturn = new AprRepresentation();

		toReturn.add(linkTo(methodOn(AprController.class).addEntry(null)).withRel("addEntry"));
		toReturn.add(linkTo(methodOn(AprController.class).updateEntry(null, null)).withRel("updateEntry"));
		toReturn.add(linkTo(methodOn(AprController.class).searchEntries(null, null)).withRel("searchEntries"));

		return toReturn;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/apr/search",
			produces = {"application/hal+json", "application/hal+xml"}
	)
	public SearchResultRepresentation searchEntries(String title, String[] keywords) {
		return new SearchResultRepresentation();
	}

	@RequestMapping(
			method = RequestMethod.POST,
			value = "/apr/",
			consumes = {"application/json", "application/xml"},
			produces = {"application/hal+json", "application/hal+xml"}
	)
	public EntryRepresentation addEntry(@RequestBody EntryRepresentation representation) {
		Entry addedEntry = registryService.add(representation);

		representation.add(linkTo(methodOn(AprController.class).getEntryOf(addedEntry.getId().id)).withSelfRel());
		if (registryService.getAlpsById(addedEntry.getId().id) != null)
			representation.add(linkTo(methodOn(AprController.class).getAlpsOf(addedEntry.getId().id)).withRel("getAlps"));
		representation.add(linkTo(methodOn(AprController.class).putAlpsOf(addedEntry.getId().id, null)).withRel("putAlpsOf"));

		return representation;
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			value = "/apr/{id}",
			consumes = {"application/json", "application/xml"},
			produces = {"application/hal+json", "application/hal+xml"}
	)
	public EntryRepresentation updateEntry(@PathVariable String id, @RequestBody EntryRepresentation representation) {
		Entry addedEntry = registryService.update(id, representation);

		representation.add(linkTo(methodOn(AprController.class).getEntryOf(addedEntry.getId().id)).withSelfRel());
		if (registryService.getAlpsById(addedEntry.getId().id) != null)
			representation.add(linkTo(methodOn(AprController.class).getAlpsOf(addedEntry.getId().id)).withRel("getAlps"));
		representation.add(linkTo(methodOn(AprController.class).putAlpsOf(addedEntry.getId().id, null)).withRel("putAlpsOf"));

		return representation;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/apr/{id}",
			produces = {"application/hal+json", "application/hal+xml"}
	)
	public EntryRepresentation getEntryOf(@PathVariable String id) {
		EntryRepresentation toReturn = EntryRepresentation.mappedFrom(registryService.getById(id));
		if (toReturn == null)
			throw new ResourceNotFoundException();

		toReturn.add(linkTo(methodOn(AprController.class).getEntryOf(id)).withSelfRel());
		if (registryService.getAlpsById(id) != null)
			toReturn.add(linkTo(methodOn(AprController.class).getAlpsOf(id)).withRel("getAlps"));
		toReturn.add(linkTo(methodOn(AprController.class).putAlpsOf(id, null)).withRel("putAlpsOf"));

		return toReturn;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/apr/{id}/alps",
			produces = {"application/alps+json", "application/alps+xml"}//, "application/json", "application/xml"}
	)
	public AlpsRepresentation getAlpsOf(@PathVariable String id) {
		Alps toReturn = registryService.getAlpsById(id);
		if (toReturn == null)
			throw new ResourceNotFoundException();

		return AlpsRepresentation.mapFrom(toReturn);
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			value = "/apr/{id}/alps",
			consumes = {"application/alps+json", "application/alps+xml"},
			produces = {"application/alps+json", "application/alps+xml"}//, "application/json", "application/xml"}
	)
	public AlpsRepresentation putAlpsOf(@PathVariable String id, @RequestBody AlpsRepresentation representation) {
		registryService.putAlps(id, representation);
		return (AlpsRepresentation.mapFrom(registryService.getAlpsById(id)));
	}

	// See: https://github.com/spring-projects/spring-boot/issues/1677#issuecomment-59021756
	@ExceptionHandler(AlpsValidationException.class)
	void handleAlpsValidationException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler({ResourceNotFoundException.class, EntryDoesNotExistException.class})
	void handleNotFonundException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

}