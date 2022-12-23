package com.example.productmoveapi.interceptor;

import com.example.productmoveapi.exception.CustomBussinessLogicException;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Binh Nguyen Thai at 22:40 on 28/11/2022
 */


@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public ResponseEntity<GeneralResponse<Object>> defaultExceptionHandler(Exception e) {
//    log.error(e.getMessage(), e);
    return ResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR,
        ResponseStatusEnum.UNKNOWN_ERROR);
  }

  @ExceptionHandler(value = MissingServletRequestParameterException.class)
  @ResponseBody
  public ResponseEntity<GeneralResponse<Object>> missingRequestParamHandler(Exception e) {
//    log.error(e.getMessage(), e);
    return ResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR,
        ResponseStatusEnum.NOT_ENOUGH_PARAM);
  }

  @ExceptionHandler(value = CustomBussinessLogicException.class)
  @ResponseBody
  public ResponseEntity<GeneralResponse<Object>> businessExceptionHandler(
      CustomBussinessLogicException e) {
//    log.error(e.getMessage(), e);
    return ResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getResponseStatusEnum());
  }

  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GeneralResponse<Object>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ResponseFactory.error(HttpStatus.BAD_REQUEST, ResponseStatusEnum.INVALID_REQUEST_PARAM,
        errors);
  }
}
