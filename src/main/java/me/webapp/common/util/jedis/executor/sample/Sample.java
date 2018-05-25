package me.webapp.common.util.jedis.executor.sample;

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

import me.webapp.common.util.jedis.executor.JedisCommand;
import me.webapp.common.util.jedis.executor.JedisExecutor;
import me.webapp.common.util.jedis.pool.JedisPoolBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class Sample {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 6379;
        int timeout = 1000;
        String password = null;

        // 构造jedisPool
        JedisPoolBuilder builder = JedisPoolBuilder.newBuilder(host, port, timeout, password);
        JedisPool pool = builder
            .maxTotal(100)
            .build();

        // 构造jedisExecutor
        JedisExecutor executor = JedisExecutor.newExecutor(pool);
        // 根据需要实现JedisCommand，并执行
        Set<String> rst = executor.execute(new JedisCommand<Set<String>>() {
            @Override
            public Set<String> commandExecute(Jedis jedis) {
                // 可以执行任意jedis命令，并且无需关注资源回收的问题
                jedis.set("aaa", "aaa");
                return jedis.keys("*");
            }
        });
        System.out.println(rst);
    }
}
