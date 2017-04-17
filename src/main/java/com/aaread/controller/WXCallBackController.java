package com.aaread.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.CookieGenerator;

import com.aaread.interceptor.LoginInterceptor;
import com.aaread.mapper.UsersMapper;
import com.aaread.model.Users;
import com.aaread.model.WxUserInfo;
import com.aaread.redis.client.RedisCache;
import com.aaread.service.UserService;
import com.aaread.util.HttpClientUtils;
import com.aaread.util.JsonUtils;

@Controller
public class WXCallBackController {
	private static final Logger logger = LoggerFactory.getLogger(WXCallBackController.class);
	private static final String secret = "36fc56da829f95b95226e58bc84b3837";
	private static final String appid = "wxe36a434759719184";
	private static final String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
	@Autowired
	private UserService userService;
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private RedisCache redis;
	
	private static final String RANDOMSTR = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	@RequestMapping("/wx_call")
	public String call(String code ,String state, @CookieValue("JSESSIONID") String cookieValue, HttpServletRequest request){
//		String test = request.getParameter("test");
//		if(!StringUtils.isEmpty(test)){
//			redis.putString(LoginInterceptor.JSSESSION_ID + cookieValue, RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz0123456789"),
//					60 * 60 * 24);
//			return "redirect:/index.do";
//		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", appid);
		map.put("secret", secret);
		map.put("code", code);
		map.put("grant_type", "authorization_code");
		try {
			String res = HttpClientUtils.doGet(url, map);
//			logger.info("wx_login:"+res);
			String opentId = JsonUtils.getFromJson(res, "openid");
			String accessToken = JsonUtils.getFromJson(res, "access_token");
			logger.info("opentId:{},accessToken:{}",new Object[]{opentId,accessToken});
			
			if(StringUtils.isEmpty(opentId)){
				return "redirect:/index.do";
			}
//			boolean isSubscribe = userService.isSubscribe(opentId);
//			if(!isSubscribe){
//				return "erweima";
//			}
			
			Users user = usersMapper.selectByUid(opentId);
			if(user ==  null){
				WxUserInfo wxUserInfo = userService.getWxUserInfo(accessToken, opentId);
				user = new Users();
				user.setUid(wxUserInfo.getOpenid());
				user.setName(wxUserInfo.getNickname());
				user.setPic(wxUserInfo.getHeadimgurl());
				usersMapper.insertSelective(user);
			}
			
			redis.putString(LoginInterceptor.JSSESSION_ID + cookieValue, opentId,60 * 60 * 24);
			redis.putString(opentId, accessToken,60 * 60 * 24);
			return "redirect:/index.do";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping("/gzh_call")
	public String gzhCall(HttpServletResponse response){
		CookieGenerator cg1 = new CookieGenerator();
		cg1.setCookieMaxAge(24 * 60 * 60 * 30);
		cg1.setCookieName("Subscribe");
		cg1.addCookie(response, RandomStringUtils.random(8, RANDOMSTR));
		return "redirect:/index.do";
	}

}
