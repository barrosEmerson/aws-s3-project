package com.example.barrostech.awss3upload.domain.exception;

public class StorageCloudException extends RuntimeException {
    public StorageCloudException(String message) {
        super(message);
    }

    public StorageCloudException(String message, Throwable cause) {
        super(message, cause);
    }
}