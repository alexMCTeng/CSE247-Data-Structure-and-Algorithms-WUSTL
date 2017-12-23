package lecture9.morehash;

import java.util.Random;

public class MultiplyBase10Hash<T> extends MyHashSet<T> {
	
	private final int p;
	
	public MultiplyBase10Hash(int p) {
		super(tenToThe(p));  // we have 10 to the p buckets
		this.p = p;
	}
	
	private static int tenToThe(int p) {
		if (p == 0)
			return 1;
		else return 10 * tenToThe(p-1);
	}

	/**
	 * Use e's hashCode as a start, but that could a really large or negative integer
	 * @param e
	 * @return
	 */
	protected int findBucket(T e) {
		int k = e.hashCode();     // 32 bits of hash from e's designer
		double A = 0.6180339887;  // From Knuth
		double prod = k * A;      //     xxxx.yyyyyyy
		int prodint = (int) prod; //     xxxx part
		double fracpart = prod - prodint;    //     xxxx.yyyyy -   xxxx.000000   =  .yyyyyy
		// System.out.println("You have fracpart " + fracpart);
		// get as of .yyyyy  as we need.  We have 10 to the p buckets
		int bucket = (int) (fracpart * tenToThe(p));
		// System.out.println("You have bucket " + bucket);
		return bucket;
	}

	
	public static void main(String[] args) {
		Random r = new Random();
		MultiplyBase10Hash<Object> h = new MultiplyBase10Hash<Object>(1);
		for (int i=0; i < 1000; i = i + 32) {
			h.add(new Integer(i));
		}
		h.dumpBuckets();
	}

	
}
