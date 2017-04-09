package com.jam01.apr.adapter.web.dto;

import com.jam01.alps.application.dto.AlpsDTO;
import com.jam01.alps.application.dto.AlpsMapper;
import com.jam01.apr.domain.profile.Profile;
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
	public String id;

	public static ProfileDTO mappedFrom(Profile profile) {
		ProfileDTO toReturn = new ProfileDTO();
		if (profile != null) {
			toReturn.alps = AlpsMapper.mapFrom(profile.getAlps());
			toReturn.tags = profile.getTags();
			toReturn.title = profile.getTitle();
			toReturn.id = profile.getId();
		}

		return toReturn;
	}

	public static Profile mapFrom(ProfileDTO profileDTO) {
		Profile toReturn = new Profile(profileDTO.title);
		toReturn.setAlps(AlpsMapper.mapFrom(profileDTO.alps));
		toReturn.setTags(profileDTO.tags);

		return toReturn;
	}
}
