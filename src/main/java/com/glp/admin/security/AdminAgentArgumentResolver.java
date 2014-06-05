package com.glp.admin.security;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import com.eyeieye.melody.web.cookyjar.Cookyjar;

/**
 * 
 * @author fish
 * 
 */
public class AdminAgentArgumentResolver implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().equals(AdminAgent.class)) {
			Cookyjar cookyjar = (Cookyjar) webRequest
					.getAttribute(Cookyjar.CookyjarInRequest,
							RequestAttributes.SCOPE_REQUEST);
			if (cookyjar != null) {
				return cookyjar.getObject(AdminAgent.class);
			}
		}
		return UNRESOLVED;
	}
}
