package music.festival.controller.error;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public Map<String, String> NoSuchElementException (NoSuchElementException ex) {
		
		Map<String,String> error = new HashMap<String,String>();
		  error.put("message", ex.toString());
		return error;
		
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(code=HttpStatus.CONFLICT)
	public Map<String, String> SQLIntegrityConstraintViolationException (SQLIntegrityConstraintViolationException ex) {
		
		Map<String,String> error = new HashMap<String,String>();
		  error.put("message", "Artist already in the database, enter a different artist");
		return error;
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code=HttpStatus.CONFLICT)
	public Map<String, String> IllegalArgumentException (IllegalArgumentException ex) {
		
		Map<String,String> error = new HashMap<String,String>();
		  error.put("message", ex.toString());
		return error;
		
	}

}
