package lecture9.loudermans18;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LoudermanSet<T> implements Set<T> {
	
	private static final int numBuckets = 8;
	
	private List<T>[] buckets;
	
	public LoudermanSet() {
		this.buckets = (List<T>[]) new LinkedList[numBuckets];
		for (int i=0; i < numBuckets; ++i) {
			buckets[i] = new LinkedList<T>();
		}
	}
	
	@Override
	public boolean add(T e) {
		int b = findBucket(e);
		if (buckets[b].contains(e)) { // already there
			return false;
		}
		else { // not there, so add it to the list
			buckets[b].add(e);
			return true;
			
		}
		
	}
	
	private int findBucket(T e) {
		// division method -- compute bucket as remainder after division
		int b = Math.abs(e.hashCode()) % numBuckets;
		return b;
	}
	
	private void dumpBuckets() {
		for (int i=0; i < numBuckets; ++i) {
			System.out.println("Bucket " + i + " has " + buckets[i]);
		}
	}
	
	public static void main(String[] args) {
		LoudermanSet<String> names = new LoudermanSet<String>();
		LoudermanSet<Integer> nums = new LoudermanSet<Integer>();
		Random r = new Random();
		names.add("hello");
		names.add("good bye");
		for (int i=0; i < 100; ++i) {
			names.add("" + r.nextInt());
		}
		System.out.println("buckets for names");
		names.dumpBuckets();
		for (int i=0; i < 20; ++i) {
			nums.add(8*i);
		}
		System.out.println("buckets for nums");
		nums.dumpBuckets();
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
