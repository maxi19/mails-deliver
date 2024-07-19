package com.turnero.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import io.lettuce.core.ReadFrom;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


@Configuration
public class AppConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED).commandTimeout(Duration.ofSeconds(timeout))
                .build();
        RedisStandaloneConfiguration serverConfiguration = new RedisStandaloneConfiguration(host, port);
        serverConfiguration.setPassword(password);
        return new LettuceConnectionFactory(serverConfiguration, clientConfiguration);
    }

    @Bean
    public RedisTemplate<String, Session> redisTemplate(){
        RedisTemplate<String, Session> empTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL,  JsonAutoDetect.Visibility.ANY);
        empTemplate.setKeySerializer(new StringRedisSerializer());

        //empTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        empTemplate.setConnectionFactory(redisConnectionFactory());
        RedisSerializer<String> serializer = new StringRedisSerializer();

        empTemplate.setHashKeySerializer(new StringRedisSerializer());
        empTemplate.setKeySerializer(serializer);
        empTemplate.setHashKeySerializer(serializer);
        //empTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        empTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));


       //
       // empTemplate.setKeySerializer(serializer);
        return empTemplate;
    }
}
