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
		return this.size == 0;
	}

	@Override
	public void insert(T thing) {
		//
		// FIXME
		//
		if(size==0) {
			this.array[0] = thing;
		}
		for (int i=size-1; i>=0; i--) {

			if (this.array[i].compareTo(thing)>0) {
				this.array[i+1] = this.array[i];
			}
			else {
				this.array[i+1] = thing;
				size++;
				return;
			}
		}

	}

	@Override
	public T extractMin() {
		//
		// FIXME
		//
		if(size==0) {
			return null;
		}
		T min = this.array[0];
		for (int  j=1; j<size;j++) {
			this.array[j-1] = this.array[j];
		}
		size--;
		return min;
	}

}
