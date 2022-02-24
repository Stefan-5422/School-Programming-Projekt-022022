package com.yes.yes.utils.exceptions;

public class ChunkAlreadyExistsException extends Exception {
    public ChunkAlreadyExistsException() {
    }

    public ChunkAlreadyExistsException(String message) {
        super(message);
    }

    public ChunkAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChunkAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ChunkAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
