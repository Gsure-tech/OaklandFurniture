package com.decagon.OakLandv1be.exceptions;

public class NoResourceFoundException extends RuntimeException{
    private String notifMessage;

    public NoResourceFoundException(String message) {
        super(message);
    }

    public NoResourceFoundException(String message, String notifMessage) {
        super(message);
        this.notifMessage = notifMessage;
    }
}
