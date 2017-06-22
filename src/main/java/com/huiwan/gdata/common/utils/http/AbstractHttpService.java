package com.huiwan.gdata.common.utils.http;

import java.io.IOException;
import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHttpService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected CloseableHttpClient client = HttpClients.custom()
			.setConnectionManager(new PoolingHttpClientConnectionManager())
			.build();

	public String post(String url, String content) throws IOException {
		return post(URI.create(url), new StringEntity(content));
	}

	public String post(String url, String content, Header... headers)
			throws IOException {
		return post(URI.create(url), new StringEntity(content), headers);
	}

	public String post(URI url, HttpEntity entity) throws IOException {
		HttpPost post = new HttpPost(url);
		post.setEntity(entity);
		try {
			HttpResponse httpResponse = client.execute(post);
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				logger.warn("not 200: {}", httpResponse.getStatusLine());
			}
			return EntityUtils.toString(httpResponse.getEntity());
		} finally {
			post.releaseConnection();
		}
	}

	public CloseableHttpClient getClient() {
		return client;
	}

	public String post(URI url, HttpEntity entity, Header... headers)
			throws IOException {
		HttpPost post = new HttpPost(url);
		for (Header header : headers) {
			post.addHeader(header);
		}
		post.setEntity(entity);
		try {
			HttpResponse httpResponse = client.execute(post);
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				logger.warn("not 200: {}", httpResponse.getStatusLine());
			}
			return EntityUtils.toString(httpResponse.getEntity());
		} finally {
			post.releaseConnection();
		}
	}

	protected String get(URI url) throws IOException {
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse httpResponse = client.execute(get);
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				logger.warn("not 200: {}", httpResponse.getStatusLine());
			}
			return EntityUtils.toString(httpResponse.getEntity());
		} finally {
			get.releaseConnection();
		}
	}

	protected String getAndReturnString(URI url) throws IOException {
		HttpGet get = new HttpGet(url);
		try {
			return EntityUtils.toString(client.execute(get).getEntity());
		} finally {
			get.releaseConnection();
		}
	}
}
