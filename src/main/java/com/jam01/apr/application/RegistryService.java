package com.jam01.apr.application;

import com.jam01.alps.application.representation.AlpsRepresentation;
import com.jam01.alps.domain.Alps;
import com.jam01.apr.adapter.web.representation.EntryRepresentation;
import com.jam01.apr.domain.registry.Entry;
import com.jam01.apr.domain.registry.EntryId;
import com.jam01.apr.domain.registry.Registry;
import org.springframework.stereotype.Service;

/**
 * Created by jam01 on 4/4/17.
 */
@Service
public class RegistryService {
	private Registry registry;

	public RegistryService(Registry registry) {
		this.registry = registry;
	}

	public Entry getById(String id) {
		return registry.getEntryBy(new EntryId(id));
	}

	public Entry add(EntryRepresentation entry) {
		return registry.save(EntryRepresentation.mapFrom(entry));
	}

	public Entry update(String id, EntryRepresentation entry) {
		Entry toAdd = EntryRepresentation.mapFrom(entry);
		toAdd.setId(new EntryId(id));

		return registry.update(toAdd);
	}

	public Alps getAlpsById(String id) {
		return registry.getAlpsBy(new EntryId(id));
	}

	public Alps putAlps(String id, AlpsRepresentation representation) {
		return registry.saveAlps(new EntryId(id), AlpsRepresentation.mapFrom(representation));
	}
}