package com.rui.pro1.comm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseControllerTest {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public String http = "http://localhost:9808";

	static CloseableHttpClient client = HttpClients.custom()
			.setConnectionManager(new PoolingHttpClientConnectionManager())
			.build();

	public String getReq(String url) {
		logger.info("req url>>>{}",url);
		HttpGet get = new HttpGet(url);
		String rsult = null;
		try {
			HttpResponse httpResponse = client.execute(get);
			rsult = EntityUtils.toString(httpResponse.getEntity());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rsult;

	}

	/**
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public String postReq(String url, String param) {
		logger.info("req url>>>\n{}{}",url,param);
		HttpPost post = new HttpPost(url);
		StringEntity reqEntity;
		String rsult = "";
		try {
			reqEntity = new StringEntity(param,ContentType.create("application/x-www-form-urlencoded",Consts.UTF_8));
			//reqEntity.setContentType("application/x-www-form-urlencoded");
			post.setEntity(reqEntity);
			HttpResponse httpResponse = client.execute(post);
			rsult = EntityUtils.toString(httpResponse.getEntity());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rsult;
	}
	
	
	public String postReq(String url) {
		logger.info("req url>>>\n{}{}",url);
		HttpPost post = new HttpPost(url);
		StringEntity reqEntity;
		String rsult = "";
		try {
		//	reqEntity = new StringEntity(param,ContentType.create("application/x-www-form-urlencoded",Consts.UTF_8));
			
			reqEntity = new StringEntity("",ContentType.create("application/x-www-form-urlencoded",Consts.UTF_8));
			//reqEntity.setContentType("application/x-www-form-urlencoded");
			post.setEntity(reqEntity);
			HttpResponse httpResponse = client.execute(post);
			rsult = EntityUtils.toString(httpResponse.getEntity());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rsult;
	}
	
	
	//Gson gson = new Gson();
//	String json = gson.toJson(data);
//	StringEntity entity = new StringEntity(json, "utf-8");
//	httpClient.post(context, url, entity, "application/json;charset=utf-8", new TextHttpResponseHandler() ... );
	
	
	public void printResult(String str) {
		System.out.println("-----------printResult   start ---------------\n");
		System.out.println(str);
		System.out.println("\n---------printResult    end ------------------");

	}
}
