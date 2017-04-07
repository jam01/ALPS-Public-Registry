package com.jam01.alps.registry.adapter.web.dto;

import com.jam01.alps.registry.domain.profile.Descriptor;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by jam01 on 4/7/17.
 */
public class DescriptorGraph {
	List<Descriptor> descriptor;
	Map<Descriptor, Descriptor> childRelations;
	Map<URI, Descriptor> superRelations;
	Map<URI, List<Descriptor>> rtRelations;
}
