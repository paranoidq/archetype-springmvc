package me.webapp.common.util.jedis.pool;

/*-
 * ========================LICENSE_START=================================
 * springmvc
 * %%
 * Copyright (C) 2018 Wei Qian
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =========================LICENSE_END==================================
 */

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class JedisPoolBuilder {

    private String host;
    private int port;
    private int timeout;
    private String password;


    private int maxTotal = 8;
    private int maxIdle = 8;
    private int maxWaitMillis = 5000;

    private boolean testWhileIdle = true;
    private int timeBetweenEvictionRunsMillis = 5000;
    private int minEvictableIdleTimeMillis = 10000;


    private JedisPoolBuilder(String host, int port, int timeout, String password) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.password = password;
    }

    public static JedisPoolBuilder newBuilder(String host, int port, int timeout, String password) {
        return new JedisPoolBuilder(host, port, timeout, password);
    }

    public JedisPool build() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        // 配置jedis线程池数量
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        // 配置jedis线程池idle eviction
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        return jedisPool;
    }

    public JedisPoolBuilder maxTotal(int maxTotal) {
        if (maxTotal > 0) {
            this.maxTotal = maxTotal;
        }
        return this;
    }

    public JedisPoolBuilder maxIdle(int maxIdle) {
        if (maxIdle > 0 && maxIdle <= maxTotal) {
            this.maxIdle = maxIdle;
        }
        return this;
    }

    public JedisPoolBuilder maxWaitMillis(int maxWaitMillis) {
        if (maxWaitMillis > 0) {
            this.maxWaitMillis = maxWaitMillis;
        }
        return this;
    }


    public JedisPoolBuilder timeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        if (timeBetweenEvictionRunsMillis > 0) {
            this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        }
        return this;
    }

    public JedisPoolBuilder minEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        if (minEvictableIdleTimeMillis > 0) {
            this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        }
        return this;
    }

    public JedisPoolBuilder testWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
        return this;
    }


    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 6379;
        int timeout = 1000;
        String password = null;

        JedisPoolBuilder builder = new JedisPoolBuilder(host, port, timeout, password);
        JedisPool pool = builder
            .maxTotal(100)
            .build();

    }
}
