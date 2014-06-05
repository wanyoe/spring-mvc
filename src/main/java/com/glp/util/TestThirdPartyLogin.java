package com.glp.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestThirdPartyLogin {
	
	HttpClient client;
	
	@PostConstruct
	private void init() {
		PoolingClientConnectionManager clientConnectionManager = new PoolingClientConnectionManager();
		clientConnectionManager.setMaxTotal(200);
		client = new DefaultHttpClient(clientConnectionManager);
		HttpParams params = client.getParams();
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000); // 连接超时
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000); // 读取超时
		params.setParameter(CoreProtocolPNames.USER_AGENT, ""); //user agent 
	}
	
	/**
	 * test login facebook
	 */
	public void testLoginFaceBook(){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient = wrapClient(httpclient);
		String loginUrl = "https://www.facebook.com/login.php?login_attempt=1";
		HttpPost post = new HttpPost(loginUrl);
		HttpGet get = new HttpGet(loginUrl);
		try{
			httpclient.execute(get);
			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
			StringBuffer sbCookies = new StringBuffer();
			if (!cookies.isEmpty()) {
			    for (int i = 0; i < cookies.size(); i++) {
			    	if(i != 0){
			    		sbCookies.append(";");
			    	}
			    	sbCookies.append(cookies.get(i).getName()).append("=").append(cookies.get(i).getValue().toString());
			    }
			}
			// release get
			get.releaseConnection();
			
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        nvps.add(new BasicNameValuePair("default_persistent", "0"));
	        nvps.add(new BasicNameValuePair("email", "wangyouyi1983@hotmail.com"));
	        nvps.add(new BasicNameValuePair("pass", "nikond80dd"));
	        HttpEntity entity = new UrlEncodedFormEntity(nvps); 
			post.setEntity(entity);
			post.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
			post.setHeader("accept-encoding", "gzip,deflate,sdch");
			post.setHeader("version", "HTTP/1.1");
			post.setHeader("cookie", sbCookies.toString());
			HttpResponse response = httpclient.execute(post);
			if(response.getEntity() != null &&  response.getEntity().getContentLength() != 0){// 
				String content2 = EntityUtils.toString(new GzipDecompressingEntity(response.getEntity()), "UTF-8");
				System.out.println("content2:" + content2);
			}
			Header[] headers = response.getAllHeaders();
			StringBuffer sb = new StringBuffer();
			if(headers.length > 0){
				for(int i = 0; i < headers.length; i++){
					Header head = headers[i];
					if(i != 0){
						sb.append("|||");
					}
					sb.append("header name:").append(head.getName()).append(",header value:" + head.getValue());
				}
			}
			System.out.println("headers:" + sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			get.releaseConnection();
			post.releaseConnection();
		}	
	}
	
	/**
	 * test login twitter
	 * step1:
	 * https://twitter.com/login  get authenticity_token
	 * step2
	 * https://twitter.com/sessions  post login infor
	 * get header name "location" equal's "https://twitter.com/sessions/new" or not   
	 */
	public void testLoginTwitter(){
		// 
		PoolingClientConnectionManager clientConnectionManager = new PoolingClientConnectionManager();
		clientConnectionManager.setMaxTotal(200);
		DefaultHttpClient httpclient = new DefaultHttpClient(clientConnectionManager);
		HttpParams params = httpclient.getParams();
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000); // 连接超时
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000); // 读取超时
		params.setParameter(CoreProtocolPNames.USER_AGENT, ""); //user agent 
		String loginUrl = "https://twitter.com/login";
		String loginSessionUrl = "https://twitter.com/sessions";
		HttpPost post = new HttpPost(loginSessionUrl);
		HttpGet get = new HttpGet(loginUrl);
		try{
			// get authenticity_token and get cookie
			String authenticityToken = "";
			HttpResponse responseGet = httpclient.execute(get);
			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
			StringBuffer sbCookies = new StringBuffer();
			if (!cookies.isEmpty()) {
			    for (int i = 0; i < cookies.size(); i++) {
			    	if(i != 0){
			    		sbCookies.append(";");
			    	}
			    	sbCookies.append(cookies.get(i).getName()).append("=").append(cookies.get(i).getValue().toString());
			    }
			}
			
			// get responseGet's content
			if(responseGet.getEntity() != null &&  responseGet.getEntity().getContentLength() != 0){// 
//				String content = EntityUtils.toString(new GzipDecompressingEntity(responseGet.getEntity()), HTTP.UTF_8);
				String content = IOUtils.toString(responseGet.getEntity().getContent(), "UTF-8");
				// parse content to get authenticity_token
				Document doc = Jsoup.parse(content); 
				Elements elements = doc.select("input[name=authenticity_token]");
				if(elements != null && elements.size() > 0){
					Element element = elements.get(0);
					authenticityToken = element.attr("value");
				}
			}
			System.out.println("authenticity_token:" + authenticityToken);
			// check authenticityToken is blank or not
			if(StringUtils.isBlank(authenticityToken)){
				return;
			}
			// post data to session to login
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        nvps.add(new BasicNameValuePair("authenticity_token", authenticityToken));
	        nvps.add(new BasicNameValuePair("session[username_or_email]", "wangyouyi1983@hotmail.com"));
	        nvps.add(new BasicNameValuePair("session[password]", "nikond80dd"));
	        HttpEntity entity = new UrlEncodedFormEntity(nvps); 
			post.setEntity(entity);
			post.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
			post.setHeader("accept-encoding", "gzip,deflate,sdch");
			post.setHeader("version", "HTTP/1.1");
			post.setHeader("cookie", sbCookies.toString());
			HttpResponse response2 = client.execute(post);
			if(response2.getEntity() != null &&  response2.getEntity().getContentLength() != 0){// 
//				String content2 = IOUtils.toString(response2.getEntity().getContent(), "UTF-8");
				String content2 = EntityUtils.toString(new GzipDecompressingEntity(response2.getEntity()), "UTF-8");
				System.out.println("content2:" + content2);
			}
			Header[] headers = response2.getAllHeaders();
			StringBuffer sb = new StringBuffer();
			if(headers.length > 0){
				for(int i = 0; i < headers.length; i++){
					Header head = headers[i];
					if(i != 0){
						sb.append("|||");
					}
					sb.append("header name:").append(head.getName()).append(",header value:" + head.getValue());
				}
			}
			System.out.println("headers:" + sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			get.releaseConnection();
			post.releaseConnection();
		}
	}
	
	
	/**
	 * get login linkedin
	 * step:
	 * https://www.linkedin.com/uas/login
	 * id:session_password-login name:session_key
	 * id:session_password-login name:session_password
	 * id:trk-login name:trk
	 * id:loginCsrfParam-login name:loginCsrfParam
	 * id:csrfToken-login name:csrfToken
	 * id:sourceAlias-login name:sourceAlias
	 * step2:
	 * https://www.linkedin.com/uas/login-submit
	 */
	public void testLoginLinkedin(){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String loginUrl = "https://www.linkedin.com/uas/login";
		String loginSubmitUrl = "https://www.linkedin.com/uas/login-submit";
		HttpPost post = new HttpPost(loginSubmitUrl);
		HttpGet get = new HttpGet(loginUrl);
		try{
			String trk = "";
			String loginCsrfParam = "";
			String csrfToken = "";
			String sourceAlias = "";
			HttpResponse responseGet = httpclient.execute(get);
			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
			StringBuffer sbCookies = new StringBuffer();
			if (!cookies.isEmpty()) {
			    for (int i = 0; i < cookies.size(); i++) {
			    	if(i != 0){
			    		sbCookies.append(";");
			    	}
			    	sbCookies.append(cookies.get(i).getName()).append("=").append(cookies.get(i).getValue().toString());
			    }
			}
			// get responseGet's content
			if(responseGet.getEntity() != null &&  responseGet.getEntity().getContentLength() != 0){// 
//				String content = EntityUtils.toString(new GzipDecompressingEntity(responseGet.getEntity()), HTTP.UTF_8);
				String content = IOUtils.toString(responseGet.getEntity().getContent(), "UTF-8");
				// parse content to get authenticity_token
				Document doc = Jsoup.parse(content); 
				Elements trkElements = doc.select("#trk-login");
				Elements loginCsrfParamElements = doc.select("#loginCsrfParam-login");
				Elements csrfTokenElements = doc.select("#csrfToken-login");
				Elements sourceAliasElements = doc.select("#sourceAlias-login");
				// get trk
				if(trkElements != null && trkElements.size() > 0){
					Element element = trkElements.get(0);
					trk = element.attr("value");
				}
				// get loginCsrfParam
				if(loginCsrfParamElements != null && loginCsrfParamElements.size() > 0){
					Element element = loginCsrfParamElements.get(0);
					loginCsrfParam = element.attr("value");
				}
				// get csrfTokenElements
				if(csrfTokenElements != null && csrfTokenElements.size() > 0){
					Element element = csrfTokenElements.get(0);
					csrfToken = element.attr("value");
				}
				// get sourceAlias
				if(sourceAliasElements != null && sourceAliasElements.size() > 0){
					Element element = sourceAliasElements.get(0);
					sourceAlias = element.attr("value");
				}
			}
			System.out.println("trk:" + trk + ";loginCsrfParam:" + loginCsrfParam + ";csrfToken:" + csrfToken + ";sourceAlias:" + sourceAlias);
			// check is blank or not
			if(StringUtils.isBlank(loginCsrfParam) || StringUtils.isBlank(csrfToken) || StringUtils.isBlank(sourceAlias)){
				return;
			}
			// post data to session to login
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
			nvps.add(new BasicNameValuePair("session_key", "wangyouyi1983@hotmail.com"));
	        nvps.add(new BasicNameValuePair("session_password", "nikond80"));
	        nvps.add(new BasicNameValuePair("trk", trk));
	        nvps.add(new BasicNameValuePair("loginCsrfParam", loginCsrfParam));
	        nvps.add(new BasicNameValuePair("csrfToken", csrfToken));
	        nvps.add(new BasicNameValuePair("sourceAlias", sourceAlias));
	        HttpEntity entity = new UrlEncodedFormEntity(nvps); 
			post.setEntity(entity);
			post.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
			post.setHeader("accept-encoding", "gzip,deflate,sdch");
			post.setHeader("version", "HTTP/1.1");
			post.setHeader("cookie", sbCookies.toString());
			HttpResponse response2 = client.execute(post);
			if(response2.getEntity() != null &&  response2.getEntity().getContentLength() != 0){// 
//						String content2 = IOUtils.toString(response2.getEntity().getContent(), "UTF-8");
				String content2 = EntityUtils.toString(new GzipDecompressingEntity(response2.getEntity()), "UTF-8");
				System.out.println("content2:" + content2);
			}
			Header[] headers = response2.getAllHeaders();
			StringBuffer sb = new StringBuffer();
			if(headers.length > 0){
				for(int i = 0; i < headers.length; i++){
					Header head = headers[i];
					if(i != 0){
						sb.append("|||");
					}
					sb.append("header name:").append(head.getName()).append(",header value:" + head.getValue());
				}
			}
			System.out.println("headers:" + sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			get.releaseConnection();
			post.releaseConnection();
		}
	}
	
	/**
	 * get login google
	 * step:
	 * https://accounts.google.com/ServiceLogin
	 * step2:
	 *  https://accounts.google.com/ServiceLoginAuth
	 */
	public void testLoginGoogle(){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String loginUrl = "https://accounts.google.com/ServiceLogin";
		String loginAuthUrl = "https://accounts.google.com/ServiceLoginAuth";
		HttpPost post = new HttpPost(loginAuthUrl);
		HttpGet get = new HttpGet(loginUrl);
		try{
			String galx = "";
			String bgresponse = "";
			String pstMsg = "1";
			String dnConn = "";
			String checkConnection = "youtube:351:1";
			String checkedDomains = "youtube";
			HttpResponse responseGet = httpclient.execute(get);
			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
			StringBuffer sbCookies = new StringBuffer();
			if (!cookies.isEmpty()) {
			    for (int i = 0; i < cookies.size(); i++) {
			    	if(i != 0){
			    		sbCookies.append(";");
			    	}
			    	sbCookies.append(cookies.get(i).getName()).append("=").append(cookies.get(i).getValue().toString());
			    }
			}
			// get responseGet's content
			if(responseGet.getEntity() != null &&  responseGet.getEntity().getContentLength() != 0){// 
//				String content = EntityUtils.toString(new GzipDecompressingEntity(responseGet.getEntity()), HTTP.UTF_8);
				String content = IOUtils.toString(responseGet.getEntity().getContent(), "UTF-8");
				// parse content to get authenticity_token
				Document doc = Jsoup.parse(content); 
				Elements galxElements = doc.select("input[name=galx]");
				Elements bgresponseElements = doc.select("input#bgresponse");
				// get galx
				if(galxElements != null && galxElements.size() > 0){
					Element element = galxElements.get(0);
					galx = element.attr("value");
				}
				// get bgresponse
				if(bgresponseElements != null && bgresponseElements.size() > 0){
					Element element = bgresponseElements.get(0);
					bgresponse = element.attr("value");
				}
			}
			System.out.println("galx:" + galx + ";bgresponse:" + bgresponse);
			// check is blank or not
			if(StringUtils.isBlank(galx)){
				return;
			}
			// post data to session to login
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
			nvps.add(new BasicNameValuePair("Email", "wangyouyi1983@hotmail.com"));
	        nvps.add(new BasicNameValuePair("Passwd", "nikond80dd"));
	        nvps.add(new BasicNameValuePair("GALX", galx));
	        nvps.add(new BasicNameValuePair("bgresponse", bgresponse));
	        nvps.add(new BasicNameValuePair("pstMsg", pstMsg));
	        nvps.add(new BasicNameValuePair("dnConn", dnConn));
	        nvps.add(new BasicNameValuePair("checkConnection", checkConnection));
	        nvps.add(new BasicNameValuePair("checkedDomains", checkedDomains));
	        HttpEntity entity = new UrlEncodedFormEntity(nvps); 
			post.setEntity(entity);
			post.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
			post.setHeader("accept-encoding", "gzip,deflate,sdch");
			post.setHeader("version", "HTTP/1.1");
			post.setHeader("cookie", sbCookies.toString());
			HttpResponse response2 = client.execute(post);
			if(response2.getEntity() != null &&  response2.getEntity().getContentLength() != 0){// 
//						String content2 = IOUtils.toString(response2.getEntity().getContent(), "UTF-8");
				String content2 = EntityUtils.toString(new GzipDecompressingEntity(response2.getEntity()), "UTF-8");
				System.out.println("content2:" + content2);
			}
			Header[] headers = response2.getAllHeaders();
			StringBuffer sb = new StringBuffer();
			if(headers.length > 0){
				for(int i = 0; i < headers.length; i++){
					Header head = headers[i];
					if(i != 0){
						sb.append("|||");
					}
					sb.append("header name:").append(head.getName()).append(",header value:" + head.getValue());
				}
			}
			System.out.println("headers:" + sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			get.releaseConnection();
			post.releaseConnection();
		}
	}
	
	public static void main(String[] args){
		TestThirdPartyLogin thridPartyLogin = new TestThirdPartyLogin();
		thridPartyLogin.init();
		//
		thridPartyLogin.testLoginFaceBook();
	}
	
	/**
	 * get https client
	 * @param base
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private DefaultHttpClient wrapClient(DefaultHttpClient base) {
	    try {
	        SSLContext ctx = SSLContext.getInstance("TLS");
	        X509TrustManager tm = new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
	            public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
	            public X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	        };
	        ctx.init(null, new TrustManager[]{tm}, null);
	        SSLSocketFactory ssf = new SSLSocketFactory(ctx);
	        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        ClientConnectionManager ccm = base.getConnectionManager();
	        SchemeRegistry sr = ccm.getSchemeRegistry();
	        sr.register(new Scheme("https", ssf, 443));
	        // set params
	        HttpParams params = base.getParams();
			params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000); // 连接超时
			params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000); // 读取超时
			params.setParameter(CoreProtocolPNames.USER_AGENT, ""); //user agent
	        return new DefaultHttpClient(ccm, base.getParams());
	    } catch (Exception ex) {
	        return null;
	    }
	}


}
