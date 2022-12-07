package com.example.productmoveapi.interceptor;

/**
 * @author Binh Nguyen Thai at 22:40 on 28/11/2022
 */

import com.example.productmoveapi.exception.CustomBussinessLogicException;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.response.ResponseFactory;
import com.example.productmoveapi.response.ResponseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public ResponseEntity<GeneralResponse<Object>> defaultExceptionHandler(Exception e) {
    log.error(e.getMessage(), e);
    return ResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR,
        ResponseStatusEnum.UNKNOWN_ERROR);
  }

  @ExceptionHandler(value = MissingServletRequestParameterException.class)
  @ResponseBody
  public ResponseEntity<GeneralResponse<Object>> missingRequestParamHandler(Exception e) {
    log.error(e.getMessage(), e);
    return ResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR,
        ResponseStatusEnum.NOT_ENOUGH_PARAM);
  }

  @ExceptionHandler(value = CustomBussinessLogicException.class)
  @ResponseBody
  public ResponseEntity<GeneralResponse<Object>> businessExceptionHandler(
      CustomBussinessLogicException e) {
    log.error(e.getMessage(), e);
    return ResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getResponseStatusEnum());
  }
}
