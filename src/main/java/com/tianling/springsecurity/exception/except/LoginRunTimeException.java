package com.tianling.springsecurity.exception.except;

import lombok.NoArgsConstructor;

/**
 * @author TianLing
 * Date 2020/6/4 15:47
 * Description
 */

public class LoginRunTimeException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public LoginRunTimeException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public LoginRunTimeException(String message) {
        super(message);
    }
}
