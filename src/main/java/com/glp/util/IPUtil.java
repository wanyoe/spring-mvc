package com.glp.util;

import javax.servlet.http.HttpServletRequest;

public abstract class IPUtil {

	public static String getRealIP(HttpServletRequest req){
		String realip = req.getHeader("X-Real-IP");
		if(realip == null){
			realip = req.getRemoteAddr();
		}
		return realip;
	}
}
