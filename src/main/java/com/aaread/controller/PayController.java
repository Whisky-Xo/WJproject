package com.aaread.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aaread.mapper.IorderMapper;
import com.aaread.model.Dingyue;
import com.aaread.model.Iorder;
import com.aaread.model.JsAPIConfig;
import com.aaread.model.PayCallback;
import com.aaread.model.Topic;
import com.aaread.model.UnifiedOrder;
import com.aaread.service.OrderService;
import com.aaread.service.UserService;
import com.aaread.util.HttpClientUtils;
import com.aaread.util.HttpConnection;
import com.aaread.util.MD5Util;
import com.aaread.util.OrderIdUtil;
import com.aaread.util.RemoteIP;
import com.aaread.util.Sha1Util;
import com.aaread.util.XMLUtil;
import com.ksyun.ks3.utils.Md5Utils;
@Controller
public class PayController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final String SING_KEY="1fc41423a415dcf56f61f368a4f9a7d3";
	private static final String MCH_ID="1377815302";
	public static final String APPID="wxe36a434759719184";

	@Autowired
	private IorderMapper orderMapper;
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	@RequestMapping(value="/pay/chongzhi")
	public String chongzhi(Model model,HttpServletRequest request){
		String openId = (String) request.getAttribute("login_user");
		String appId = APPID;
		String ticket = userService.getJSApiTicket(openId);
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String nonceStr = UUID.randomUUID().toString();
		String url="http://www.aaread.com/pay/chongzhi.html";
		Map<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("jsapi_ticket", ticket);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("noncestr", nonceStr);
		paramsMap.put("url", url);
		String signature = createSign(paramsMap,null);
		model.addAllAttributes(paramsMap);
		model.addAttribute("signature", signature);
		model.addAttribute("appId", appId);
		return "chongzhi";
	}
	
    public static String createSign(Map<String,String> params,String key){
        StringBuffer sign = new StringBuffer();
        Map<String, String> map = new HashMap<String, String>();
		try {
			map = getSortMap(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        boolean isNotFirst = false;

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(isNotFirst == true){
                sign.append("&");
            }else{
                isNotFirst = true;
            }

            sign.append(entry.getKey()).append("=").append(entry.getValue());
        }
        logger.info(sign.toString());
        if(!StringUtils.isEmpty(key)){
        	sign.append("&key=").append(SING_KEY);
        	return DigestUtils.md5Hex(sign.toString()).toUpperCase();
        }
        return Sha1Util.getSha1(sign.toString());
    }


    /**
     * 获取排序后的类属性及值
     * @param object
     * @return
     * @throws Exception
     */
    private static Map<String, String> getSortMap(Map<String,String> params) throws Exception{

        Map<String, String> sortMap = new TreeMap<String, String>(
                new Comparator<String>() {

                    @Override
                    public int compare(String arg0, String arg1) {

                        return arg0.compareTo(arg1);
                    }

                });

        sortMap.putAll(params);

        return sortMap;
    }
	
	 /**
     * 统一下单
     * @Title: unifiedOrder 
     * @Description: TODO 
     * @param: @param openId 微信用户openId
     * @param: @param orderId 订单ID
     * @param: @param money 订单总价，单位：分
     * @param: @param callbackUrl 回调路径
     * @param: @return
     * @param: @throws Exception
     * @return: String
     */
	@ResponseBody
	@RequestMapping(value="order")
    public JsAPIConfig unifiedOrder(int amount,HttpServletRequest request) throws Exception{
		if(amount<=0){
			return null;
		}
    	String openId = (String) request.getAttribute("login_user");
    	
    	Iorder order = new Iorder();
    	order.setOrderid(OrderIdUtil.genTradeNo());
    	order.setCreateTime(new Date());
    	order.setUid(openId);
    	order.setAmount((long)amount);
    	order.setStatus(0);
    	int row = orderMapper.insertSelective(order);
    	if(row <= 0){
    		return null;
    	}
    	UnifiedOrder unifiedOrder = new UnifiedOrder();
        unifiedOrder.setAppid(APPID);
        unifiedOrder.setAttach("hehedesk");

        unifiedOrder.setBody("会员充值");
        //TODO
        unifiedOrder.setMch_id(MCH_ID);

        unifiedOrder.setNonce_str(OrderIdUtil.genTradeNo());
        unifiedOrder.setNotify_url("http://www.aaread.com/pay/notify");

        unifiedOrder.setOpenid(openId);
        unifiedOrder.setOut_trade_no(order.getOrderid());

        String ip = RemoteIP.getRemoteIp(request);
        if(StringUtils.isEmpty(ip)){
        	ip = "120.92.44.176";
        }
        unifiedOrder.setSpbill_create_ip(ip);
        unifiedOrder.setTotal_fee(amount);

        String sign = createUnifiedOrderSign(unifiedOrder);
        unifiedOrder.setSign(sign);
        
        

        /**
         * 转成XML格式
         */
        XMLUtil.getXstream().alias("xml", unifiedOrder.getClass());
        String xml = XMLUtil.getXstream().toXML(unifiedOrder);

        HttpConnection http = new HttpConnection();
        logger.info(xml);
        
        String response = http.post("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
        logger.info("unifiedOrder");
        logger.info(response);
        Map<String, String> responseMap = XMLUtil.parseXml(response);

        String prepayId = responseMap.get("prepay_id");
        
        if(!StringUtils.isEmpty(prepayId)){
        	order.setPrepayid(prepayId);
        	orderMapper.updateByPrimaryKey(order);
        }
        return createPayConfig(prepayId);
    }
    
    
    /**
     * 获取支付配置
     * @Title: createPayConfig
     * @Description: TODO
     * @param @param preayId 统一下单prepay_id
     * @param @return
     * @param @throws Exception    
     * @return JsAPIConfig    
     * @throws
     */
    public JsAPIConfig createPayConfig(String prepayId) throws Exception {
        JsAPIConfig config = new JsAPIConfig();

        String nonce = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String packageName = "prepay_id="+prepayId;
        StringBuffer sign = new StringBuffer();
        sign.append("appId=").append(APPID);
        sign.append("&nonceStr=").append(nonce);
        sign.append("&package=").append(packageName);
        sign.append("&signType=").append(config.getSignType());
        sign.append("&timeStamp=").append(timestamp);
        sign.append("&key=").append(SING_KEY);
        String signature = DigestUtils.md5Hex(sign.toString()).toUpperCase();

        config.setAppId(APPID);
        config.setNonce(nonce);
        config.setTimestamp(timestamp);
        config.setPackageName(packageName);
        config.setSignature(signature);

        return config;
    }
    
    /**
     * 获取统一下单签名
     * @Title: createUnifiedOrderSign
     * @Description: TODO
     * @param @param unifiedOrder
     * @param @return    
     * @return String    
     * @throws
     */
    public String createUnifiedOrderSign(UnifiedOrder unifiedOrder){
        StringBuffer sign = new StringBuffer();
        sign.append("appid=").append(unifiedOrder.getAppid());
        sign.append("&attach=").append(unifiedOrder.getAttach());
        sign.append("&body=").append(unifiedOrder.getBody());
        sign.append("&device_info=").append(unifiedOrder.getDevice_info());
        sign.append("&mch_id=").append(unifiedOrder.getMch_id());
        sign.append("&nonce_str=").append(unifiedOrder.getNonce_str());
        sign.append("&notify_url=").append(unifiedOrder.getNotify_url());
        sign.append("&openid=").append(unifiedOrder.getOpenid());
        sign.append("&out_trade_no=").append(unifiedOrder.getOut_trade_no());
        sign.append("&spbill_create_ip=").append(unifiedOrder.getSpbill_create_ip());
        sign.append("&total_fee=").append(unifiedOrder.getTotal_fee());
        sign.append("&trade_type=").append(unifiedOrder.getTrade_type());
        //TODO
        sign.append("&key=").append(SING_KEY);

        return DigestUtils.md5Hex(sign.toString()).toUpperCase();
    }
    
    /**
     * 微信支付回调页面
     * @Title: wechatPayNotify
     * @Description: TODO
     * @param @param request
     * @param @param trade_status
     * @param @param out_trade_no
     * @param @param trade_no    
     * @return void    
     * @throws
     */
    @ResponseBody 
    @RequestMapping(value="/pay/notify")
    public String notify(HttpServletRequest request){
        try {
             Map<String, String> map = getCallbackParams(request);
             if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
            	 
            	 String sign = map.get("sign");
            	 map.remove("sign");
            	 String newSign = createSign(map,SING_KEY);
            	 logger.info("=====sing:"+sign+"==newsign:"+newSign);
            	 if(!newSign.equals(sign)){
            		 return getPayCallback(); 
            	 }
                 String orderId = map.get("out_trade_no");
                 //这里写成功后的业务逻辑
//                 orderService.updateConfirm(orderId);
                 Iorder order = orderMapper.selectByOrderId(orderId);
                 orderService.updateOrderSucc(order);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getPayCallback(); 
    }
    /**
     * 获取请求参数
     * @Title: getCallbackParams
     * @Description: TODO
     * @param @param request
     * @param @return
     * @param @throws Exception    
     * @return Map<String,String>    
     * @throws
     */
    public Map<String, String> getCallbackParams(HttpServletRequest request)
            throws Exception {
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        System.out.println("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        logger.info("wx pay callback :" + result);
        return XMLUtil.parseXml(result);
    }
    
    /**
     * 生成收到支付结果的确认信息
     * @Title: getPayCallback
     * @Description: TODO
     * @param @return    
     * @return String    
     * @throws
     */
    public String getPayCallback(){
        PayCallback callback = new PayCallback();
        XMLUtil.getXstream().alias("xml", callback.getClass());
        String xml = XMLUtil.getXstream().toXML(callback);
        return xml;
    }
    
    public static void main(String[] args) {
//    	e.cf, e.uu, e.vu, e.ran
    	String cf = "h5-android";
    	String uu = "876119";
    	String vu = "300320501";
    	String ran ="1478760782";
//		String sign = MD5Util.MD5Encode("h5-android876176300380531"+System.currentTimeMillis()/1000+"fbeh5player12c43eccf2bec3300344", "UTF-8");
    	String sign = MD5Util.MD5Encode("h5-android876119300320501"+"1478760782"+"fbeh5player12c43eccf2bec3300344", "UTF-8");
		System.out.println(sign);
//		9154366c584a860e9ca8c15f9987a241
	}
    
}
