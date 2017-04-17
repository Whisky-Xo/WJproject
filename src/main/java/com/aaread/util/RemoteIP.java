package com.aaread.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class RemoteIP {
	public static final String COMMA = ",";
	public static final String COLON = ":";
	public static final String UNKNOWN = "unknown";
	public static final String X_FORWARDED_FOR = "X-Forwarded-For";
	public static final String X_REAL_IP = "X-Real-IP";
	public static final String REMOTE_PORT = "remote-port";
	
	public static String getRemoteIp(HttpServletRequest request){
    	String remoteAddr = request.getHeader(X_FORWARDED_FOR);
    	System.out.println("remoteAddr1:"+remoteAddr);
        // 如果通过多级反向代理，X-Forwarded-For的值不止一个，而是一串用逗号分隔的IP值，此时取X-Forwarded-For中第一个非unknown的有效IP字符串
        if (isEffective(remoteAddr) && (remoteAddr.indexOf(COMMA) > -1)) {
            String[] array = remoteAddr.split(COMMA);
            for (String element : array) {
                if (isEffective(element)) {
                    remoteAddr = element;
                    break;
                }
            }
        }
        if (!isEffective(remoteAddr)) {
            remoteAddr = request.getHeader(X_REAL_IP);
        }
        System.out.println("remoteAddr2:"+remoteAddr);
        if (!isEffective(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        }
        System.out.println("remoteAddr3:"+remoteAddr);
        /*String port = request.getHeader(REMOTE_PORT);
        if(StringUtils.isBlank(port)){
        	port = request.getServerPort() + StringUtils.EMPTY;
        }*/
        
        //return remoteAddr + COLON + port;
    	if(StringUtils.isNotBlank(remoteAddr) && !"0:0:0:0:0:0:0:1".equals(remoteAddr)){
    		return remoteAddr;
    	}
    	
        return StringUtils.EMPTY;
    }
    
    /**
     * 远程地址是否有效.
     * 
     * @param remoteAddr
     *            远程地址
     * @return true代表远程地址有效，false代表远程地址无效
     */
    private static boolean isEffective(String remoteAddr) {
        boolean isEffective = false;
        if ((!StringUtils.isBlank(remoteAddr)) && (!UNKNOWN.equalsIgnoreCase(remoteAddr))) {
            isEffective = true;
        }
        return isEffective;
    }
    
    public static String getRemotePhoneIp(HttpServletRequest request){
    	String ip = getRemoteIp(request);
    	if(!"0:0:0:0:0:0:0:1".equals(ip)){
    		return ip;
    	}
    	
    	return "";
    }
}
