package com.jam01.apr.adapter.web;

import com.jam01.alps.application.dto.AlpsDTO;
import com.jam01.apr.adapter.web.dto.ProfileDTO;
import com.jam01.alps.application.dto.WrappedAlpsDTO;
import com.jam01.apr.application.ProfileService;
import com.jam01.apr.domain.profile.Profile;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by jam01 on 4/4/17.
 */

@RestController
public class ProfileController {
	private ProfileService profileService;

	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/api/profile/{id}/alps",
			produces = {"application/alps+xml"}
	)
	public AlpsDTO getXmlAlpsOf(@PathVariable String id) {
		return ProfileDTO.mappedFrom(profileService.getProfileBy(id)).alps;
	}


	@RequestMapping(
			method = RequestMethod.GET,
			value = "/api/profile/{id}/alps",
			produces = {"application/alps+json"}
	)
	public WrappedAlpsDTO getJsonAlpsOf(@PathVariable String id) {
		return new WrappedAlpsDTO(ProfileDTO.mappedFrom(profileService.getProfileBy(id)).alps);
	}


	@RequestMapping(
			method = RequestMethod.GET,
			value = "/api/profile/{id}",
			produces = {"application/hal+json", "application/hal+xml"}
	)
	public ProfileDTO getProfileOf(@PathVariable String id) {
		return ProfileDTO.mappedFrom(profileService.getProfileBy(id));
	}


	@RequestMapping(
			method = RequestMethod.POST,
			value = "/api/profile",
			consumes = {"application/json", "application/xml"},
			produces = {"application/hal+json", "application/hal+xml"}
	)
	public ProfileDTO addProfile(@RequestBody ProfileDTO profileDTO) {
		Profile toAdd = ProfileDTO.mapFrom(profileDTO);
		Profile afterAdding = profileService.addProfile(toAdd);

		ProfileDTO toReturn = ProfileDTO.mappedFrom(afterAdding);
		toReturn.add(linkTo(methodOn(ProfileController.class).getProfileOf(toReturn.id)).withSelfRel());

		return toReturn;
	}

}