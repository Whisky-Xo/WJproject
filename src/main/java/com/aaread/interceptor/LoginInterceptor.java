/*
 * LoginInterceptor.java  2012-2-25 
 * 
 * Copyright (c) 2012, Sohu.com All Rights Reserved. 
 *
 * (Description about the file)
 */
package com.aaread.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

import com.aaread.redis.client.RedisCache;

/**
 * 用户登录拦截器
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginInterceptor.class);

	private RedisCache redis;
	
	public static final String JSSESSION_ID = "VOTE_JSSESSION_ID_";


	private static final String retUrl = "http://www.aaread.com/wx_call.do";
	
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("handler name [" + handler.getClass().getName() + "]");
		}

		long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		boolean isLogin = isLogin(request, response);
		String currentUrl = request.getRequestURL().toString();
		//登录例外
		if(StringUtils.indexOf( currentUrl, "wx_call.do" )>0 ||  
				StringUtils.indexOf( currentUrl, "/resources/" )>0 
				||  StringUtils.indexOf( currentUrl, "/include/" )>0
				||  StringUtils.indexOf( currentUrl, "/erweima.do" )>0
				||  StringUtils.indexOf( currentUrl, "/dekcb.do" )>0
				||  StringUtils.indexOf( currentUrl, "/gzh_call.do" )>0
				||  StringUtils.indexOf( currentUrl, "/notify" )>0
				||  StringUtils.indexOf( currentUrl, "/page_" )>0
				||  StringUtils.indexOf( currentUrl, "/item_" )>0
				||  StringUtils.indexOf( currentUrl, "/video" )>0
				||	StringUtils.endsWith(currentUrl, "/")
				){
			return true;
		}
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe36a434759719184&redirect_uri="
				+ URLEncoder.encode(retUrl,"UTF-8")
				+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		
		if ( !isLogin ) {
			response.sendRedirect( url );
			return false;
		}
		
		return true;
	}

	private boolean isLogin(HttpServletRequest request,
			HttpServletResponse response) {

		Cookie cookie = WebUtils.getCookie(request, "JSESSIONID");

		if (null == cookie) {
			logger.info("cookie is null");
			CookieGenerator cg1 = new CookieGenerator();
			cg1.setCookieMaxAge(24 * 60 * 60);
			cg1.setCookieName("JSESSIONID");
			cg1.addCookie(response, "" + request.getSession().getId());
			return false;
		}
		String key = cookie.getValue();
		logger.info("cookievalue={}",key);

		String wxUserId = redis.getString(JSSESSION_ID + key);
		if (StringUtils.isEmpty(wxUserId)) {
			return false;
			//TODO 测试用
//			request.setAttribute("login_user", "test");
//			return true;
		} else {
			redis.putString(JSSESSION_ID + key, wxUserId,
					60 * 60 * 24);
			request.setAttribute("login_user", wxUserId);
		}
		return true;
	}

	public void setRedis(RedisCache redis) {
		this.redis = redis;
	}


	private String getReffrrer(HttpServletRequest request) {
		String referrer = request.getRequestURL().toString();

		if (request.getQueryString() != null) {
			referrer = referrer + '?';
			referrer = referrer + request.getQueryString();
		}
		return referrer;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;
		String referrer = getReffrrer(request);

		logger.info("executeTime :{} ms,[{}] ", Long.valueOf(executeTime),
				referrer);

		super.postHandle(request, response, handler, modelAndView);
	}
}
