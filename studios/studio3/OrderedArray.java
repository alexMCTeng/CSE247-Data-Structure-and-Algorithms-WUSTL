package studio3;

public class OrderedArray<T extends Comparable<T>> implements PriorityQueue<T> {

	public T[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public OrderedArray(int maxSize) {
		array = (T[]) new Comparable[maxSize];
		size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		//
		// FIXME
		//
		return false;
	}

	@Override
	public void insert(T thing) {
		//
		// FIXME
		//
	}
	
	@Override
	public T extractMin() {
		//
		// FIXME
		//
		return null;
	}

}
