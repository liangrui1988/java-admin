package com.huiwan.gdata.comm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseConatrollerTest {
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
			
			HttpClientContext  httpContext = new HttpClientContext();
			 Registry<CookieSpecProvider> registry = RegistryBuilder
				        .<CookieSpecProvider> create()
				        .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
				        .register(CookieSpecs.BROWSER_COMPATIBILITY,
				            new BrowserCompatSpecFactory()).build();
			httpContext.setCookieSpecRegistry(registry);
			CookieStore cookieStore=simpleLogin();
			httpContext.setCookieStore(cookieStore);

			HttpResponse httpResponse = client.execute(get,httpContext);
			rsult = EntityUtils.toString(httpResponse.getEntity());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rsult;

	}
	
	public static void main(String[] args) {
		
		

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
			
			HttpClientContext  httpContext = new HttpClientContext();
			 Registry<CookieSpecProvider> registry = RegistryBuilder
				        .<CookieSpecProvider> create()
				        .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
				        .register(CookieSpecs.BROWSER_COMPATIBILITY,
				            new BrowserCompatSpecFactory()).build();
			httpContext.setCookieSpecRegistry(registry);
			CookieStore cookieStore=simpleLogin();
			httpContext.setCookieStore(cookieStore);

			
			HttpResponse httpResponse = client.execute(post,httpContext);
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
	
	
	private  CookieStore setCookieStoreHandler(HttpResponse httpResponse) {
		CookieStore   cookieStore = new BasicCookieStore();
	    // JSESSIONID
//	    String setCookie = httpResponse.getFirstHeader("Set-Cookie")
//	    	
//	        .getValue();
	    
	    Header[] headers= httpResponse.getHeaders("Set-Cookie");
	    
	    if(headers!=null&&headers.length>0){
	    	for(Header h:headers){
	    		String ckV=h.getValue();
	    		String name=h.getName();
	    		
	    		if(ckV.startsWith("rememberMe")){
	    			  String rememberMe = ckV.substring("rememberMexxxxxx=".length(),
	    					  ckV.indexOf(";"));
	    				    // 新建一个Cookie
	    				    BasicClientCookie rememberMeC = new BasicClientCookie("rememberMe",
	    				    		rememberMe);
//	    				    // 新建一个Cookie
//	    				    BasicClientCookie cookie = new BasicClientCookie("Set-Cookie",
//	    				    		rememberMe);
	    				    rememberMeC.setPath("/");
	    				    System.out.println("rememberMe:"+rememberMe);

	    				    rememberMeC.setExpiryDate(new Date(new Date().getTime()+10000000));
	    				    
	    				    rememberMeC.setVersion(1);
	    				    rememberMeC.setDomain("localhost");
	    				    rememberMeC.setPath("/");
	    				    
	    				    cookieStore.addCookie(rememberMeC);
	    		}
	    		
	    		if(ckV.startsWith("sid")){
	    			  String rememberMe = ckV.substring("sid=".length(),
	    					  ckV.indexOf(";"));
	    				    // 新建一个Cookie
	    				    BasicClientCookie rememberMeC = new BasicClientCookie("sid",
	    				    		rememberMe);
//	    				    // 新建一个Cookie
//	    				    BasicClientCookie cookie = new BasicClientCookie("Set-Cookie",
//	    				    		rememberMe);
	    				    rememberMeC.setPath("/");
	    				    System.out.println("sid:"+rememberMe);
	    				    rememberMeC.setVersion(1);
	    				    rememberMeC.setDomain("localhost");
	    				    rememberMeC.setPath("/");

	    				    rememberMeC.setExpiryDate(new Date(new Date().getTime()+10000000));

	    				    cookieStore.addCookie(rememberMeC);
	    		}
	    	}
	    }
	    
	    
	    
//	    System.out.println("setCookie:"+setCookie);
//	    String JSESSIONID = setCookie.substring("sid=".length(),
//	        setCookie.indexOf(";"));

	    
	    
//	    //
//	    String setCookie2 = httpResponse.getFirstHeader("Set-Cookie")
//		        .getValue();
	    
	  
	   // cookie.setVersion(0);
	   // cookie.setDomain("127.0.0.1");

		return cookieStore;
	  }

	public CookieStore simpleLogin() throws ClientProtocolException, IOException{
		String url = http + "/login";
		String parma = "userName=admin&password=admin&rememberMe=true";
		CookieStore cookieStore = null;
		HttpPost post = new HttpPost(url);
		StringEntity reqEntity;

		reqEntity = new StringEntity(parma, ContentType.create(
				"application/x-www-form-urlencoded", Consts.UTF_8));
		post.setEntity(reqEntity);
		HttpResponse httpResponse = client.execute(post);
		
		System.out.println("result:"+EntityUtils.toString(httpResponse.getEntity()));
		cookieStore = setCookieStoreHandler(httpResponse);

		return cookieStore;
		
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
