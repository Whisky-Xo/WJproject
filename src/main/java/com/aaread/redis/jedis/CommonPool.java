package com.aaread.redis.jedis;

import java.util.Map;

import redis.clients.jedis.JedisPool;

/**
 * 通用Jedis Pool，不支持Sentinel
 * @title JedisCommonPool
 * @description TODO 
 * @author Clark
 * @date 2014年7月8日
 * @version 1.0
 */
public class CommonPool extends Pool{
	
	public CommonPool(Map<String,String> redisConfigParameters){
		super();
		
		log.info(" redisConfigParameters setting : " + redisConfigParameters);
		if (redisConfigParameters == null || redisConfigParameters.size() == 0) {
			log.info("jedisPoolConfigParameter not setting any property");
			return;
		}
		parseSettingValue(redisConfigParameters);
		this.server = findRCSetting(REDIS_SERVER).getValue();
		this.jedisPool = this.newJedisPool(server);
	}
	
	@Override
	public JedisPool getJedisPool() {
		return this.jedisPool;
	}
}
