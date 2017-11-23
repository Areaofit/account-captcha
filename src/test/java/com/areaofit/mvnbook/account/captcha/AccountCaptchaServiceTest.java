package com.areaofit.mvnbook.account.captcha;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AccountCaptchaServiceTest {
	
	private AccountCaptchaService service;
	
	ApplicationContext applicationContext = null;
	
	@Before
	public void prepare() throws Exception{
		applicationContext = new ClassPathXmlApplicationContext("account-captcha.xml");
		service = (AccountCaptchaService) applicationContext.getBean("accountCaptchaService");
	}

	@Test
	public void testGenerateCaptcha() throws Exception{
		String captchaKey = service.generateCaptchaKey();
		assertNotNull(captchaKey);
		byte[] captchaImage = service.generateCaptchaImages(captchaKey);
		assertTrue(captchaImage.length > 0);
		File image = new File("target/"+captchaKey+".jpg");
		OutputStream out = null;
		try {
			out = new FileOutputStream(image);
			out.write(captchaImage);
		} finally {
			if (out != null) {
				out.close();
			}
		}
		assertTrue(image.exists() && image.length() > 0);
	}
	
	@Test
	public void testValidateCaptchaCorrect() throws Exception{
		List<String> preDefinedTexts = new ArrayList<String>();
		preDefinedTexts.add("123456");
		preDefinedTexts.add("abcdef");
		service.setPreDefinedTexts(preDefinedTexts);
		String captchaKey = service.generateCaptchaKey();
		service.generateCaptchaImages(captchaKey);
		assertTrue(service.validateCaptcha(captchaKey, "123456"));
		
		captchaKey = service.generateCaptchaKey();
		service.generateCaptchaImages(captchaKey);
		assertTrue(service.validateCaptcha(captchaKey, "abcdef"));
	}
	
	@Test
	public void testValidateCaptchaIncorrect() throws Exception{
		List<String> preDefinedTexts = new ArrayList<String>();
		preDefinedTexts.add("123456");
		service.setPreDefinedTexts(preDefinedTexts);
		String captchaKey = service.generateCaptchaKey();
		service.generateCaptchaImages(captchaKey);
		assertFalse(service.validateCaptcha(captchaKey, "456123"));
	}
	
}
