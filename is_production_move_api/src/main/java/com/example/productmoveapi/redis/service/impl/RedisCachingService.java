package com.example.productmoveapi.redis.service.impl;

import com.example.productmoveapi.redis.service.IRedisCaching;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Binh Nguyen Thai at 11:03 on 05/12/2022
 */
@Service
public class RedisCachingService implements IRedisCaching {

  private final RedisTemplate<String, Object> redisTemplate;

  public RedisCachingService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }


  public void addOpsValue(String key, Object value, long expiredInSecond) {
    redisTemplate.opsForValue().set(key, value);
    redisTemplate.expire(key, expiredInSecond, TimeUnit.SECONDS);
  }

  public void removeFromOpsValue(String key) {
    redisTemplate.delete(key);
  }

  public Object getFromOpsValue(String key) {
    return redisTemplate.opsForValue().get(key);
  }

}