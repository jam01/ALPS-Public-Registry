package com.jam01.apr.adapter.persistence;

import com.jam01.alps.application.AlpsMapper;
import com.jam01.alps.domain.Alps;
import com.jam01.apr.domain.registry.Entry;
import com.jam01.apr.domain.registry.EntryId;
import com.jam01.apr.domain.registry.Registry;
import com.jam01.apr.exception.EntryDoesNotExistException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

		Entry aprEntry = new Entry("ALPS Public Registry", new ArrayList<>(Arrays.asList("meta", "alps", "apr")));
		aprEntry.setId(new EntryId("0"));
		entryMap.put(aprEntry.getId(), aprEntry);

		AlpsMapper mapper = new AlpsMapper();
		try {
			saveAlps(aprEntry.getId(),
					mapper.readValue(new String(FileCopyUtils.copyToByteArray(new ClassPathResource("apr.xml").getInputStream())),
							AlpsMapper.DataType.XML));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public Entry getEntryBy(EntryId id) {
		return entryMap.get(id);
	}

	@Override
	public Entry save(Entry entry) {
		EntryId entryId = new EntryId(UUID.randomUUID().toString());
		entryMap.put(entryId, entry);
		entry.setId(entryId);

		return entry;
	}

	@Override
	public Entry update(Entry entry) {
		entryMap.put(entry.getId(), entry);
		return entry;
	}

	@Override
	public Alps getAlpsBy(EntryId id) {
		return alpsMap.get(id);
	}

	@Override
	public Alps saveAlps(EntryId entryId, Alps alps) {
		if (entryMap.get(entryId) == null)
			throw new EntryDoesNotExistException(entryId);

		alpsMap.put(entryId, alps);

		return alps;
	}
}
