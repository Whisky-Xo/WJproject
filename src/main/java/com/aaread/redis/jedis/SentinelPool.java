package com.aaread.redis.jedis;

import java.util.List;
import java.util.Map;

import redis.clients.jedis.JedisPool;

import com.aaread.redis.sentinels.Sentinel;
import com.aaread.redis.sentinels.SentinelHandler;
import com.aaread.redis.sentinels.SentinelHeartKeeper;

/**
 * jedis客户端基于sentinel的连接池，实现主从机制的failover
 * 
 * @author 张树伟
 *
 */
public class SentinelPool extends Pool implements SentinelHandler{
	
	public static final String SENTINEL_SERVER = "sentinelServer";
	
	public static final String SENTINEL_NAME = "sentinelName";
	
	private Sentinel sentinel = null;
	
	private boolean isNew = true;
	
	private SentinelHeartKeeper hertKeeper;
	
	public SentinelPool(Map<String,String> redisConfigParameters){
		super();
		
		redisSettings.add(new JedisSetting(SENTINEL_SERVER, DataType_String));
		redisSettings.add(new JedisSetting(SENTINEL_NAME, DataType_String));
		
		log.info(" redisConfigParameters setting : " + redisConfigParameters);
		if (redisConfigParameters == null || redisConfigParameters.size() == 0) {
			log.info("jedisPoolConfigParameter not setting any property");
			return;
		}
		parseSettingValue(redisConfigParameters);
		
		buildSentinel();
		try {
			//初始化redis连接池
			server = getMasterServer();
			this.jedisPool = this.newJedisPool(server);
			isNew = false;
			
			//启动心跳检查
			hertKeeper = new SentinelHeartKeeper(this);
			hertKeeper.start();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public JedisPool getJedisPool() {
		if(isNew){
			synchronized(this.jedisPool){
				if(isNew){
					this.jedisPool = this.newJedisPool(getMasterServer());
					isNew = false;
				}
			}
			
		}
		return this.jedisPool;
	}
	
	@Override
	public String getMasterServer(){
		String server = null;
		try{
			List<String> m = sentinel.getMasterIp(findRCSetting(SENTINEL_NAME).getValue());
			if(log.isDebugEnabled()){
				log.debug("+++ sentinel return redis master server : " + m.toString());
			}
			server = m.get(0)+":"+m.get(1);
		}catch(Exception e){//连接sentinel获取redis master server失败，转为默认
			log.error("getMasterHosts is error : " + e.getMessage(),e.fillInStackTrace());
			server = this.server = findRCSetting(REDIS_SERVER).getValue();
		}
		
		return server;
	}
	
	@Override
	public void checkRedisMasterServer(){
		String currentServer = this.getMasterServer();
		if(!currentServer.equals(server)){
			server = currentServer;
			isNew = true;
		}
		if(log.isDebugEnabled()){
			log.debug("+++ redis server = " + server);
		}
	}

	private void buildSentinel(){
		String[] hostAndPort = findRCSetting(SENTINEL_SERVER).getValue().split(":");
		sentinel = new Sentinel(hostAndPort[0],Integer.parseInt(hostAndPort[1]));
	}
	
	
	
}
