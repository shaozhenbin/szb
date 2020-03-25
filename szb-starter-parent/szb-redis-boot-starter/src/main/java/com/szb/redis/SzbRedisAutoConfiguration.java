package com.szb.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties({SzbRedisProperties.class})
public class SzbRedisAutoConfiguration {

	@Bean(name="szbRedisDataSource")
    public LettuceConnectionFactory szbRedisDataSource(SzbRedisProperties szbRedisProperties) {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(szbRedisProperties.getDatabase());
        redisStandaloneConfiguration.setHostName(szbRedisProperties.getHost());
        redisStandaloneConfiguration.setPort(szbRedisProperties.getPort());
        redisStandaloneConfiguration.setPassword(szbRedisProperties.getPassword());

        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration
                .builder()
                .commandTimeout(Duration.ofMillis(300))
                .shutdownTimeout(Duration.ZERO)
                .build();

        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration,
                lettuceClientConfiguration);
        return factory;
    }


    @Bean(name ="szbRedisTemplate")
    public RedisTemplate<String,Object> szbRedisTemplate(@Qualifier("szbRedisDataSource")
                                                                  LettuceConnectionFactory lettuceConnectionFactory) {
        final RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
        RedisSerializer<String> keySerializer = new StringRedisSerializer();

        // 使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
        Jackson2JsonRedisSerializer<Object> valueSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);

        final PolymorphicTypeValidator ptv =
                BasicPolymorphicTypeValidator.builder()
                        .allowIfBaseType(Object.class)
                        .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        valueSerializer.setObjectMapper(objectMapper);

        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
        redisTemplate.setHashValueSerializer(valueSerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

//        RedisSerializationContext<String, Object> serializationContext = RedisSerializationContext
//                .<String, Object>newSerializationContext()
//                .key(keySerializer)
//                .value(valueSerializer)
//                .hashKey(keySerializer)
//                .hashValue(valueSerializer)
//                .build();
        return redisTemplate;
    }
	

}
