package com.glp.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * 全局公用函数
 * @author springpig
 * @date 2013-11-26
 */
public abstract class GlobalUtil {
	
	
	
	/**
	 * 获取ip地址
	 * @param request request对象
	 * @return
	 */
	public static String getRealIP(HttpServletRequest request){
		String realip = request.getHeader("X-Real-IP");
		if(realip == null){
			realip = request.getRemoteAddr();
		}
		return realip;
	}
	
	/**
	 * 返回输入页面上的Map
	 * @param returnMap
	 * @return
	 * map格式：{success:1/0,message:{error_name:error_message}}
	 
	public static Map<String, Object> getJsonMap(boolean isSuccess, Map<String, String> resultMap){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("isSucccess", isSuccess);
		if(!isSuccess){
			returnMap.put("message", resultMap);
		}
		return returnMap;
	}*/
	
	/**
	 * get object's json map
	 * @param isSuccess:boolean true or false
	 * @param object:object
	 * @return
	 */
	public static Map<String, Object> getJsonObjectMap(boolean isSuccess, Object object){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("isSuccess", isSuccess);
		if(object != null){
			returnMap.put("message", object);
		}
		return returnMap;
	}
	
	/**
	 * get error map
	 * @param code
	 * @param message
	 * @return
	 */
	public static Map<String, String> getErrorMap(String code, String message){
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("errorCode", code);
		errorMap.put("error", message);
		return errorMap;
	}
	
	/**
	 * 根据user_id和code_type，生成MD5token
	 * @param user_id:user id
	 * @param code_type:code type
	 * @return
	 */
	public static String getMD5Token(String user_id, String code_type, String curTimeMillis){
		return MD5Encrypt.encode(getToken(user_id, code_type, curTimeMillis));
	}
	
	/**
	 * 根据user_id和code_type，生成MD5token
	 * @param user_id:用户id
	 * @param code_type:token 类别
	 * @return
	 */
	public static String getToken(String user_id, String code_type, String curTimeMillis){
		return user_id + "_" + GlobalConstants.COOKIE_VERFIY_KEY + "_" + code_type + "_" + curTimeMillis ;
	}
	
	/**
	 * 生成token验证的key
	 * @param user_id:用户id
	 * @param code_type:token类型
	 * @param curTimeMillis:系统时间
	 * @return
	 */
	public static String getTokenKey(String user_id, String code_type, String curTimeMillis){
		return user_id + "_" +  curTimeMillis + "_" + code_type;
	}
	
	/**
	 * 判断token跟key是否匹配
	 * @param key:key的结构式 user.id_curTimeMillis_codetype;
	 * @param token:根据科研结构生成的token
	 * @return
	 */
	public static boolean isTokenOk(String key, String token){
		// 判断key是否ok
		if(key == null || key.trim().length() == 0 || token == null || token.trim().length() == 0)
			return false;
		String[] keyValues = key.split("_");
		if(keyValues.length != 3)
			return false;
		String verify_token = GlobalUtil.getMD5Token(keyValues[0], keyValues[2], keyValues[1]);
		return StringUtils.equals(token, verify_token);
	}
	
	/**
	 * 根据名称获取cookie
	 * @param request
	 * @param cookieName
	 * @return null, not exists;else cookie
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName){
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies == null){
			return cookie;
		}
		// 获取cookie
		for(int i = 0; i < cookies.length; i++){// 
			Cookie c = cookies[i];
			if(c.getName().equalsIgnoreCase(cookieName)){
				cookie = c;
				break;
			}
		}
		return cookie;
	}
	
	/**
	 * 验证cookie是否飞ok
	 * @param cookie token|key,key的结构式 user.id_curTimeMillis_codetype;
	 * @return
	 */
	public static boolean isCookieOk(Cookie cookie){
		if(cookie == null)
			return false;
		String[] cookieValues = cookie.getValue().split("\\|");
		if(cookieValues.length != 2){
			return false;
		}
		String token = cookieValues[0];
		String key = cookieValues[1];
		// 判断key是否ok
		String[] keyValues = key.split("_");
		if(keyValues.length != 3)
			return false;
		String verify_token = GlobalUtil.getMD5Token(keyValues[0], keyValues[2], keyValues[1]);
		return StringUtils.equals(token, verify_token);
	}
	
	/**
	 * get addr's lat and lng by google
	 * @param addr:address
	 * @return 经度、维度
	 */
	public static String[] getCoordinate(Fetcher fetcher, String address, String city, String province, String country) {
		ObjectMapper objMap = new ObjectMapper();
		String[] latLng = new String[2];
		JsonNode jsonNode = null;
		String url = "http://maps.googleapis.com/maps/api/geocode/json";
		try{
			StringBuffer sb = new StringBuffer();
			sb.append(URLEncoder.encode(address, GlobalConstants.CHARSET_UTF8));
			if(StringUtils.isNotBlank(city)){// check city is null or not
				sb.append(",").append(URLEncoder.encode(city, GlobalConstants.CHARSET_UTF8));
			}
			if(StringUtils.isNotBlank(province)){// check province is null or not
				sb.append(",").append(URLEncoder.encode(province, GlobalConstants.CHARSET_UTF8));
			}
			if(StringUtils.isNotBlank(country)){// check country is null or not
				sb.append(",").append(URLEncoder.encode(country, GlobalConstants.CHARSET_UTF8));
			}
			// URIBuilder builder = new URIBuilder(url).addParameter("address", "8520+NE+ALDERWOOD+RD+Suite+D,+OR,+USA").addParameter("sensor", "false");
			URIBuilder builder = new URIBuilder(url).addParameter("address", sb.toString()).addParameter("sensor", "false");
			jsonNode = objMap.readTree(fetcher.get(builder.toString(), null));
			if(jsonNode.get("status") != null && "ok".equalsIgnoreCase(jsonNode.get("status").getTextValue())){
				latLng[0] = jsonNode.get("results").get(0).get("geometry").get("location").get("lng").asText();
				latLng[1] = jsonNode.get("results").get(0).get("geometry").get("location").get("lat").asText();
			}else{
				latLng[0] = "";
				latLng[1] = "";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return latLng;
	}
	
	/**
	 * 过滤搜索的特殊字符串
	 * @param str
	 * @return
	 */
	static Pattern p = Pattern.compile("[`~_!@#$%^*()=|{}':;,\\[\\]<>/?~！@#￥%……&*（）——+—《》|{}【】‘；：”“’。，、？]+");   
	public static String SearchStringFilter(String str){     	  
		Matcher m = p.matcher(str);     
		return m.replaceAll(" ").trim();     
	}   
	
	/**
	 * 去掉object里的前后空格
	 * @param object
	 * @return
	 */
	public static Object trimObject(Object object){
		if(object instanceof Map || object instanceof Set || object instanceof List){
			return object;
		}else{
			Class<? extends Object> tmpClass = object.getClass();
			Field[] tmpField = tmpClass.getDeclaredFields();
			for(int i = 0; i < tmpField.length; i++){
				Field field = tmpField[i];
				String fieldName = field.getName();
				String backName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				String getMethodName = "get" + backName;
				String setMethodName = "set" + backName;
				Method getMethod, setMethod;
				try{
					getMethod = tmpClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(object, new Object[]{});
					if(value == null){
						continue;
					}
					if(value instanceof String){
						value = StringUtils.trim(String.valueOf(value));;
					}
					if("java.lang.String".equals(tmpField[i].getType().getName())){
						setMethod = tmpClass.getMethod(setMethodName, new Class[]{field.getType()});
						setMethod.invoke(object, new Object[]{value});
					}
				}catch(SecurityException e){
					
				}catch(NoSuchMethodException e){
					
				}catch(IllegalArgumentException e){
					
				}catch(IllegalAccessException e){
					
				}catch(InvocationTargetException e){
					
				}
			}
			return object;
		}
	}
	
	
	public static void main(String[] args){
		
		Long c = null;
		System.out.println(new DecimalFormat(",###").format(c));
		
	}
	
	

}
