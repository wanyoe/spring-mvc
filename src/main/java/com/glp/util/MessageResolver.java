package com.glp.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

public class MessageResolver {
	
	/**
	 * get i18n message
	 * @param request HttpServletRequest
	 * @param code: error code
	 * @param args:code message args
	 * @return
	 * @see org.springframework.context.MessageSource#getMessage(String, Object[], Locale)
	 */
	public static String getMessage(HttpServletRequest request, String code, Object... args) {
		WebApplicationContext messageSource = RequestContextUtils.getWebApplicationContext(request);
		if (messageSource == null) {
			throw new IllegalStateException("WebApplicationContext not found!");
		}
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale locale;
		if (localeResolver != null) {
			locale = localeResolver.resolveLocale(request);
		} else {
			locale = request.getLocale();
		}
		return messageSource.getMessage(code, args, locale);
	}
}
