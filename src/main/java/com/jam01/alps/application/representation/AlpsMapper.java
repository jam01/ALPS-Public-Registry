package com.jam01.alps.application.representation;

import com.jam01.alps.domain.Alps;

import java.util.stream.Collectors;

/**
 * Created by jam01 on 4/7/17.
 */
public class AlpsMapper {
	public static Alps mapFrom(AlpsRepresentation alpsRepresentation) {

		return new Alps(alpsRepresentation.version,
				DocRepresentation.mapFrom(alpsRepresentation.doc),
				alpsRepresentation.descriptor
						.stream()
						.map(DescriptorMapper::generateMatrix)
						.collect(Collectors.toList()));
	}

	public static AlpsRepresentation mapFrom(Alps alps) {
		AlpsRepresentation toReturn = new AlpsRepresentation();
		toReturn.version = alps.getVersion();
		toReturn.doc = DocRepresentation.mapFrom(alps.getDoc());
		toReturn.descriptor = DescriptorMapper.mappedFrom(alps.getRoots());
//		toReturn.link = LinkRepresentation.mapFrom(alps.getLink());

		return toReturn;
	}
}
