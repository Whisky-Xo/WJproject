package com.aaread.redis.sentinels;

public interface SentinelHandler {
	public void checkRedisMasterServer();
	public String getMasterServer();
}
