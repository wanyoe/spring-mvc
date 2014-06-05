package com.glp.resolver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eyeieye.melody.web.cookyjar.SelfDependence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import com.eyeieye.melody.web.cookyjar.Cookyjar;
import com.glp.security.UserAccessDeniedException;
import com.glp.security.UserAgent;

@Component
public class WebExceptionResolver implements HandlerExceptionResolver, PriorityOrdered {
	private static final Log logger = LogFactory
			.getLog(WebExceptionResolver.class);

	private String webEncoding = "UTF-8";

	private String errorPage = "/errors/500";

	private String loginPath = "/user/signin.htm";

	private String deniedPage = "/errors/401";

	private String loginReturnParameterName = "url";
	
	boolean useRedirect = false;

    private Class<? extends RuntimeException> deniedException = UserAccessDeniedException.class;

    private Class<? extends SelfDependence> dependence = UserAgent.class;

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//if (ex instanceof UserAccessDeniedException) {
        if (deniedException.isInstance(ex)) {
			return resolveAccessDeniedException(request);
		}
		logger.error("web error", ex);
		if(useRedirect )
			return new ModelAndView("redirect:" +this.errorPage);
		return new ModelAndView(this.errorPage, "error", ex);
	}

	private ModelAndView resolveAccessDeniedException(
			HttpServletRequest request) {
		Cookyjar cookyjar = (Cookyjar) request
				.getAttribute(Cookyjar.CookyjarInRequest);
		SelfDependence agent =  cookyjar
				.getObject(dependence);
		if (agent == null) {
			String returnUrl = getReturnUrl(request);
			return new ModelAndView("redirect:" + loginPath,
					loginReturnParameterName, returnUrl);
		}
		if(useRedirect)
			return new ModelAndView("redirect:" + this.deniedPage);
		return new ModelAndView(this.deniedPage);
	}

	private String getReturnUrl(HttpServletRequest request) {
		StringBuffer sb = request.getRequestURL();
		appendRequestParameters(sb, request);
		try {
			return URLEncoder.encode(sb.toString(), this.webEncoding);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	private void appendRequestParameters(StringBuffer sb,
			HttpServletRequest request) {
		Enumeration<?> en = request.getParameterNames();
		if (!en.hasMoreElements()) {
			return;
		}
		sb.append('?');
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String[] values = request.getParameterValues(name);
			if (values == null || values.length == 0) {
				continue;
			}
			for (String v : values) {
				try {
					v = URLEncoder.encode(v, this.webEncoding);
				} catch (UnsupportedEncodingException ignore) {
				}
				sb.append(name).append('=').append(v).append('&');
			}
		}
		sb.deleteCharAt(sb.length() - 1);
	}

	@Override
	public int getOrder() {
		return 100;
	}

	public void setWebEncoding(String webEncoding) {
		this.webEncoding = webEncoding;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public void setLoginPath(String loginPath) {
		this.loginPath = loginPath;
	}

	public void setDeniedPage(String deniedPage) {
		this.deniedPage = deniedPage;
	}

	public void setLoginReturnParameterName(String loginReturnParameterName) {
		this.loginReturnParameterName = loginReturnParameterName;
	}
	public void setUseRedirect(boolean useRedirect) {
		this.useRedirect = useRedirect;
	}

    public void setDeniedException(Class<? extends RuntimeException> deniedException) {
        this.deniedException = deniedException;
    }

    public void setDependence(Class<? extends SelfDependence> dependence) {
        this.dependence = dependence;
    }

    public Class<? extends RuntimeException> getDeniedException() {
        return deniedException;
    }

    public Class<? extends SelfDependence> getDependence() {
        return dependence;
    }
}
