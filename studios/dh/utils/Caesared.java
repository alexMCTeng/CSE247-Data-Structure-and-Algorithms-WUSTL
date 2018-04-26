package dh.utils;

/**
 * Carries a character along with whether it has been rotated or not
 * If it has been rotated, the character is the rotated version, not the original.
 * If it has not been rotated, the character is the original character.
 * 
 * This class only rotates characters between 'a' and 'z'.  All other characters
 *   are not rotated.  This lets us see punctuation and spacing clearly 
 *   in both the cleartext and the corresponding encrypted strings.
 * @author roncytron
 *
 */
public class Caesared {

	private final boolean rotated;
	private final char c;

	/**
	 * Only this class can create an instance, using the static method gen.
	 * Note that we do not remember the rotation amount, as that is imposed by
	 *   cryptography.
	 * @param rotated was this character rotated?
	 * @param c if rotated, the value of the rotated character; otherwise the original character
	 */
	private Caesared(boolean rotated, char c) {
		this.rotated = rotated;
		this.c       = c;
	}
	
	private static void check(long rotation) {
		if (rotation < 0)
			throw new IllegalArgumentException("Must supply nonnegative rotation value but you supplied " + rotation);
	}

	public String toString() {
		return ""+c;
	}

	/**
	 * Only rotates characters between 'a' and 'z' so we can see some things clearly.
	 * @param c cleartext character
	 * @param rotation rotation amount
	 * @return an instance of Caesared containing the possibly rotated character
	 */
	public static Caesared gen(char c, long rotation) {
		check(rotation);
		if (c >= 'a' && c <= 'z') {
			char r = (char) (((c-'a') + rotation) % 26 + 'a');
			return new Caesared(true, r);
		}
		else return new Caesared(false, c);
	}

	/**
	 * Return the contained character unrotated by the specified amount.
	 * If the character was not rotated, we just return the character without unrotating it.
	 * @param rotation the amount to unrotate, must be positive
	 * @return the answer as described above.
	 */
	public char unRotate(long rotation) {
		check(rotation);
		if (!rotated)
			return c;
		else {
			rotation = rotation % 26;
			return (char)((c-'a' + (26-rotation)) % 26 + 'a');
		}
	}

}
