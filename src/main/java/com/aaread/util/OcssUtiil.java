package com.aaread.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.ksyun.ks3.dto.CannedAccessControlList;
import com.ksyun.ks3.dto.ObjectMetadata;
import com.ksyun.ks3.dto.PutObjectResult;
import com.ksyun.ks3.http.HttpClientConfig;
import com.ksyun.ks3.service.Ks3;
import com.ksyun.ks3.service.Ks3Client;
import com.ksyun.ks3.service.Ks3ClientConfig;
import com.ksyun.ks3.service.Ks3ClientConfig.PROTOCOL;
import com.ksyun.ks3.service.request.PutObjectRequest;

public class OcssUtiil {

	private static Ks3  client;
	

	public static Ks3 getOssClient(){
		
		Ks3ClientConfig config = new Ks3ClientConfig();
		/**
		 * 设置服务地址</br>
		 * 中国（北京）| ks3-cn-beijing.ksyun.com
		 * 中国（上海）| ks3-cn-shanghai.ksyun.com
		 * 中国（香港）| ks3-cn-hk-1.ksyun.com
		 * 美国（圣克拉拉）| ks3-us-west-1.ksyun.com
		*/
		config.setEndpoint("ks3-cn-beijing.ksyun.com");   //此处以北京region为例
		config.setProtocol(PROTOCOL.http);
//		config.setEndpoint("test2");
		/**
		*true表示以   endpoint/{bucket}/{key}的方式访问</br>
		*false表示以  {bucket}.endpoint/{key}的方式访问
		*/
		config.setPathStyleAccess(false);

		HttpClientConfig hconfig = new HttpClientConfig();
		//在HttpClientConfig中可以设置httpclient的相关属性，比如代理，超时，重试等。

		config.setHttpClientConfig(hconfig);
		
		
		if(client == null){
			synchronized (OcssUtiil.class) {
				if(client == null){
					client = new Ks3Client("xMxCWSadY7C4otVbX0b9","isGKlZzj9HeTFpIyTA70r+5si+SPXkoSWi/rxWM6",config);
					return client;
				}
			}
		}
		return client;
	}

	public static String sendFileToOss(File file,String key) {
		 PutObjectRequest request = new PutObjectRequest("aaread",
				 key, file);
		    //上传一个公开文件
		 request.setCannedAcl(CannedAccessControlList.PublicRead);
		 PutObjectResult result = getOssClient().putObject(request);
		 return result.geteTag();
	}
	public static String sendFileToOss( String key,
			InputStream inputStream, ObjectMetadata metadata) {
		PutObjectRequest request = new PutObjectRequest("aaread",key,inputStream
				, metadata);
		//上传一个公开文件
		request.setCannedAcl(CannedAccessControlList.PublicRead);
		PutObjectResult result = getOssClient().putObject(request);
		return result.geteTag();
	}
	
	public static void main(String[] args) {
		File f = new File("C:/Users/wangjie/Pictures/IMG_0015.JPG");
		sendFileToOss(f, "test/IMG_001524.JPG");
	}
	
}
