package hokutosai.server.util;

import java.util.Random;

public class RandomToken {

	private static final String sample = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Random rand = new Random();
	private int length;
	
	public RandomToken(int length) {
		this.length = length;
	}
	
	public String next() {
		StringBuilder token = new StringBuilder();
		int sampleLength = sample.length();
		
		for (int i = 0; i < this.length; ++i) {
			token.append(sample.charAt(rand.nextInt(sampleLength)));
		}
		
		return token.toString();
	}
	
}
