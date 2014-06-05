package com.glp.message.result;

import java.io.Serializable;

/**
 * <p>
 * </p>
 * 
 */
public class ResultBase implements Serializable {

	private static final long serialVersionUID = -2655356456731762301L;

	public static final String ERROR_INVALID_PARAMETER = "ERROR_INVALID_PARAMETER";
	
	public static final String ERROR_EMAIL_SYSTEM = "ERROR_EMAIL_SYSTEM";
	
	private boolean isSuccess = false;

	private String errorCode;

	private String errorMessage;

	public String getErrorMessage() {
		if (errorMessage == null) {
			return errorCode;
		}
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
