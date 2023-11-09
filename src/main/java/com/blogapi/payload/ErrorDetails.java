package com.blogapi.payload;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp; //ToDo: timestamp it will tell you the time when the error or exception occurred.
    private String message;//ToDo: message it will tell you the error or exception occurred.
    private String details;//ToDo: details will tell you the details of the error or exception occurred.

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
    public String getDetails() {
        return details;
    }
}
