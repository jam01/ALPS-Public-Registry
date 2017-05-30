package com.jam01.apr.application;

import com.jam01.alps.application.representation.AlpsMapper;
import com.jam01.alps.application.representation.AlpsRepresentation;
import com.jam01.alps.domain.Alps;
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

	public EntryId add(Entry entry) {
		return registry.save(entry);
	}

	public Alps getAlpsById(String id) {
		return registry.getAlpsBy(new EntryId(id));
	}

	public void add(String id, Alps alps) {
		registry.save(new EntryId(id), alps);
	}

	public void add(String id, AlpsRepresentation representation) {
		registry.save(new EntryId(id), AlpsMapper.mapFrom(representation));
	}
}