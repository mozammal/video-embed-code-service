package com.mozammal.videoembedcodeservice.exception;

public class ErrorMessage {
    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
