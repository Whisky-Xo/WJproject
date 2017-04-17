package com.aaread.redis.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.aaread.protostuff.ProtostuffCodec;
import com.aaread.redis.jedis.Pool;
import com.aaread.redis.jedis.ShardedPoolManager;

/**
 * Redis 指令封装实现
 * @title RedisCacheImpl
 * @description TODO 
 * @author Clark
 * @date 2014年7月8日
 * @version 1.0
 */
@Service
public class RedisCacheImpl implements RedisCache {

	private Logger log = LoggerFactory.getLogger(RedisCache.class);
	
	@Resource
	private ShardedPoolManager shardedPoolManager;
	
	private ProtostuffCodec protostuffCodec = new ProtostuffCodec();

	public void setShardedPoolManager(ShardedPoolManager shardedPoolManager) {
		this.shardedPoolManager = shardedPoolManager;
	}

	private JedisPool getJedisPool(String key, String shardKey) {
		if (null != shardKey) {
			return shardedPoolManager.getJedisPool(shardKey);
		} else {
			return shardedPoolManager.getJedisPool(key);
		}
	}
	
	@Override
	public <T> String put(String key, T value, Class<T> clazz) {
		return this.put(key, value, clazz, 0, null);
	}

	@Override
	public <T> String put(String key, T value, Class<T> clazz, String shardKey) {
		return this.put(key, value, clazz, 0, shardKey);
	}

	@Override
	public <T> String put(String key, T value, Class<T> clazz, int seconds) {
		return this.put(key, value, clazz, seconds, null);
	}

	@Override
	public <T> String put(String key, T value, Class<T> clazz, int seconds, String shardKey) {
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		String ret = null;
		try {
			jedis = jedisPool.getResource();
			ret = jedis.set(key.getBytes(), this.object2Bytes(clazz,value));
			if (seconds > 0) {
				jedis.expire(key.getBytes(), seconds);// 设置过期时间
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	@Override
	public Long incrby(String key, Long value){
		return this.incrby(key, value, null);
	}
	
	@Override
	public Long incrby(String key, Long value, String shardKey){
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		Long ret = null;
		try {
			jedis = jedisPool.getResource();
			ret = jedis.incrBy(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	@Override
	public Long decrby(String key, Long value){
		return this.decrby(key, value, null);
	}
	
	@Override
	public Long decrby(String key, Long value, String shardKey){
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		Long ret = null;
		try {
			jedis = jedisPool.getResource();
			ret = jedis.decrBy(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	@Override
	public Long incr(String key){
		return this.incr(key,null);
	}
	
	@Override
	public Long incr(String key, String shardKey){
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		Long ret = null;
		try {
			jedis = jedisPool.getResource();
			ret = jedis.incr(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	@Override
	public Long decr(String key){
		return this.incr(key,null);
	}
	
	@Override
	public Long decr(String key, String shardKey){
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		Long ret = null;
		try {
			jedis = jedisPool.getResource();
			ret = jedis.decr(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}

	@Override
	public String putString(String key, String value) {
		return this.putString(key, value, 0, null);
	}

	@Override
	public String putString(String key, String value, String shardKey) {
		return this.putString(key, value, 0, shardKey);
	}

	@Override
	public String putString(String key, String value, int seconds) {
		return this.putString(key, value, seconds, null);
	}

	@Override
	public String putString(String key, String value, int seconds, String shardKey) {
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		String ret = null;
		try {
			jedis = jedisPool.getResource();
			ret = jedis.set(key, value);
			if (seconds > 0) {
				jedis.expire(key, seconds);// 设置过期时间
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}

	@Override
	public <T> T get(String key,Class<T> clazz) {
		return this.get(key, clazz, null);
	}

	@Override
	public <T> T get(String key, Class<T> clazz, String shardKey) {
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			byte[] obj = jedis.get(key.getBytes());
			return this.bytes2Object(clazz,obj);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return null;
	}

	@Override
	public String getString(String key) {
		return this.getString(key, null);
	}

	@Override
	public String getString(String key, String shardKey) {
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return null;
	}

	@Override
	public Long addToList(String key, String entry, boolean isR) {
		return this.addToList(key, entry, isR, 0, null);
	}

	@Override
	public Long addToList(String key, String entry, boolean isR, String shardKey) {
		return this.addToList(key, entry, isR, 0, shardKey);
	}

	@Override
	public Long addToList(String key, String entry, boolean isR, int seconds) {
		return this.addToList(key, entry, isR, seconds, null);
	}

	@Override
	public Long addToList(String key, String entry, boolean isR, int seconds, String shardKey) {
		Jedis jedis = null;
		Long ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			if (isR) {
				ret = jedis.rpush(key, entry);
			} else {
				ret = jedis.lpush(key, entry);
			}
			if (seconds > 0) {
				jedis.expire(key.getBytes(), seconds);// 设置过期时间
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}

	@Override
	public <T> Long addToList(String key, T object, Class<T> clazz, boolean isR) {
		return this.addToList(key, object, clazz, isR, 0, null);
	}

	@Override
	public <T> Long addToList(String key, T object, Class<T> clazz, boolean isR, String shardKey) {
		return this.addToList(key, object, clazz, isR, 0, shardKey);
	}

	@Override
	public <T> Long addToList(String key, T object, Class<T> clazz, boolean isR,int seconds) {
		return this.addToList(key, object, clazz, isR, seconds, null);
	}

	@Override
	public <T> Long addToList(String key, T object, Class<T> clazz, boolean isR,int seconds, String shardKey) {
		Jedis jedis = null;
		Long ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			if (isR) {
				ret = jedis.rpush(key.getBytes(), this.object2Bytes(clazz,object));
			} else {
				ret = jedis.lpush(key.getBytes(), this.object2Bytes(clazz,object));
			}
			if (seconds > 0) {
				jedis.expire(key.getBytes(), seconds);// 设置过期时间
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}

	@Override
	public Long putToMap(String key, String field, String value) {
		return this.putToMap(key, field, value, 0, null);
	}

	@Override
	public Long putToMap(String key, String field, String value, String shardKey) {
		return this.putToMap(key, field, value, 0, shardKey);
	}

	@Override
	public Long putToMap(String key, String field, String value, int seconds) {
		return this.putToMap(key, field, value, seconds, null);
	}

	@Override
	public Long putToMap(String key, String field, String value, int seconds, String shardKey) {
		Jedis jedis = null;
		Long ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret = jedis.hset(key, field, value);
			if (seconds > 0) {
				jedis.expire(key.getBytes(), seconds);// 设置过期时间
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}

	@Override
	public Long hincrby(String key, String field, long value) {
		return this.hincrby(key, field, value, null);
	}

	@Override
	public Long hincrby(String key, String field, long value, String shardKey) {
		Jedis jedis = null;
		Long ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret = jedis.hincrBy(key, field, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}

	@Override
	public Long delStringFromMap(String key, String field) {
		return this.delStringFromMap(key, field, null);
	}

	@Override
	public Long delStringFromMap(String key, String field, String shardKey) {
		Jedis jedis = null;
		Long ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret = jedis.hdel(key, field);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}

	@Override
	public String getStringFromMap(String key, String field) {
		return this.getStringFromMap(key, field, null);
	}

	@Override
	public String getStringFromMap(String key, String field, String shardKey) {
		Jedis jedis = null;
		String ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret = jedis.hget(key, field);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}

	@Override
	public Map<String, String> getMapFromMap(String key) {
		return this.getMapFromMap(key, null);
	}

	@Override
	public Map<String, String> getMapFromMap(String key, String shardKey) {
		Jedis jedis = null;
		Map<String, String> ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret = jedis.hgetAll(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	@Override
	public Long hlen(String key){
		return this.hlen(key, null);
	}
	
	@Override
	public Long hlen(String key, String shardKey){
		Jedis jedis = null;
		Long ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret = jedis.hlen(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	@Override
	public boolean hexists(String key, String field){
		return this.hexists(key, field, null);
	}
	
	@Override
	public boolean hexists(String key, String field, String shardKey){
		Jedis jedis = null;
		boolean ret = false;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret = jedis.hexists(key, field);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}

	@Override
	public List<String> getStringFromList(String key, long start, long end) {
		return this.getStringFromList(key, start, end, null);
	}

	@Override
	public List<String> getStringFromList(String key, long start, long end, String shardKey) {
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			return jedis.lrange(key, start, end);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return null;
	}

	@Override
	public String getStringFromList(String key, boolean isR) {
		return this.getStringFromList(key, isR, null);
	}

	@Override
	public String getStringFromList(String key, boolean isR, String shardKey) {
		Jedis jedis = null;
		String ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			if (isR) {
				ret = jedis.rpop(key);
			} else {
				ret = jedis.lpop(key);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}

	@Override
	public <T> T getFromList(String key, Class<T> clazz, boolean isR) {
		return this.getFromList(key, clazz, isR, null);
	}

	@Override
	public <T> T getFromList(String key, Class<T> clazz, boolean isR, String shardKey) {
		Jedis jedis = null;
		T ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			byte[] objBytes = null;
			if (isR) {
				objBytes = jedis.rpop(key.getBytes());
			} else {
				objBytes = jedis.lpop(key.getBytes());
			}
			ret = this.bytes2Object(clazz, objBytes);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	@Override
	public <T> List<T> getFromList(String key, int start, int end, Class<T> clazz){
		return this.getFromList(key, start, end, clazz, null);
	}
	
	@Override
	public <T> List<T> getFromList(String key, int start, int end, Class<T> clazz,String shardKey){
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			List<byte[]> objBytes = jedis.lrange(key.getBytes(), start, end);
			return this.bytes2Object(clazz, objBytes);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return null;
	}

	@Override
	public Long remove(String key) {
		return this.remove(key, null);
	}

	@Override
	public Long remove(String key, String shardKey) {
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		Long ret = null;
		try {
			jedis = jedisPool.getResource();
			ret = jedis.del(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}

	@Override
	public boolean exists(String key, String shardKey) {
		Jedis jedis = null;
		boolean bool = false;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			bool = jedis.exists(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return bool;
	}

	@Override
	public boolean exists(String key) {
		return this.exists(key, null);
	}

	@Override
	public void expire(String key, int seconds) {
		this.expire(key, seconds, null);
	}

	@Override
	public void expire(String key, int seconds, String shardKey) {
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			jedis.expire(key, seconds);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	@Override
	public Long sadd(String key,String member){
		return this.sadd(key, member, null, 0);
	}
	
	@Override
	public Long sadd(String key,String member,String shardKey){
		return this.sadd(key, member, shardKey, 0);
	}
	
	@Override
	public Long sadd(String key,String member,String shardKey,int seconds){
		Jedis jedis = null;
		Long ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret = jedis.sadd(key, member);
			if (seconds > 0) {
				jedis.expire(key.getBytes(), seconds);// 设置过期时间
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	@Override
	public Long srem(String key,String member){
		return this.srem(key, member, null);
	}
	
	@Override
	public Long srem(String key,String member,String shardKey){
		Jedis jedis = null;
		Long ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret = jedis.srem(key, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	@Override
	public boolean sismember(String key,String member){
		return this.sismember(key, member, null);
	}
	@Override
	public boolean sismember(String key, String member,String shardKey){
		Jedis jedis = null;
		boolean ret = false;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret =jedis.sismember(key, member);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	@Override
	public Long scard(String key){
		return this.scard(key, null);
	}
	@Override
	public Long scard(String key,String shardKey){
		Jedis jedis = null;
		Long ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret =jedis.scard(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	@Override
	public Set<String> getStrsFromSet(String key){
		return this.getStrsFromSet(key,null);
	}
	
	@Override
	public Set<String> getStrsFromSet(String key,String shardKey){
		Jedis jedis = null;
		Set<String> ret = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			ret = jedis.smembers(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	@Override
	public Set<String> keys(String pattern){
		Jedis jedis = null;
		Map<String, Pool> poolMap = shardedPoolManager.getJedisPool();
		Set<String> ret = new HashSet<String>();
		for(Entry<String,Pool> entry : poolMap.entrySet()){
			log.info("keys pool = " + entry.getKey());
			JedisPool jedisPool = entry.getValue().getJedisPool();
			try{
				jedis = jedisPool.getResource();
				ret.addAll(jedis.keys(pattern));
			} catch (Exception e) {
				log.error(e.getMessage(), e.fillInStackTrace());
				jedisPool.returnBrokenResource(jedis);
			} finally {
				if (null != jedisPool) {
					jedisPool.returnResource(jedis);
				}
			}
		}
		log.info("keys size = " + ret.size());
		return ret;
	}

	@Override
	public void getPoolTest(String shardKey) {
		getJedisPool(shardKey, shardKey);
	}
	
	private <T> List<T> bytes2Object (Class<T> clazz, List<byte[]> objBytes) throws Exception {
		if (objBytes == null) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for(byte[] obj : objBytes){
			list.add(this.bytes2Object(clazz, obj));
		}
		return list;
	}
	
	private <T>byte[] object2Bytes(Class<T> clazz, T object) throws Exception {
		if (object == null) {
			return null;
		}
		return protostuffCodec.encode(clazz, object);
	}
	private <T>byte[] List2Bytes(Class<T> clazz, List<T> object) throws Exception {
		if (object == null) {
			return null;
		}
		return protostuffCodec.encodeList(clazz, object);
	}

	private <T> T bytes2Object(Class<T> clazz, byte[] objBytes) throws Exception {
		if (objBytes == null) {
			return null;
		}
		return protostuffCodec.decode(clazz, objBytes);
	}
	private <T> List<T> bytes2List(Class<T> clazz, byte[] objBytes) throws Exception {
		if (objBytes == null) {
			return null;
		}
		return protostuffCodec.decodeList(clazz, objBytes);
	}

	@Override
	public <T> String putList(String key, List<T> value, Class<T> clazz, int seconds) {
		return putList(key, value, clazz, seconds, null);
	}

	@Override
	public <T> String putList(String key, List<T> value, Class<T> clazz,
			int seconds, String shardKey) {
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		String ret = null;
		try {
			jedis = jedisPool.getResource();
			ret = jedis.set(key.getBytes(), this.List2Bytes(clazz,value));
			if (seconds > 0) {
				jedis.expire(key.getBytes(), seconds);// 设置过期时间
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return ret;
	}
	
	public <T> List<T> getList(String key,Class<T> clazz) {
		return this.getList(key, clazz, null);
	}

	public <T> List<T> getList(String key, Class<T> clazz, String shardKey) {
		Jedis jedis = null;
		JedisPool jedisPool = getJedisPool(key, shardKey);
		try {
			jedis = jedisPool.getResource();
			byte[] obj = jedis.get(key.getBytes());
			return this.bytes2List(clazz,obj);
		} catch (Exception e) {
			log.error(e.getMessage(), e.fillInStackTrace());
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (null != jedisPool) {
				jedisPool.returnResource(jedis);
			}
		}
		return null;
	}

}
