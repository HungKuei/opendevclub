package com.opendev.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

/**
 * redis实现分布式锁解决方案
 */
public class RedisLock {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String lock_key = "redis_lock_key";


    public String getRedisLock(Long acquireTimeOut, Long timeOut){
        RedisConnection connection = null;
        try {
            connection = redisTemplate.getConnectionFactory().getConnection();
            String lock_value = UUID.randomUUID().toString();

            int expireLock = (int)(timeOut / 1000);
            Long endTime = System.currentTimeMillis() + acquireTimeOut;
            while (System.currentTimeMillis() < endTime){
                if (connection.setNX(lock_key.getBytes(), lock_value.getBytes())){
                    connection.expire(lock_key.getBytes(), expireLock);
                    return lock_value;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return null;
    }

    public void unLock(String lockKey){

    }

}
