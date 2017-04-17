package com.aaread.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

public class HttpClientUtils {

    private static final int INFO_THRESHOLD = 0;
    public static final String CHARSET = "utf-8";

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static final int HTTP_CON_TIME_OUT = 5000;
    public static final int HTTP_SO_TIME_OUT = 5000;
    public static final int HTTP_MAX_CONNECTIONS = 100;

    public static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    public static RequestConfig defaultRequestConfig = RequestConfig.custom()
    		.setSocketTimeout(HTTP_SO_TIME_OUT)
    		.setConnectTimeout(HTTP_CON_TIME_OUT)
    		.build();
    public static CloseableHttpClient httpclient;
    public static ConnectionConfig connectionConfig = ConnectionConfig.custom()
            .setCharset(Consts.UTF_8)
            .build();
    static {
        cm.setMaxTotal(HTTP_MAX_CONNECTIONS);
        cm.setDefaultConnectionConfig(connectionConfig);
        httpclient =  HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(defaultRequestConfig)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .build();

    }

    public static String postData(String url, String inputXml) {
        return postData(url, inputXml, "GBK");
    }

    public static String postData(String url, String inputXml, String encoding) {
        return postData(url, inputXml, encoding, false);
    }

    public static String postData(String url, String inputXml, String encoding, boolean isSetHeader) {
        StopWatch clock = new StopWatch();
        clock.start(); // 计时开始
        // logger.info("encoding={}", encoding);
        logger.info("url={},inputData={}", url, inputXml);
        HttpPost httpPost = new HttpPost(url);
        if (isSetHeader) {
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        }
        String result = null;
        try {
            StringEntity strEntity = new StringEntity(inputXml, encoding);
            strEntity.setContentEncoding(encoding);
            httpPost.setEntity(strEntity);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            logger.debug("----------------------------------------");
            logger.debug(response.getStatusLine().toString());
            if (resEntity != null) {
                logger.debug("Response content length: " + resEntity.getContentLength());
                logger.debug("Chunked?: " + resEntity.isChunked());
                result = EntityUtils.toString(resEntity, encoding).trim();
                logger.debug(result);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpPost.abort();
        }
        clock.stop(); // 计时结束
        long executeTime = clock.getTime();

        logger.debug("url={},executeTime={}ms", url, executeTime);
        if (executeTime > INFO_THRESHOLD) {
            Object[] params = {executeTime, url, result};
            logger.info("executeTime={}ms,url={},result={}", params);
        }
        return result;
    }
    
    public static String doGet(String url,Map map) throws Exception {
    	if (map != null) {
    		String queryString = Joiner.on("&").withKeyValueSeparator("=").join(map);
    		url = url + "?" + queryString;
		}
    	return getHttpResult(url);
    }

    public static String getHttpResult(String url) throws Exception {
        StopWatch clock = new StopWatch();
        clock.start(); // 计时开始
        logger.info("url={}", url);
        HttpGet httpget = new HttpGet(url);
        String json = null;
        try {
            HttpResponse response = httpclient.execute(httpget);
            // logger.debug(httpget.getURI().toString());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                json = EntityUtils.toString(entity,"UTF-8").trim();
            }

            long executeTime = clock.getTime();

            logger.debug("url={},result={}", url, json);
            logger.debug("url={},executeTime={}ms", url, executeTime);
            if (executeTime > INFO_THRESHOLD) {
                Object[] params = {executeTime, url, json};
                logger.info("executeTime={}ms,url={},result={}", params);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            httpget.abort();
        }
        return json;
    }
    public static byte[] getHttpResultBytes(String url) throws Exception {
    	StopWatch clock = new StopWatch();
    	clock.start(); // 计时开始
    	logger.info("url={}", url);
    	HttpGet httpget = new HttpGet(url);
    	byte[] bytes = null;
    	try {
    		HttpResponse response = httpclient.execute(httpget);
    		// logger.debug(httpget.getURI().toString());
    		HttpEntity entity = response.getEntity();
    		if (entity != null) {
    			bytes = EntityUtils.toByteArray(entity);
    		}
    		
    		long executeTime = clock.getTime();
    		
    		logger.debug("url={},executeTime={}ms", url, executeTime);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw e;
    	} finally {
    		httpget.abort();
    	}
    	return bytes;
    }

    public static String getHttpResponse(String url, String encoding) {
    	logger.info("url={},encoding={}", url, encoding);
        String retString = null;
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
        		.build();
        httpGet.setConfig(requestConfig);

        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        try {
            retString = httpclient.execute(httpGet, responseHandler);
        } catch (ClientProtocolException e) {
            logger.error(" catch error : ", e);
        } catch (IOException e) {
            logger.error(" catch error : ", e);
        }
        logger.info("url={},retString={}", url, retString);
        return retString;
    }

    public static void test() throws UnsupportedEncodingException {
        String token = "2.00XXOOiBY_QWkBc7b0b66782qNUjPC";
        String content = "天气还可以";
        String status = URLEncoder.encode(content, "UTF-8");
        ;

        String url = "https://api.weibo.com/2/statuses/update.json";

        String input = "access_token=" + token + "&status=" + status;
        HttpClientUtils.postData(url, input, "UTF-8");
    }

    public static String doPost(String url,Map params,String charset) throws Exception  {

        StopWatch clock = new StopWatch();
        clock.start(); // 计时开始
//        logger.info("encoding={}", encoding);
        logger.info("url={},inputData={}", url,params );
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
     
        String result = null;
        try {
        	logger.info("{}",params);
        	String inputContent = "";
        	if (params != null) {
        		inputContent = Joiner.on("&").withKeyValueSeparator("=").join(params);
			}
            logger.info(inputContent);
            StringEntity strEntity = new StringEntity(inputContent,"UTF-8");
            strEntity.setContentEncoding("UTF-8");
            httpPost.setEntity(strEntity);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            logger.debug("----------------------------------------");
            logger.debug(response.getStatusLine().toString());
            if (resEntity != null) {
                logger.debug("Response content length: " + resEntity.getContentLength());
                logger.debug("Chunked?: " + resEntity.isChunked());
                result = EntityUtils.toString(resEntity,"UTF-8").trim();
                logger.debug(result);
            }
        }   catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            httpPost.abort();
        }
        clock.stop(); // 计时结束
        long executeTime = clock.getTime();

        logger.debug("url={},executeTime={}ms", url, executeTime);
        if (executeTime > 0) {
            Object[] paramss = { executeTime, url, result };
            logger.info("executeTime={}ms,url={},result={}", paramss);
        }
        return result;
    }
    
    public static String doPost(String url,Map<String,String> params) throws ClientProtocolException, IOException {
    	 logger.info("url={},inputData={}", url,params );

         StopWatch clock = new StopWatch();
         clock.start(); // 计时开始
    	if(StringUtils.isEmpty(url)){
            return null;
        }
    	
        List<NameValuePair> pairs = null;
        if(params != null && !params.isEmpty()){
            pairs = new ArrayList<NameValuePair>(params.size());
            for(Map.Entry<String,String> entry : params.entrySet()){
                String value = entry.getValue();
                if(value != null){
                    pairs.add(new BasicNameValuePair(entry.getKey(),value));
                }
            }
        }
        HttpPost httpPost = new HttpPost(url);
        if(pairs != null && pairs.size() > 0){
            httpPost.setEntity(new UrlEncodedFormEntity(pairs,CHARSET));
        }
        CloseableHttpResponse response = httpclient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            httpPost.abort();
            throw new RuntimeException("HttpClient,error status code :" + statusCode);
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        if (entity != null){
            result = EntityUtils.toString(entity, CHARSET);
        }
        EntityUtils.consume(entity);
        response.close();
        
        clock.stop(); // 计时结束
        long executeTime = clock.getTime();

        logger.debug("url={},executeTime={}ms", url, executeTime);
        if (executeTime > 0) {
            Object[] paramss = { executeTime, url, result };
            logger.info("executeTime={}ms,url={},result={}", paramss);
        }
        return result;
    }
    
    public static void main(String[] args) throws IOException {
        String url = "http://121.40.215.72:9080/phoenix-pay-server/test/genTradeNo";
        Map<String,String> params = Maps.newHashMap();
        params.put("trade_no", "0120150408111803000011");
		params.put("trade_code", "2001");
		params.put("summary", "投标退款：" + "012015040811180300001");
		params.put("amount", 100 + "");
		params.put("identity_id", null);
        
		doPost(url, params);
    }
}
