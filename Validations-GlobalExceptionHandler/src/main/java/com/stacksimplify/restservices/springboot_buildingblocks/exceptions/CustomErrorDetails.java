package com.stacksimplify.restservices.springboot_buildingblocks.exceptions;

import java.util.Date;

// Simple custom error details bean
// this class is a simple bean that will be handled in CustomGlobalExceptionHandler class
public class CustomErrorDetails {

    private Date timestamp;
    private String message;
    private String errorDetails;

    public CustomErrorDetails(Date timestamp, String message, String errorDetails) {
        this.timestamp = timestamp;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    @Override
    public String toString() {
        return "CustomErrorDetails{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", errorDetails='" + errorDetails + '\'' +
                '}';
    }
}
