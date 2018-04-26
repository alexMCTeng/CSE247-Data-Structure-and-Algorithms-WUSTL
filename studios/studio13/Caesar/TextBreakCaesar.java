package studio13.Caesar;

import dh.utils.Caesared;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TextBreakCaesar {

	private static String preamble = "We the People of the United States, in Order to form a more perfect Union, establish Justice, insure domestic Tranquility, provide for the common defence, promote the general Welfare, and secure the Blessings of Liberty to ourselves and our Posterity, do ordain and establish this Constitution for the United States of America.";
	private Random rand = new Random();

	/**
	 * Generates a scrambled version of our Constitution's preamble.
	 * Words are left intact, but their order is permuted.
	 * @return
	 */
	private static String scramble() {
		String[] words = preamble.split("[ ]");
		List<String> shuffled = Arrays.asList(words);
		Collections.shuffle(shuffled);
		return String.join(" ", shuffled);
	}

	@Test
	public void testZero() {
		testRot(0);
	}

	@Test
	public void testRandom() {
		for (int i=0; i < 5; ++i) {
			int rotate = rand.nextInt(26);
			testRot(rotate);
		}
	}

	private void testRot(int rotate) {
		char[] in = scramble().toCharArray();
		Caesared[] cs = new Caesared[in.length];
		for (int i=0; i < in.length; ++i) {
			cs[i] = Caesared.gen(in[i], rotate);
		}
		BreakCaesar bc = new BreakCaesar(cs);
		System.out.println("Rotation value you should discover: " + rotate);
		verify(bc.getRotation(), in, cs);
	}

	private static String stringify(Object[] array) {
		String ans = "";
		for (Object o : array) {
			ans = ans + o;
		}
		return ans;
	}

	private void verify(int rotation, char[] plain, Caesared[] rotated) {
		System.out.println("Plain:     " + new String(plain));
		System.out.println("Rotated:   " + stringify(rotated));
		String unrotated = "";
		for (int i=0; i < plain.length; ++i) {
			char unc = rotated[i].unRotate(rotation);
			assertEquals("Characters disagree", ""+plain[i], ""+unc);
			unrotated = unrotated + unc;
		}
		System.out.println("Unrotated: " + unrotated);
		System.out.println();
	}

}
