package com.example.productmoveapi.redis.service;

/**
 * @author Binh Nguyen Thai at 11:03 on 05/12/2022
 */
public interface IRedisCaching {

  void addOpsValue(String key, Object value, long expiredInSecond);

  void removeFromOpsValue(String key);

  Object getFromOpsValue(String key);

}