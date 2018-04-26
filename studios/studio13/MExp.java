package studio13;

/**
 * Like the ModExp used in lecture but without discrete log
 * @author roncytron
 *
 */
public class MExp {
	
	private long z, p;  // base and modulus
	private long[] h;   // base raised to powers 0...9
		
	/**
	 * Convenient to create an instance with base z and modulus p if you
	 *    are wanting to raise z to various powers mod p.
	 *    
	 * @param z the base
	 * @param p the modulus
	 */
	public MExp(long z, long p) {
		this.p = p;
		this.z = mod(z,p);
		h = new long[10];
		h[0] = 1;
		for (int i=1; i < 10; ++i) {
			h[i] = mod(h[i-1] * this.z, this.p);
		}
	}

	/**
	 * Compute w to the 10th power, mod p
	 * @param w
	 * @param p
	 * @return w to the 10th power, mod p
	 */
	public static long tenthpower(long w, long p) {
		long sq = mod(w*w, p);     // w squared
		long ans = sq;            
		ans = mod(ans * ans, p);   // w squared squared, so w to the 4th power
		ans = mod(ans * ans, p);   // w to the 4th squared, so w to the 8th power
		return mod(ans * sq, p);   // w to the 8th times w squared = w to the 10th power
	}
	
	/**
	 * Protects against a negative result mod p
	 * @param m
	 * @param p
	 * @return m mod p as traditionally computed in math, a result in range 0...p-1
	 */
	private static long mod(long m, long p) {
		return ((m % p) + p) % p;
	}
	

	/**
	 * 
	 * @param n exponent
	 * @return this.z to the n mod this.p
	 */
	public long toThePower(long n) {
		char[] digs = (""+n).toCharArray();
		long ans = 1;
		for (int i=0; i < digs.length; ++i) {
			ans = mod(tenthpower(ans, this.p) * h[digs[i]-'0'], p);
		}
		return ans;
	}
	
	/**
	 * Compute g to the x mod p.  A convenient static method for outside use.
	 * @param g the base
	 * @param x the exponent
	 * @param p the modulus
	 * @return
	 */
	public static long gToTheXModP(long g, long x, long p) {
		return new MExp(g,p).toThePower(x);
	}

}
