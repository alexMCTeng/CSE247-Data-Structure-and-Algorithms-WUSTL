package lecture9.morehash;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

public class MultiplyHash<T> extends MyHashSet<T> {
	
	private final int p;
	
	public MultiplyHash(int p) {
		super(1<<p);  // we have 2 to the p buckets
		this.p = p;
	}

	/**
	 * Use e's hashCode as a start, but that could a really large or negative integer
	 * @param e
	 * @return
	 */
	protected int findBucket(T e) {
		int h = e.hashCode();
		long Aint = 2654435769L;  // A from Knuth shifted right 32 bits
		long prod = Aint * h;   // that's a 64 bit number, and I want the bottom 32 bits
		int bottom32 = (int)prod;
		int b = bottom32 >>> (32-p);
		return b;
	}

	
	public static void main(String[] args) {
//		System.out.println("abc".hashCode());
//		System.out.println("cba".hashCode());
		Random r = new Random();
		MultiplyHash<Object> h = new MultiplyHash<Object>(2);
		for (int i=0; i < 1000; i = i + 32) {
			h.add(new Integer(i));
		}
		h.dumpBuckets();
	}

	
}
