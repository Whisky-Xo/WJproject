package com.aaread.redis.jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;

public class ShardedPoolManagerFactory implements 
	FactoryBean<ShardedPoolManager>,
	DisposableBean{
	
	private String poolConfig;
	
	private int failOver;
	
	private int hashingAlg;
	
	private ShardedPoolManager shardedPoolManager;
	
	public String getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(String poolConfig) {
		this.poolConfig = poolConfig;
	}

	public int getFailOver() {
		return failOver;
	}

	public void setFailOver(int failOver) {
		this.failOver = failOver;
	}

	public int getHashingAlg() {
		return hashingAlg;
	}

	public void setHashingAlg(int hashingAlg) {
		this.hashingAlg = hashingAlg;
	}

	@Override
	public ShardedPoolManager getObject() {
		
		List<Map<String,String>> confList = new ArrayList<Map<String,String>>();
		String configs[] = poolConfig.split(";");
		for(String config : configs){
			String entrys[] = config.split(",");
			Map<String,String> confMap = new HashMap<String,String>();
			for(String entry : entrys){
				confMap.put(entry.split("=")[0], entry.split("=")[1]);
			}
			confList.add(confMap);
		}
		shardedPoolManager = new ShardedPoolManager(confList,failOver,hashingAlg);
		shardedPoolManager.initialize();
		return shardedPoolManager;
	}

	@Override
	public Class<?> getObjectType() {
		return ShardedPoolManager.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void destroy() throws Exception {
		shardedPoolManager.destory();
	}

}
