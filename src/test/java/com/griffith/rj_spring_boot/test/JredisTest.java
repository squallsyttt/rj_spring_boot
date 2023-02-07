package com.griffith.rj_spring_boot.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

/**
 * 用 jRedis 操作redis
 */
public class JredisTest {
    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set("username","griffith");
        jedis.close();
    }
}
