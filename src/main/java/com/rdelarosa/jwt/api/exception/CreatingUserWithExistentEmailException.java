package com.rdelarosa.jwt.api.exception;

public class CreatingUserWithExistentEmailException extends RuntimeException {

    public CreatingUserWithExistentEmailException() {
    }

    public CreatingUserWithExistentEmailException(String message) {
        super(message);
    }

    public CreatingUserWithExistentEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreatingUserWithExistentEmailException(Throwable cause) {
        super(cause);
    }
}
