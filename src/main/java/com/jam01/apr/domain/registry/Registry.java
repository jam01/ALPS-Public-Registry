package com.jam01.apr.domain.registry;

import com.jam01.alps.domain.Alps;

/**
 * Created by jam01 on 4/4/17.
 */

public interface Registry {
	Entry getEntryBy(EntryId id);

	Entry save(Entry entry);

	Entry update(Entry entry);

	Alps getAlpsBy(EntryId id);

	Alps saveAlps(EntryId entryId, Alps alps);
}
