package com.areaofit.mvnbook.account.captcha;

import java.util.Random;

public class RandomGenerator {
	
	private static String range = "0123456789qwertyuiopasdfghjklzxcvbnm";
	
	public static String getRandomString() {
		Random random = new Random();
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			sBuffer.append(range.charAt(random.nextInt(range.length())));
		}
		return sBuffer.toString();
	}
	

}
