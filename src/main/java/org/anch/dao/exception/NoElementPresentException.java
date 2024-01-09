package org.anch.dao.exception;

public class NoElementPresentException extends RuntimeException {

    public NoElementPresentException() {
    }

    public NoElementPresentException(String message) {
        super(message);
    }

    public NoElementPresentException(String message, Exception cause) {
        super(message, cause);
    }

}
