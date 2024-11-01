package com.adera.take.home.test.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;

@ControllerAdvice
public class ValidationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
    List<String> errors = new ArrayList<>();

    ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

    Map<String, List<String>> result = new HashMap<>();
    result.put("errors", errors);

    Map<String, String> data = new HashMap<>();
    data.put("status", HttpStatus.PROCESSING.toString());
    data.put("message", errors.toString());
    data.put("data", "");
    JSONObject jsonObject = new JSONObject(data);
    String orgJsonData = jsonObject.toString();

    //return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(orgJsonData, HttpStatus.BAD_REQUEST);
  }
}
