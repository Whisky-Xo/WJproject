package com.aaread.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderIdUtil {
	
	private static final AtomicInteger index = new AtomicInteger(0);
	private static final int MAX_INDEX = 99999;
	//生成唯一订单号
	public static String genTradeNo(){
		int i = index.getAndIncrement();
		if (i > MAX_INDEX){
			for (int current = index.get();current>MAX_INDEX;) {
	            if (index.compareAndSet(current, 0))
	                break;
	            current = index.get();
	        }
			i = index.getAndIncrement();
		}
		String strIndex = String.format("%05d",i);
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowTime = dateFormat.format(now);
		
		return "01" + nowTime + strIndex;
	}

}
