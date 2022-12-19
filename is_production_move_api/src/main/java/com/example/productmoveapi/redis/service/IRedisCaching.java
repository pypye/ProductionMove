package com.example.productmoveapi.redis.service;

import java.util.List;

/**
 * @author Binh Nguyen Thai at 11:03 on 05/12/2022
 */
public interface IRedisCaching {

  void addToHash(String key, String hkey, Object hvalue);

  void addToHash(String key, String hkey, Object hvalue, long expiredInSecond);

  void removeFromHash(String key, String hkey);

  Object getFromHash(String key, String hkey);

  void addOpsValue(String key, Object value);

  void addOpsValue(String key, Object value, long expiredInSecond);

  void removeFromOpsValue(String key);

  Object getFromOpsValue(String key);

  List<String> getKeyFromKeyPattern(String keyPattern);
}