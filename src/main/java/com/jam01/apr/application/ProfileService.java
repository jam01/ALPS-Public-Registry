package com.jam01.apr.application;

import com.jam01.apr.domain.profile.Profile;
import com.jam01.apr.domain.profile.ProfileRepository;
import org.springframework.stereotype.Service;

/**
 * Created by jam01 on 4/4/17.
 */
@Service
public class ProfileService {
	private ProfileRepository repo;

	public ProfileService(ProfileRepository repo) {
		this.repo = repo;
	}

	public Profile getProfileBy(String id) {
		return repo.getBy(id);
	}

	public Profile addProfile(Profile profile) {
		return repo.save(profile);
	}
}