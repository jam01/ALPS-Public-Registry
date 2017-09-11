package com.jam01.apr.exception;

import com.jam01.apr.domain.registry.EntryId;

/**
 * Created by jam01 on 9/10/17.
 */
public class EntryDoesNotExistException extends RuntimeException {
	public EntryDoesNotExistException(EntryId entryId) {
		super(entryId.id + "does not exist in the registry.");
	}
}
