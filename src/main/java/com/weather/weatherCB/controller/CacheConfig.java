package com.weather.weatherCB.controller;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

        @Bean
        public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofMinutes(60)); // Tiempo de vida de la caché
            return RedisCacheManager.builder(connectionFactory)
                    .cacheDefaults(config)
                    .build();
        }



}
