package com.example.productmoveapi.response;

/**
 * @author Binh Nguyen Thai at 22:32 on 28/11/2022
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseFactory {

  public static <T> ResponseEntity<GeneralResponse<T>> success(T data) {
    GeneralResponse<T> generalResponse = new GeneralResponse<>();
    generalResponse.setStatus(new GeneralResponseStatus(ResponseStatusEnum.SUCCESS));
    generalResponse.setData(data);
    return ResponseEntity.ok(generalResponse);
  }

  public static <T> ResponseEntity<GeneralResponse<T>> success(GeneralResponse<T> responseObject) {
    return ResponseEntity.ok(responseObject);
  }

  public static ResponseEntity<GeneralResponse<Object>> success() {
    GeneralResponse<Object> responseObject = new GeneralResponse<>();
    responseObject.setStatus(new GeneralResponseStatus(ResponseStatusEnum.SUCCESS));
    return ResponseEntity.ok(responseObject);
  }

  public static ResponseEntity<GeneralResponse<Object>> success(Object data, Class<?> clazz) {
    GeneralResponse<Object> responseObject = new GeneralResponse<>();
    responseObject.setStatus(new GeneralResponseStatus(ResponseStatusEnum.SUCCESS));
    responseObject.setData(clazz.cast(data));
    return ResponseEntity.ok(responseObject);
  }

  public static ResponseEntity<GeneralResponse<Object>> error(HttpStatus httpStatus,
      ResponseStatusEnum responseStatusEnum) {
    return error(httpStatus, responseStatusEnum, null);
  }

  public static ResponseEntity<GeneralResponse<Object>> error(HttpStatus httpStatus,
      ResponseStatusEnum responseStatusEnum, Object data) {
    GeneralResponse<Object> responseObject = new GeneralResponse<>();
    responseObject.setStatus(new GeneralResponseStatus(responseStatusEnum));
    responseObject.setData(data);
    return new ResponseEntity<>(responseObject, httpStatus);
  }

}