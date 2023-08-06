package com.group.chat.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate mStringRedisTemplate;

    /**
     * 判断当前Redis缓存中是否有Key
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        Boolean has = mStringRedisTemplate.hasKey(key);
        return has != null && has;
    }
}
