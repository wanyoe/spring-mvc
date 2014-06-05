package com.glp.security;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.eyeieye.melody.web.adapter.AnnotationMethodHandlerInterceptorAdapter;
import com.eyeieye.melody.web.cookyjar.Cookyjar;


public class UserAuthorityHandlerInterceptor extends
		AnnotationMethodHandlerInterceptorAdapter {

	private static final Integer placeholder = Integer.valueOf(0);

	@Override
	public void preInvoke(Method handlerMethod, Object handler,
			ServletWebRequest webRequest) {
		Cookyjar cookyjar = (Cookyjar) webRequest.getAttribute(
				Cookyjar.CookyjarInRequest, RequestAttributes.SCOPE_REQUEST);
		if (cookyjar == null) {
			UserAccess access = AnnotationUtils.getAnnotation(handlerMethod,
					UserAccess.class);
			//需要权限操作的话
			if(access != null)
				throw new IllegalStateException("cookyjar not find in request:"+webRequest.getRequest().getRequestURI());
			else 
				return;
		}
		UserAgent agent = (UserAgent) cookyjar
				.getObject(UserAgent.class);
		if (!pass(agent, handlerMethod, handler,cookyjar)) {
			throw new UserAccessDeniedException();
		}
	}

	private Map<Method, Permission[]> caches = new ConcurrentHashMap<Method, Permission[]>();

	private Map<Method, Integer> noControlCaches = new ConcurrentHashMap<Method, Integer>();

	private boolean pass(UserAgent user, Method handlerMethod,
			Object handler,Cookyjar cookyjar) {
		Permission[] permissions = null;
		permissions = this.caches.get(handlerMethod);
		if (permissions == null) {
			if (noControlCaches.containsKey(handlerMethod)) {
				return true;
			}
			UserAccess access = AnnotationUtils.getAnnotation(handlerMethod,
					UserAccess.class);
			if (access == null) {
				noControlCaches.put(handlerMethod, placeholder);
				return true;
			}
			permissions = access.value();
			this.caches.put(handlerMethod, permissions);
		}
        if(user != null && user.needRefresh()){
        	/**
            User u = service.findUserById(user.getId());
            if(u != null){
                String status = u.getStatus();
                if(!StringUtils.equals(user.getUserStatus(),status)){
                    user.setUserStatus(status);
                }
                user.updateRefreshTime(5, TimeUnit.MINUTES);
                cookyjar.set(user);
//                if(StringUtils.equals(status, Constants.USER_STATUS_ENUM.UNVERIFY.getValue())){
//
//                }else if(StringUtils.equals(status, Constants.USER_STATUS_ENUM.VERIFIED.getValue())){
//
//                }else if(StringUtils.equals(status, Constants.USER_STATUS_ENUM.PASS.getValue())) {
//
//                }else if(StringUtils.equals(status, Constants.USER_STATUS_ENUM.UNPASS.getValue())){
//
//                }else if(StringUtils.equals(status, Constants.USER_STATUS_ENUM.CLOSED.getValue())){
//
//                }
            }else{
                throw new UserAccessDeniedException("User not exist");
            }**/
        }
		if (permissions.length == 0) {
			return user != null;
		}
		if (user != null) {
			for (Permission em : permissions) {
				if (user.havePermission(em)) {
					return true;
				}
			}
		}
		return false;
	}
}
