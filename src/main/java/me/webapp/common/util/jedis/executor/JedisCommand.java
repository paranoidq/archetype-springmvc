package me.webapp.common.util.jedis.executor;

import redis.clients.jedis.Jedis;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public interface JedisCommand<T> {

    /**
     * 执行Jedis command具体逻辑，并通过callback传递执行结果
     *
     * @param jedis
     * @return T
     */
    T commandExecute(Jedis jedis);
}
