package studio3;

import java.util.LinkedList;
import java.util.List;

public class UnorderedList<T extends Comparable<T>> implements PriorityQueue<T> {

	public LinkedList<T> list;
	T min;
	
	
	public UnorderedList() {
		list = new LinkedList<T>();
	}
	
	@Override
	public boolean isEmpty() {
		//
		// FIXME
		//
		if(this.list.peekFirst() == null) {
			return true;
		}
		return false;
	}

	@Override
	public void insert(T thing) {
		//
		// FIXME
		//
		this.list.add(thing);
	}

	@Override
	public T extractMin() {
		//
		// FIXME
		//
		T min = null;
		for(T thing : this.list) {
			if (min == null || thing.compareTo(min) < 0) {
				min = thing;
			}
		}
		this.list.remove(min);
		return min;
	}

}
