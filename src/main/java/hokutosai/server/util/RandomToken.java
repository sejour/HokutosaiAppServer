package hokutosai.server.util;

import java.util.Random;

public class RandomToken {

	private static final String sample = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Random rand = new Random();
	
	public RandomToken() {}
	
	public String generate(int length) {
		StringBuilder token = new StringBuilder();
		int sampleLength = sample.length();
		
		for (int i = 0; i < length; ++i) {
			token.append(sample.charAt(rand.nextInt(sampleLength)));
		}
		
		return token.toString();
	}
	
}
