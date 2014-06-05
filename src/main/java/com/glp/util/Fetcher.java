package com.glp.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

/**
 * http client 
 * @author 
 * @date 2013-12-19
 */
public class Fetcher {

	HttpClient client;
	
	@PostConstruct
	private void init() {
		PoolingClientConnectionManager clientConnectionManager = new PoolingClientConnectionManager();
		clientConnectionManager.setMaxTotal(200);
		client = new DefaultHttpClient(clientConnectionManager);
		HttpParams params = client.getParams();
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); // 连接超时
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000); // 读取超时
		params.setParameter(CoreProtocolPNames.USER_AGENT, ""); //user agent 
	}
	
	/**
	 * default method
	 * @param url
	 * @param defaultCharset
	 * @return
	 * @throws IOException
	 */
	public String get(String url,String defaultCharset) throws IOException{
		if(defaultCharset == null){
			defaultCharset = "UTF-8";
		}
		HttpGet get = new HttpGet(url);
		try{
			HttpResponse response = client.execute(get);
			String str = IOUtils.toString(response.getEntity().getContent(), defaultCharset);
			return str;
		}finally{
			get.releaseConnection();
		}
	}
	
	/**
	 * post method
	 * @param url
	 * @param entity
	 * @param defaultCharset
	 * @return
	 * @throws IOException
	 */
	public String post(String url,HttpEntity entity,String defaultCharset) throws IOException{
		if(defaultCharset == null){
			defaultCharset = "UTF-8";
		}
		HttpPost post = new HttpPost(url);
		post.setEntity(entity);
		try{
			HttpResponse response = client.execute(post);
			return IOUtils.toString(response.getEntity().getContent(), defaultCharset);
		}finally{
			post.releaseConnection();
		}
	}

	/**
	 * post method
	 * @param url
	 * @param entity
	 * @param defaultCharset
	 * @return
	 * @throws IOException
	 */
	public String postByHeader(String url, HttpEntity entity, String headName, String headData, String defaultCharset) throws IOException{
		if(defaultCharset == null){
			defaultCharset = "UTF-8";
		}
		HttpPost post = new HttpPost(url);
		if(entity != null){
			post.setEntity(entity);
		}
		post.addHeader(headName, headData);
		try{
			HttpResponse response = client.execute(post);
			return IOUtils.toString(response.getEntity().getContent(), defaultCharset);
		}finally{
			post.releaseConnection();
		}
	}
	
	/**
	 * get post's response header
	 * @param url
	 * @param entity
	 * @param headers
	 * @param defaultCharset
	 * @return get 
	 * @throws IOException
	 */
	public Header[] getPostResponseHeader(String url, HttpEntity entity, Header[] headers){
		Header[] headerArr = null;
		HttpPost post = new HttpPost(url);
		if(entity != null){
			post.setEntity(entity);
		}
		post.setHeaders(headers);
		try{
			HttpResponse response;
			response = client.execute(post);
			headerArr = response.getAllHeaders();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			post.releaseConnection();
		}
		return headerArr;
	}
	
	/**
	 * test login facebook
	 */
	public void testLoginFaceBook(){
		String loginUrl = "http://www.wand.com/core/reg_addProfile.aspx";
		HttpPost post = new HttpPost(loginUrl);
		
		try{
			
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        nvps.add(new BasicNameValuePair("__EVENTTARGET", ""));
	        nvps.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
	        nvps.add(new BasicNameValuePair("ctl00$wandMaster$ContactInfo$lstcountry", "CH"));
	        HttpEntity entity = new UrlEncodedFormEntity(nvps); 
			post.setEntity(entity);
			
			HttpResponse response = client.execute(post);
			if(response.getEntity() != null &&  response.getEntity().getContentLength() != 0){// 
				String content2 =  IOUtils.toString(response.getEntity().getContent(), "UTF-8");
				System.out.println("content2:" + content2);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			post.releaseConnection();
		}	
	}
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		Fetcher fetcher = new Fetcher();
		fetcher.init();
		
		fetcher.testLoginFaceBook();
	}
}
