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
		return size == 0;
	}

	@Override
	public void insert(T thing) {
		//
		// FIXME
		//
		if(size==0) {//nothing in the array, then put it in array[0]
			this.array[0] = thing;
			size++;//increment size
		}
		else if(this.array[size-1].compareTo(thing)<= 0) {
			this.array[size] = thing;
			size++;
		}
		else {
			for (int i=size-1; i>=0; i--) {//starting from the last element in the array
				if (this.array[i].compareTo(thing)>0) {// if array[i] > thing
					this.array[i+1] = this.array[i];//assign array[i] to array[i+1], that is, move to the right
					this.array[i] = thing;//then insert thing into array[i], then keep looping
				}
			}
			size++;//increment size
		}
	}

	@Override
	public T extractMin() {
		//
		// FIXME
		//
			T min = this.array[0];//If the implementation is correct, then the smallest element would appear at array[0]
			for (int  j=1; j<size;j++) {//using for loop to move the remaining element to the left
				this.array[j-1] = this.array[j];
			}	
			size--;//decrement size
			return min;
		}
	}
