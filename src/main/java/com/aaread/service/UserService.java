package com.aaread.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.aaread.model.WxUserInfo;
import com.aaread.redis.client.RedisCache;
import com.aaread.util.HttpClientUtils;
import com.aaread.util.JsonUtils;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private static final String url = "https://api.weixin.qq.com/sns/userinfo";
	private static final String url_ticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	private static final String url2 = "https://api.weixin.qq.com/cgi-bin/user/info";
	private static final String WX_USER_ID = "WX_USER_ID";
	
	@Autowired
	private RedisCache redisCache;
	
	final ReentrantLock lock;
	
	public UserService() {
		this.lock = new ReentrantLock();
	}
	public WxUserInfo getWxUserInfo(String accessToken,String openid){
		
//		if(redisCache.sismember(WX_USER_ID, openid)){
//			return ;
//		}
//		String accessToken2 = getAccessToken();
		Map<String, String> map = new HashMap<String, String>();
		map.put("access_token", accessToken);
		map.put("openid", openid);
		map.put("lang", "zh_CN");
		try {
//			String res = HttpClientUtils.doGet(url, map);
			logger.info("userinfo:======================");
			String res2 = HttpClientUtils.doGet(url, map);
			WxUserInfo user = JsonUtils.fromJson(res2, WxUserInfo.class);
//			logger.info("userinfo:"+res);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public String getJSApiTicket(String openId){
		String accessToken = getAccessToken();
		Map<String, String> map = new HashMap<String, String>();
		map.put("access_token", accessToken);
		map.put("type", "jsapi");
		try {
//			String res = HttpClientUtils.doGet(url, map);
			String res2 = HttpClientUtils.doGet(url_ticket, map);
			String ticket = JsonUtils.getFromJson(res2,"ticket");
//			logger.info("userinfo:"+res);
			return ticket;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isSubscribe(String openid){
		
//		if(redisCache.sismember(WX_USER_ID, openid)){
//			logger.info("已关注:"+openid);
//			return true;
//		}
		String accessToken2 = getAccessToken();
		Map<String, String> map = new HashMap<String, String>();
		map.put("access_token", accessToken2);
		map.put("openid", openid);
		map.put("lang", "zh_CN");
		try {
//			String res = HttpClientUtils.doGet(url, map);
			String res = HttpClientUtils.doGet(url2, map);
			long subscribe = JsonUtils.getFromJson(res, "subscribe");
		    if(subscribe == 1){
//		    	redisCache.sadd(WX_USER_ID, openid);
		    	return true;
		    }
//			logger.info("userinfo:"+res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private static final String url_tocken = "https://api.weixin.qq.com/cgi-bin/token";
	
	private static final String secret = "36fc56da829f95b95226e58bc84b3837";
	private static final String appid = "wxe36a434759719184";

	public String getAccessToken(){
		String accessToken = redisCache.getString("wx_access_token");
		if(StringUtils.isEmpty(accessToken)){
			final ReentrantLock lock = this.lock;
		    lock.lock();
			try {
				accessToken = redisCache.getString("wx_access_token");
				if(!StringUtils.isEmpty(accessToken)){
					return accessToken;
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("grant_type", "client_credential");
				map.put("appid", appid);
				map.put("secret", secret);
				
				String res = HttpClientUtils.doGet(url_tocken, map);
				
				accessToken = JsonUtils.getFromJson(res, "access_token");
				if(!StringUtils.isEmpty(accessToken)){
					logger.info("get accessToken from wx:"+accessToken);
					redisCache.putString("wx_access_token", accessToken, 3600);
					return accessToken;
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
		}
		logger.info("get accessToken from redis:"+accessToken);
		return accessToken;
	}
	
	public static void main(String[] args) throws Exception {
//		String url = "https://api.weixin.qq.com/sns/userinfo?openid=oFN4st_eXLhp-jexsSH08pQrIh0o&lang=zh_CN&access_token=OezXcEiiBSKSxW0eoylIeAeL7-6c9sHtJJGQ4oK35yHiJ73Kp6A0Z_-y0ivvC5rNo7QrT_-2986GgOx756Nl1a_Hhsew2kLV-4pK7YFdTINwzAxHb58-8Cmr9NqGtYbSFfV2Aua6dOWsCOuO4ePyKg";
//		System.out.println(HttpClientUtils.doGet(url, null));
	}
}
