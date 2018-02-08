package studio3;

import java.util.LinkedList;
import java.util.List;

public class UnorderedList<T extends Comparable<T>> implements PriorityQueue<T> {

	public List<T> list;
	
	public UnorderedList() {
		list = new LinkedList<T>();
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
