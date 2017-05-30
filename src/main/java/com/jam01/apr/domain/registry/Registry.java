package com.jam01.apr.domain.registry;

import com.jam01.alps.domain.Alps;

/**
 * Created by jam01 on 4/4/17.
 */

public interface Registry {
	Entry getEntryBy(EntryId id);

	EntryId save(Entry entry);

	Alps getAlpsBy(EntryId id);

	void save(EntryId entryId, Alps alps);
}
