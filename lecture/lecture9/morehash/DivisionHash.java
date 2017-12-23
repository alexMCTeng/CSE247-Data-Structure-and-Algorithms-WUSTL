package lecture9.morehash;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

public class DivisionHash<T> extends MyHashSet<T> {
	
	
	public DivisionHash(int numBuckets) {
		super(numBuckets);   // tell the superclass we need that many buckets
	}

	/**
	 * Use e's hashCode as a start, but that could a really large or negative integer
	 * @param e
	 * @return
	 */
	protected int findBucket(T e) {
		int h = e.hashCode();
		// map h to some bucket
		int b = Math.abs(h) % numBuckets;  // this gives me an int 0, ... (numBuckets-1)
		return b;
	}

	
	public static void main(String[] args) {
//		System.out.println("abc".hashCode());
//		System.out.println("cba".hashCode());
		Random r = new Random();
		DivisionHash<Object> h = new DivisionHash<Object>(8);
		for (int i=0; i < 1000; i = i + 16) {
			h.add(new MyInteger(i));
		}
		h.dumpBuckets();
	}

	
}
