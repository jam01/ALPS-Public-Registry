package com.jam01.alps.registry.domain.profile;

/**
 * Created by jam01 on 4/4/17.
 */

public interface ProfileRepository {
	Profile getBy(String id);

	Profile save(Profile profile);
}
