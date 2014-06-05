package com.glp.admin.security;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.eyeieye.melody.web.adapter.AnnotationMethodHandlerInterceptorAdapter;
import com.eyeieye.melody.web.cookyjar.Cookyjar;

public class AdminAuthorityHandlerInterceptor extends
		AnnotationMethodHandlerInterceptorAdapter {

	private static final Integer placeholder = Integer.valueOf(0);

	@Override
	public void preInvoke(Method handlerMethod, Object handler,
			ServletWebRequest webRequest) {
		Cookyjar cookyjar = (Cookyjar) webRequest.getAttribute(
				Cookyjar.CookyjarInRequest, RequestAttributes.SCOPE_REQUEST);
		if (cookyjar == null) {
			throw new IllegalStateException("cookyjar not find in request:"+webRequest.getRequest().getRequestURI());
		}
		AdminAgent agent = (AdminAgent) cookyjar
				.getObject(AdminAgent.class);
		if (!pass(agent, handlerMethod, handler)) {
			throw new AdminAccessDeniedException();
		}
	}

	private Map<Method, AdminPermission[]> caches = new ConcurrentHashMap<Method, AdminPermission[]>();

	private Map<Method, Integer> noControlCaches = new ConcurrentHashMap<Method, Integer>();

	private boolean pass(AdminAgent user, Method handlerMethod,
			Object handler) {
		AdminPermission[] permissions = null;
		permissions = this.caches.get(handlerMethod);
		if (permissions == null) {
			if (noControlCaches.containsKey(handlerMethod)) {
				return true;
			}
			AdminAccess access = AnnotationUtils.getAnnotation(handlerMethod,
                    AdminAccess.class);
			if(access == null){
				access = AnnotationUtils.findAnnotation(handler.getClass(), AdminAccess.class);
			}
			if (access == null) {
				noControlCaches.put(handlerMethod, placeholder);
				return true;
			}
			permissions = access.value();
			this.caches.put(handlerMethod, permissions);
		}
		if (permissions.length == 0) {
			return user != null;
		}
		if (user != null) {
			for (AdminPermission em : permissions) {
				if (user.havePermission(em)) {
					return true;
				}
			}
		}
		return false;
	}
}
