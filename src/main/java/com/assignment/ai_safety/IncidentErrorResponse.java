package com.assignment.ai_safety;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class IncidentErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime timestamp;
    private int statusCode;
    private String path;
    private String message;

    public IncidentErrorResponse(ZonedDateTime timestamp, int statusCode, String path, String message) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.path = path;
        this.message = message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
