package lecture9.morehash;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

/**
 * It's missing findBucket!
 * @author roncytron
 *
 * @param <T>
 */
abstract public class MyHashSet<T> implements Set<T> {
	
	protected final int numBuckets;
	private LinkedList<T>[] buckets;
	
	public MyHashSet(int numBuckets) {
		this.buckets = new LinkedList[numBuckets];
		this.numBuckets = numBuckets;
		// go ahead and create empty lists for each bucket
		for (int i=0; i < numBuckets; ++i) {
			buckets[i] = new LinkedList<T>();
		}
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
	
	public void dumpBuckets() {
		for (int i=0; i < numBuckets; ++i) {
			System.out.println("Bucket " + i + " " + buckets[i]);
		}
	}
	
	/**
	 * Use e's hashCode as a start, but that could a really large or negative integer
	 * @param e
	 * @return
	 */
	abstract protected int findBucket(T e);

	@Override
	public boolean add(T e) {
		int b = findBucket(e);
		if (buckets[b].contains(e)) {
			return false;   // no add, already there
		}
		else {
			buckets[b].add(e);  // Assume this takes Theta(1)
			return true;
		}
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
	


	
}
