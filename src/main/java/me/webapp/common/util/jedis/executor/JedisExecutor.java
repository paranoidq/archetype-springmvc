package me.webapp.common.util.jedis.executor;

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

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class JedisExecutor {

    private JedisPool jedisPool;

    private JedisExecutor(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public static JedisExecutor newExecutor(JedisPool jedisPool) {
        return new JedisExecutor(jedisPool);
    }


    /**
     * Command模式，调用JedisCommand执行redis处理，并做清理工作
     * 具体的redis处理逻辑在JedisCommand的实现中
     *
     * <p>
     *     采用该模式可以避免忘记归还jedis到资源池导致的泄露问题
     * </p>
     *
     * 经过试验，也可以采用CGLib动态代理，但是由于jedis处理的内部会相互调用，因此动态代理要对方法进行判断，避免多次被代理和循环调用的问题，且存在性能隐患
     * 因此不如采用command模式简介有效
     *
     * @param jedisCommand
     * @param <T>
     * @return
     */
    public <T> T execute(JedisCommand<T> jedisCommand) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedisCommand.commandExecute(jedis);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
