package com.soldiersoft.traveler.util;

import cn.hutool.json.JSONUtil;
import com.soldiersoft.traveler.model.dto.RedisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    private static StringRedisTemplate stringRedisTemplate;

    public static String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public static void setString(String key, String value, Long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public static boolean delete(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.delete(key));
    }

    public static Set<ZSetOperations.TypedTuple<String>> getZSet(String key, double min, double max, long offset, long count) {
        return stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, offset, count);
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisUtil.stringRedisTemplate = stringRedisTemplate;
    }

    public void setStringWithLogicalTime(String key, Object value, Long timeout, TimeUnit unit) {
        RedisDTO redisDTO = RedisDTO.builder()
                .data(value)
                .expireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(timeout)))
                .build();
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisDTO));
    }
}
