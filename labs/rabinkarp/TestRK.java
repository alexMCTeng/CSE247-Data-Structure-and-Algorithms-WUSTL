package rabinkarp;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class TestRK {

	@Test
	public void testExample() {
		RK example = new RK(4);
		int[] expect = { 97, 38, 254, 306, 214, 430, 337, 153, 73, 368, 92, 224, 306, 214, 429, 306, 214};
//		String query = "aaba";
		String target = "aabaacaadaabaabaa";
		for (int i=0; i < target.length(); ++i) {
			assertEquals("See example on web page if this fails", expect[i], example.nextCh(target.charAt(i)));
		}
	}
	
	@Test
	public void testRandom() {
		for (int i=0; i < 100; ++i) {
			testRandomLength(i);
		}
	}
	
	@Test
	public void testSameChar() {
		char c = alpha[r.nextInt(alpha.length)];
		RK singleChar = new RK(1);
		for (int i=0; i < 100; ++i) {
			assertEquals(c, singleChar.nextCh(c));
		}
	}
	
	public void testRandomLength(int length) {
		RK rk = new RK(length);
		String query = genRandom(length);
		System.out.println("Looking for query " + query);
		String target = genRandom(length) + query + genRandom(length);
		int queryHash = computeHash(query.toCharArray());
		for (int i=0; i < target.length(); ++i) {
			int h = rk.nextCh(target.charAt(i));
			if (i == 2*length-1) {
				assertEquals(queryHash, h);
				reportMatch(h, "Found match", query, target, i);
			}
			else if (queryHash == h) {
				reportMatch(h, "Spurious match?", query, target, i);
			}
		}
	}
	
	private void reportMatch(int h, String message, String query, String target, int where) {
		int length = query.length();
		String matched = target.substring(Math.max(where-length+1, 0), Math.min(where+1, target.length()));
		System.out.println("  " + h + ": " + matched + " " + message);
	}
	
	private Random r = new Random();
	private final char[] alpha = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '2', '4', '7'};

	private String genRandom(int length) {
		String ans = "";
		for (int i=0; i < length; ++i) {
			ans = ans + alpha[r.nextInt(alpha.length)];
		}
		return ans;
	}
	private int computeHash(char[] buf) {
		int ans = 0;
		for (int i=0; i < buf.length; ++i) {
			ans = (ans * 31 + buf[i]) % 511;
		}
		return ans;
	}
	
}
