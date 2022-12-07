package com.example.productmoveapi.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;

/**
 * @author Binh Nguyen Thai at 11:02 on 05/12/2022
 */

@Configuration
public class RedisConfig {

  @Value("${spring.redis.host}")
  public String redisHost;

  @Value("${spring.redis.port}")
  public int redisPort;

  @Value("${spring.redis.password}")
  public String redisPassword;

  @Bean
  public Jedis initJedis() {
    return new Jedis(redisHost, redisPort);
  }

  @Bean
  RedisConnectionFactory connectionFactory() {
    return new JedisConnectionFactory(configuration());
  }

  @Bean
  RedisStandaloneConfiguration configuration() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    configuration.setHostName(redisHost);
    configuration.setPort(redisPort);
    configuration.setPassword(redisPassword);
    return configuration;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory());
    template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
    template.setKeySerializer(new StringRedisSerializer());
    return template;
  }

}