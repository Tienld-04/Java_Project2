package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.customeException.FieldRequiredException;
import com.javaweb.model.ErrorResponeDTO;

@ControllerAdvice //bộ xử lý ngoại lệ toàn cục cho project 
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> handleArithmeticException(
            ArithmeticException ex, WebRequest request) {
		ErrorResponeDTO errorResponeDTO = new   ErrorResponeDTO();
		errorResponeDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Lỗi 5 not / for 0");
		errorResponeDTO.setDetailError(details);
        return new ResponseEntity<>(errorResponeDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<Object> handleFieldRequiredException(
    		FieldRequiredException ex, WebRequest request) {
		ErrorResponeDTO errorResponeDTO = new   ErrorResponeDTO();
		errorResponeDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("check name of num is error");
		errorResponeDTO.setDetailError(details);
        return new ResponseEntity<>(errorResponeDTO, HttpStatus.BAD_GATEWAY);
    }
}
