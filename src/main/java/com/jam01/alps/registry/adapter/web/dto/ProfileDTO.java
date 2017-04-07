package com.jam01.alps.registry.adapter.web.dto;

import com.jam01.alps.registry.domain.profile.Profile;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by jam01 on 4/5/17.
 */

@XmlRootElement(name = "profile")
public class ProfileDTO extends ResourceSupport {
	public List<String> tags;
	public String title;
	public AlpsDTO alps;
	//	public List<Link> links;
	public String id;

	public static ProfileDTO mappedFrom(Profile profile) {
		ProfileDTO toReturn = new ProfileDTO();
		if (profile != null) {
			toReturn.alps = AlpsDTO.mapFrom(profile.getAlps());
			toReturn.tags = profile.getTags();
			toReturn.title = profile.getTitle();
			toReturn.id = profile.getId();
//		toReturn.links = new ArrayList<>();
		}

		return toReturn;
	}

	public static Profile mapFrom(ProfileDTO profileDTO) {
		Profile toReturn = new Profile(profileDTO.title);
		toReturn.setAlps(AlpsDTO.mapFrom(profileDTO.alps));
		toReturn.setTags(profileDTO.tags);

		return toReturn;
	}
}
