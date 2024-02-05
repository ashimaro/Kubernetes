package com.assessment.SpringBootMicroservicesContainerization.exception;
import org.springframework.http.HttpStatus;
public class CustomErrorException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatus;
    private final String statusCode;

	public CustomErrorException(HttpStatus httpStatus, String statusCode, String message){
        super(message);
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

}

