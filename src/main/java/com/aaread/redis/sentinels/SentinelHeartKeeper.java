package com.aaread.redis.sentinels;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 心跳
 * @author 张树伟
 * 2013-8-23
 */
public class SentinelHeartKeeper {
	
	private final ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	private SentinelHandler sentinelHandler = null;
	
	public SentinelHeartKeeper(SentinelHandler sentinelHandler){
		this.sentinelHandler = sentinelHandler;
	}
	
	public void start() {
		exec.scheduleAtFixedRate(new HeartTasks(),1, 1,TimeUnit.SECONDS);
	}
	
	public void stop() {
		exec.shutdown();
	}
	
	/**
	 * 心跳线程
	 * 
	 * @author zsw
	 * 
	 */
	class HeartTasks implements Runnable {
		public void run() {
			sentinelHandler.checkRedisMasterServer();
		}
	}
}
