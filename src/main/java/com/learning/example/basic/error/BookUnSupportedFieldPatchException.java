package com.learning.example.basic.error;

import java.util.Set;

public class BookUnSupportedFieldPatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4901516498878930468L;

	public BookUnSupportedFieldPatchException(Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }
}
