package com.szb.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

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

        LettuceClientConfiguration.LettuceClientConfigurationBuilder
                lettuceClientConfigurationBuilder = LettuceClientConfiguration.builder();;
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration,
                lettuceClientConfigurationBuilder.build());
        return factory;
    }


    @Bean(name ="szbRedisTemplate")
    public RedisTemplate<String,Object> szbRedisTemplate(@Qualifier("szbRedisDataSource")
                                                                  LettuceConnectionFactory lettuceConnectionFactory) {
        final RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
        RedisSerializer<String> keySerializer = new StringRedisSerializer();
        RedisSerializer<Object> valueSerializer = new Jackson2JsonRedisSerializer(Object.class);
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
