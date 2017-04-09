package com.jam01.alps.application.dto;

import com.jam01.alps.domain.Alps;

import java.util.stream.Collectors;

/**
 * Created by jam01 on 4/7/17.
 */
public class AlpsMapper {
	public static Alps mapFrom(AlpsDTO alpsDTO) {
		return new Alps(alpsDTO.version,
				DocDTO.mapFrom(alpsDTO.doc),
				alpsDTO.descriptor
						.stream()
						.map(DescriptorMapper::generateMatrix)
						.collect(Collectors.toList()));
	}

	public static AlpsDTO mapFrom(Alps alps) {
		AlpsDTO toReturn = new AlpsDTO();
		toReturn.version = alps.getVersion();
		toReturn.doc = DocDTO.mapFrom(alps.getDoc());
		toReturn.descriptor = DescriptorMapper.mappedFrom(alps.getRoots());
		toReturn.link = LinkDTO.mapFrom(alps.getLink());

		return toReturn;
	}
}
