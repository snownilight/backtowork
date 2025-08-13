package com.snownilight.backtowork.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle all exceptions
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse<String> handleException(Exception e) {
        return ApiResponse.error(500, e.getMessage());
    }

    /**
     * Handle custom exceptions
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ApiResponse<String> handleCustomException(IllegalArgumentException e) {
        return ApiResponse.error(400, e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ApiResponse<String> handleNoHandlerFound(NoHandlerFoundException ex) {
        return ApiResponse.error(404, "404 Not Found: " + ex.getRequestURL());
    }
}
