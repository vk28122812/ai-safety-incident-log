package com.assignment.ai_safety;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class IncidentExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncidentErrorResponse> incidentNotFoundHandler(IncidentNotFoundException e, HttpServletRequest req ) {
        IncidentErrorResponse incidentErrorResponse = new IncidentErrorResponse(ZonedDateTime.now(), HttpStatus.NOT_FOUND.value(),req.getRequestURI(), e.getMessage());
        return new ResponseEntity<>(incidentErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncidentErrorResponse>genericHandler(Exception e, HttpServletRequest req) {
        IncidentErrorResponse incidentErrorResponse = new IncidentErrorResponse(ZonedDateTime.now(), HttpStatus.BAD_REQUEST.value(),req.getRequestURI(), e.getMessage());
        return  new ResponseEntity<>(incidentErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
