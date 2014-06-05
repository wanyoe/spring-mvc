package com.glp.security;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import com.eyeieye.melody.web.cookyjar.Cookyjar;
import com.glp.security.UserAgent;

/**
 * 
 * @author fish
 * 
 */
public class UserAgentArgumentResolver implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().equals(UserAgent.class)) {
			Cookyjar cookyjar = (Cookyjar) webRequest
					.getAttribute(Cookyjar.CookyjarInRequest,
							RequestAttributes.SCOPE_REQUEST);
			if (cookyjar != null) {
				return cookyjar.getObject(UserAgent.class);
			}
		}
		return UNRESOLVED;
	}
}
