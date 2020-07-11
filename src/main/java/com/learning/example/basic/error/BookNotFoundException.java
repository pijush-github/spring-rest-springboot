package com.learning.example.basic.error;

public class BookNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5040520494545929581L;

	public BookNotFoundException(Long id) {
        super("Book id not found : " + id);
    }
}
