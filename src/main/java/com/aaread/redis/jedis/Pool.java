package com.aaread.redis.jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public abstract class Pool {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public static final int DataType_String = 0;
	
	public static final int DataType_Integer = 1;
	
	public static final int DataType_Long = 2;
	
	public static final int DataType_Boolean = 3;
	
	public static final int NotSetting = 0;
	
	public static final int SettingOk = 1;
	
	public static final int SettingWrong = 2;
	
	public static final String MAX_ACTIVE = "maxActive";
	
	public static final String MAX_IDLE = "maxIdle";
	
	public static final String MAX_WAIT = "maxWait";
	
	public static final String TEST_ON_BORROW = "testOnBorrow";
	
	public static final String TEST_ON_RETURN = "testOnReturn";
	
	public static final String REDIS_SERVER = "redisServer";
	
	public static final String REDIS_NAME = "redisName";
	
	public static final String REDIS_WEIGHT = "weight";
	
	public static final String PASSWORD = "password";
	
	protected List<JedisSetting> redisSettings;
	
	protected String server;
	
	protected JedisPool jedisPool = null;
	
	public Pool(){
		redisSettings = new ArrayList<JedisSetting>();
		redisSettings.add(new JedisSetting(MAX_ACTIVE, DataType_Integer));
		redisSettings.add(new JedisSetting(MAX_IDLE, DataType_Integer));
		redisSettings.add(new JedisSetting(MAX_WAIT, DataType_Integer));
		redisSettings.add(new JedisSetting(TEST_ON_BORROW, DataType_Boolean));
		redisSettings.add(new JedisSetting(TEST_ON_RETURN, DataType_Boolean));
		redisSettings.add(new JedisSetting(REDIS_SERVER, DataType_String));
		redisSettings.add(new JedisSetting(REDIS_NAME, DataType_String));
		redisSettings.add(new JedisSetting(PASSWORD, DataType_String));
	}
	
	public abstract JedisPool getJedisPool();
	
	protected JedisPool newJedisPool(String redisHost){
		JedisPoolConfig config = new JedisPoolConfig();
		if (findRCSetting(MAX_ACTIVE).getFlag() == SettingOk) {
			config.setMaxActive(findRCSetting(MAX_ACTIVE).getIntValue());
		}else{
			config.setMaxActive(1000);
		}
		if (findRCSetting(MAX_IDLE).getFlag() == SettingOk) {
			config.setMaxIdle(findRCSetting(MAX_IDLE).getIntValue());
		}else{
			config.setMaxIdle(20);
		}
		if (findRCSetting(MAX_WAIT).getFlag() == SettingOk) {
			//config.setMaxWait(findRCSetting(MAX_WAIT).getIntValue());
			config.setMaxWait(3000);
		}else{
			config.setMaxWait(5000);
		}
		if (findRCSetting(TEST_ON_BORROW).getFlag() == SettingOk) {
			config.setTestOnBorrow(findRCSetting(TEST_ON_BORROW).getBooleanValue());
		}else{
			config.setTestOnBorrow(true);
		}
		if (findRCSetting(TEST_ON_RETURN).getFlag() == SettingOk) {
			config.setTestOnReturn(findRCSetting(TEST_ON_RETURN).getBooleanValue());
		}else{
			config.setTestOnReturn(true);
		}
		
		log.info("maxActive=" + config.getMaxActive() + ",maxIdle=" + config.getMaxIdle() + ",maxWait=" + config.getMaxWait());
		
		JedisPool pool = null;
		if (findRCSetting(PASSWORD).getFlag() == SettingOk){
			pool = new JedisPool(config, redisHost.split(":")[0],Integer.parseInt(redisHost.split(":")[1]),Protocol.DEFAULT_TIMEOUT,findRCSetting(PASSWORD).getValue());
		}else{
			pool = new JedisPool(config, redisHost.split(":")[0],Integer.parseInt(redisHost.split(":")[1]));
		}
				
		
		return pool;
	}
	
	protected void parseSettingValue(Map<String,String> ss) {
		for (Entry<String,String> entry : ss.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			
			JedisSetting mcs = findRCSetting(key);
			if (mcs == null) {
				log.info("invalid property name: " + key);
				continue;
			}
			if (mcs.getFlag() != NotSetting) {
				log.error("repeat set property  : " + key);
				continue;
			}
			if (value == null || value.trim().length() == 0) {
				log.error("no property value : " + value);
				continue;
			}
			parseSettingValue(mcs, value.trim());
		}

	}
	
	protected JedisSetting findRCSetting(String name) {
		if (name == null || name.trim().length() == 0) {
			return null;
		}
		name = name.trim().toLowerCase();
		for (JedisSetting rs : redisSettings) {
			if (name.equals(rs.getName().trim().toLowerCase())) {
				return rs;
			}
		}
		return null;
	}
	
	protected void parseSettingValue(JedisSetting mcs, String value) {
		if (mcs.getDataType() == DataType_String) {
			mcs.setValue(value);
			mcs.setFlag(SettingOk);
			return;
		}
		if (mcs.getDataType() == DataType_Integer) {
			try {
				mcs.setValue(String.valueOf(Integer.parseInt(value)));
				mcs.setFlag(SettingOk);
			} catch (Exception ee) {
				log.error("invalid " + mcs.getName() + " value( need integer ) : " + value);
				mcs.setFlag(SettingWrong);
			}
			return;
		}
		if (mcs.getDataType() == DataType_Long) {
			try {
				mcs.setValue(String.valueOf(Integer.parseInt(value)));
				mcs.setFlag(SettingOk);
			} catch (Exception ee) {
				log.error("invalid " + mcs.getName() + " value( need Long ) : " + value);
				mcs.setFlag(SettingWrong);
			}
			return;
		}

		if (mcs.getDataType() == DataType_Boolean) {
			if ("true".equals(value.toLowerCase())) {
				mcs.setValue("true");
				mcs.setFlag(SettingOk);
			} else if ("false".equals(value.toLowerCase())) {
				mcs.setValue("false");
				mcs.setFlag(SettingOk);
			} else {
				log.error("invalid " + mcs.getName() + " value( need 'true' or 'false' ) : " + value);
				mcs.setFlag(SettingWrong);
			}
			return;
		}
	}
	
	public void destory(){
		if(jedisPool != null){
			jedisPool.destroy();
		}
	}
	
	public String getRedisName(){
		return findRCSetting(REDIS_NAME).getValue();
	}
}
