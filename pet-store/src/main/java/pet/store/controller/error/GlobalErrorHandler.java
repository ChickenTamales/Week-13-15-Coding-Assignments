package pet.store.controller.error;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import lombok.extern.slf4j.Slf4j;

/*
 * Add the class-level annotations @RestControllerAdvice (from the 
 * org.springframework.web.bind.annotation package). Also add the @Slf4j annotation
 *
 * */

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

/*  Create handler for the NoSuchElementException. Spring will pass in the 
 *  exception that is thrown
 *  
 *  Add a handler method for NoSuchElementException. The handler method should 
 *  specify a response status of 404 (Not Found). It should return Map<String, 
 *  String> and take a NoSuchElementException as a parameter. (Spring will fill in 
 *  the parameter when it calls the method.) 
 * 
 *  Log the error using the SLF4J logger. You do not need to log the stack trace. 
 *  (SLF4J stands for Simple Logging Façade for Java. It implements the Façade Design 
 *  Pattern so that you can write code conforming to the SLF4J API and use any 
 *  underlying logging framework that has an SLF4J bridge. By default, Spring Boot 
 *  uses the Logback logging framework to perform the logging. In this sense, logging 
 *  is similar to JDBC in which you write code conforming to the JDBC API and the 
 *  driver class maps SQL requests to the needs of the specific database.) 
 *  Return a map with a single entry. The key is "message" (with the quotes) and the 
 *  value is the result of calling toString() on the exception parameter. 
 * 
 *  */
	
	@ExceptionHandler(NoSuchElementException.class)/*the class we want to handle*/
	@ResponseStatus(code=HttpStatus.NOT_FOUND)/*change the status that gets 
	returned*/
	public Map<String, String> handleNoSuchElementException(
			NoSuchElementException ex) {
		log.info("Attempting to modify a pet store with an invalid ID", ex);

		Map<String, String> invalidPetStoreId = new HashMap<>();
		invalidPetStoreId.put("message", ex.toString()); 
	    return invalidPetStoreId;
	}		
}
