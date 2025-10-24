package com.example.TechLearningHub.exception;

public class QiitaApiException extends RuntimeException {
    public QiitaApiException(String message) {
        super(message);
    }

    public QiitaApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
