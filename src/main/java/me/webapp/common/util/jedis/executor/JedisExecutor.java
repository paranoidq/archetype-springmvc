package me.webapp.common.util.jedis.executor;

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
