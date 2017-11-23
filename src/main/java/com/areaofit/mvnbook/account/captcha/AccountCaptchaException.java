package com.areaofit.mvnbook.account.captcha;

public class AccountCaptchaException extends Exception {

	private static final long serialVersionUID = 2952986516522702049L;

	public AccountCaptchaException(String desc){
		super(desc);
	}
	
	public AccountCaptchaException(String desc, Exception e){
		super(desc,e);
	}
}
