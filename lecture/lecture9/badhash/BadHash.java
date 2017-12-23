package lecture9.badhash;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BadHash<T> implements Set<T> {
	
	private static final int numBuckets = 8;
	private List<T>[] buckets;
	
	public BadHash() {
		this.buckets = (LinkedList<T>[]) new LinkedList[numBuckets];
		for (int i=0; i < numBuckets; ++i) {
			this.buckets[i] = new LinkedList<T>();
		}
	}


	private int findBucket(T e) {
		int bucket = Math.abs(e.hashCode()) % numBuckets;
		return bucket;
	}
	
	public void dumpBuckets() {
		for (int i=0; i < numBuckets; ++i) {
			System.out.println("Bucket " + i + ": " + buckets[i]);
		}
	}

	@Override
	public boolean add(T e) {
		int bucket = findBucket(e);
		if (!this.buckets[bucket].contains(e)) {
			this.buckets[bucket].add(e);
			return true;
		}
		else return false;
	}




	@Override
	public int size() {
		// FIXME Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isEmpty() {
		// FIXME Auto-generated method stub
		return false;
	}


	@Override
	public boolean contains(Object o) {
		// FIXME Auto-generated method stub
		return false;
	}


	@Override
	public Iterator<T> iterator() {
		// FIXME Auto-generated method stub
		return null;
	}


	@Override
	public Object[] toArray() {
		// FIXME Auto-generated method stub
		return null;
	}


	@Override
	public <T> T[] toArray(T[] a) {
		// FIXME Auto-generated method stub
		return null;
	}


	@Override
	public boolean remove(Object o) {
		// FIXME Auto-generated method stub
		return false;
	}


	@Override
	public boolean containsAll(Collection<?> c) {
		// FIXME Auto-generated method stub
		return false;
	}


	@Override
	public boolean addAll(Collection<? extends T> c) {
		// FIXME Auto-generated method stub
		return false;
	}


	@Override
	public boolean retainAll(Collection<?> c) {
		// FIXME Auto-generated method stub
		return false;
	}


	@Override
	public boolean removeAll(Collection<?> c) {
		// FIXME Auto-generated method stub
		return false;
	}


	@Override
	public void clear() {
		// FIXME Auto-generated method stub
		
	}

	public static void main(String[] args) {
		BadHash<Integer> bh = new BadHash<Integer>();
		Random r = new Random();
		for (int i=0; i < 1000; i=i+16) {
			bh.add(i);
		}
		bh.dumpBuckets();
	}


}
