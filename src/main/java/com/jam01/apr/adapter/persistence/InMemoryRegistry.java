package com.jam01.apr.adapter.persistence;

import com.jam01.alps.domain.Alps;
import com.jam01.apr.domain.registry.Entry;
import com.jam01.apr.domain.registry.EntryId;
import com.jam01.apr.domain.registry.Registry;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by jam01 on 4/4/17.
 */

@Repository
public class InMemoryRegistry implements Registry {
	private HashMap<EntryId, Entry> entryMap;
	private HashMap<EntryId, Alps> alpsMap;

	public InMemoryRegistry() {
		this.entryMap = new HashMap<>();
		this.alpsMap = new HashMap<>();
	}

	@Override
	public Entry getEntryBy(EntryId id) {
		return entryMap.get(id);
	}

	@Override
	public EntryId save(Entry entry) {
		EntryId entryId = new EntryId(UUID.randomUUID().toString());
		entryMap.put(entryId, entry);

		return entryId;
	}

	@Override
	public Alps getAlpsBy(EntryId id) {
		return alpsMap.get(id);
	}

	@Override
	public void save(EntryId entryId, Alps alps) {
		alpsMap.put(entryId, alps);
	}
}
