package com.areaofit.mvnbook.account.captcha;

import java.util.List;

public interface AccountCaptchaService {
	
	/**
	 * 生成验证码主键
	 * @return
	 */
	String generateCaptchaKey();
	
	/**
	 * 生成验证码图片
	 * @param captchaKey
	 * @return
	 */
	byte[] generateCaptchaImages(String captchaKey) throws AccountCaptchaException;
	
	boolean validateCaptcha(String captchaKey,String captchaValue) throws AccountCaptchaException;
	
	List<String> getPreDefinedTexts();
	
	void setPreDefinedTexts(List<String> preDefinedTexts);

}
