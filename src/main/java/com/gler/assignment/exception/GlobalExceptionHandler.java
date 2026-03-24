package com.gler.assignment.exception;

import com.gler.assignment.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleUpstreamError(
            RuntimeException ex,
            HttpServletRequest request
    ) {

        ErrorResponse error = new ErrorResponse(
                502,
                "Upstream API Unreachable",
                "Connection to the upstream is unreachable",
                request.getRequestURI()
        );

        return ResponseEntity.status(502).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericError(
            Exception ex,
            HttpServletRequest request
    ) {

        ErrorResponse error = new ErrorResponse(
                500,
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(500).body(error);
    }
}