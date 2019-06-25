package com.microblog.user.service.config;


import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
/*
    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer maxActive;
    //最大空闲处
    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;
    //最大等待时间
    @Value("${spring.redis.jedis.pool.max-wait}")
    private String maxWait;
    //连接池中的最小空闲连接
    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer minIdle;


    //host name
    @Value("${spring.redis.host}")
    private String host;
    //连接池中的最小空闲连接
    @Value("${spring.redis.port}")
    private int port;


    *//**
     * @description:   创建  JedisPoolConfig Bean　，连接池
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
     *//*
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        System.out.println(this.toString());
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);

        return jedisPoolConfig;

    }
    *//**
     * @description: 创建 JedisConnectionFactory Bean　
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
     *//*
    @Bean(name = "JedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        return jedisConnectionFactory;

    }
    *//**
     * @description: 创建  RedisTemplate Bean
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
     *//*
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    *//**
     * @description:  配置模板
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
     *//*
    private void initDomainRedisTemplate(RedisTemplate<String, ? extends  Object> redisTemplate, RedisConnectionFactory factory) {

        //解决
        ObjectMapper mapper = jacksonObjectMapper();
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        //配置key序列化机制
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //配置value序列化机制，实体类必须有set方法 GenericJackson2JsonRedisSerializer
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }

    public ObjectMapper jacksonObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //允许出现特殊字符和转义符
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true) ;
        return objectMapper;
    }

    @Override
    public String toString() {
        return "RedisConfig{" +
                "maxActive=" + maxActive +
                ", maxIdle=" + maxIdle +
                ", maxWait=" + maxWait +
                ", minIdle=" + minIdle +
                '}';
    }*/
}