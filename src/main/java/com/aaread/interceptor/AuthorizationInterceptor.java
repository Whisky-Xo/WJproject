/*
 * AuthorizationInterceptor.java  2011-11-10 
 * 
 * Copyright (c) 2011, Sohu.com All Rights Reserved. 
 *
 * (Description about the file)
 */
package com.aaread.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 实现CAS的权限认证的拦截，如果没有权限redirect到{@link LoginController#login}，进行登陆操�?
 *
 * @date  2011-11-10
 * @author  wenyuwang
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	
	private boolean test = false;
	
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	    
	   
		if ( logger.isDebugEnabled() ) {
			logger.debug("handler name [" + handler.getClass().getName() + "]");
		}
		boolean isAccessible = true;
		
		String currentUrl = request.getRequestURL().toString();
		//登录例外
		if(StringUtils.indexOf( currentUrl, "wx_call.do" )>0 ||  StringUtils.indexOf( currentUrl, "/resources/" )>0 ||  StringUtils.indexOf( currentUrl, "/include/" )>0){
			return isAccessible;
		}
		String retUrl = "http://www.kkdai.com.cn/vote/wx_call.do";
		
//		String url = https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
		
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx345f92d8210366cd&redirect_uri="
				+ URLEncoder.encode(retUrl,"UTF-8")
				+ "&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
		
		String uid = (String) request.getSession().getAttribute( "uid" );
		if ( uid == null ) {
			if(test){
				response.sendRedirect( "http://www.kkdai.com.cn/vote/wx_call.do?test=test" );
				return false;
			}
			isAccessible = false;
			response.sendRedirect( url );
		}
		return isAccessible;
	}
}
