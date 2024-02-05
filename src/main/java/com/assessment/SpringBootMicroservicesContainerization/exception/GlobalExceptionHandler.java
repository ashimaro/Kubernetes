package com.assessment.SpringBootMicroservicesContainerization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.modelmapper.MappingException;
import org.modelmapper.spi.ErrorMessage;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex, HttpServletRequest request) {

    	log.error("Exception thowrn !!!",ex);
        Map<String, Object> map = new LinkedHashMap<>();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String statusCode = "500";
        String message = ex.getMessage();

        // fine grain control of each exception
        try {
            if (ex instanceof CustomErrorException) {
                CustomErrorException customErrorException = (CustomErrorException) ex;
                httpStatus = customErrorException.getHttpStatus();
                statusCode = customErrorException.getStatusCode();
                message = customErrorException.getMessage();
            } else if (ex instanceof MethodArgumentNotValidException) {
                // trigged when DTO on controller fails validation
                MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
                httpStatus = HttpStatus.BAD_REQUEST;
                statusCode = "100";
                message = e.getFieldErrors().get(0).getDefaultMessage();
            } else if (ex instanceof MappingException) {
                MappingException e = (MappingException) ex;
                ErrorMessage[] errors = e.getErrorMessages().toArray(new ErrorMessage[0]);
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                statusCode = "101";
                message = errors[0].getMessage()
                        + ". Invalid property formatting may caused this. For example '2021-10-09' is valid Date but '2021 - 10 - 09' is not";
            } else {
            	httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            	statusCode = "500";
            	message = ex.getMessage();
            }
            
        } catch (Exception e) {
        	log.error("Failed exception !!!!",e);
            statusCode = "500";
            message = e.getMessage();
        }

        map.put("statusCode", statusCode);
        map.put("message", message);

        return new ResponseEntity<>(map, httpStatus);

    }
}
