package me.webapp.common.util.jedis.sample.jedis;

import me.webapp.common.util.jedis.pool.JedisPoolBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class UsageSample {

    public static void main(String[] args) throws InterruptedException {

        String host = "127.0.0.1";
        int port = 6379;
        int timeout = 1000;
        String password = null;

        JedisPoolBuilder builder = JedisPoolBuilder.newBuilder(host, port, timeout, password);
        JedisPool pool = builder
            .maxTotal(100)
            .build();

        Jedis jedis = pool.getResource();


        String statusCode = jedis.set("demo", "demo");
        jedis.expire("demo", 1);
        System.out.println(statusCode);

        Thread.sleep(1000);

        System.out.println(jedis.keys("*"));
    }
}
