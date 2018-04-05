package studio10;

import java.awt.Color;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.SortedSet;

import timing.Ticker;

public class BST<E> implements SortedSet<E> {
	
	private TreeNode<E> root;
	private Ticker ticker;
	
	public BST(Ticker t) {
		this.root = null;
		this.ticker = t;
	}

	public static void main(String[] args) {
		BST<String> lonely = new BST<String>(new Ticker());
		lonely.add("sad");
		lonely.add("happy");
		System.out.println("lonely tree \n" + lonely.dumpTree());
		BST<Integer> t = new BST<Integer>(new Ticker()); // here, E is Integer
		Random r = new Random();
//		for (int i=0; i < 5; ++i) {
//			t.add(r.nextInt(100));
//		}
		t.add(50);
		t.add(30);
		t.add(70);
		t.add(40);
		t.add(60);
		t.add(35);
		t.add(45);
		t.add(247);
		System.out.println("Contains a 5? " + t.contains(5));
		System.out.println("Contains a 35? " + t.contains(35));
		System.out.println("Contains 247? " + t.contains(247));
		System.out.println("Contains a 6? " + t.contains(6));
		
		System.out.println("Least is " +t.first());
		System.out.println("greatest is " + t.last());
		
		System.out.println("tree dump is \n" + t.dumpTree());
		System.out.println("tree is \n" + t);
		
		System.out.println("bye bye 40");
		t.remove(40);
		System.out.println("After remove 40 \n" + t.dumpTree());
		t.remove(50);
		System.out.println("After remove of 50\n" +t.dumpTree());
		System.out.println("iterator:");
		for (Integer i : t) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		
		
// Why does this code fail?
//		
//		BST<Color> col = new BST<Color>(new Ticker());
//		col.add(Color.RED);
//		col.add(Color.BLUE);
	}

	@Override
	public void clear() {
		ticker.tick();
		this.root = null;
	}

	@Override
	public boolean remove(Object o) {
		ticker.tick();
		E val = (E) o;
		this.root = removeHelper(this.root, val);
		return true;
	}
	
	private TreeNode<E> removeHelper(TreeNode<E> node, E val) {
		ticker.tick();
		if (node == null) {
			return null;
		}
		if (node.value.equals(val)) { // found it!
			if (node.left == null && node.right == null) {
				// no kids
				// cause my caller to assign null where node used to be referenced
				return null;
			}
			else if (node.left == null && node.right != null) { // just one child, to the right
				return node.right;
			}
			else if (node.left != null && node.right == null) { // one bambino to the left
				return node.left;
			}
			else {
				// node has two children
				// find the least of my right child
				E leastOfRight = firstHelper(node.right);
				// recursively ask for the deletion of that value
				//  no worries because I have a copy of it in leastOfRight
				this.remove(leastOfRight);  // must be one of the easier cases above
				node.value = leastOfRight;
				return node;
			}
		}
		else { // should we go left or right?
			if (leq(val, node)) {
				node.left = removeHelper(node.left, val);
			}
			else {
				node.right = removeHelper(node.right, val);
			}
			return node;
		}
	}

	@Override
	public boolean contains(Object o) {
		ticker.tick();
		E val = (E) o;
		return containsHelper(root, val);
	}
	
	private boolean containsHelper(TreeNode<E> node, E val) {
		ticker.tick();
		if (node == null) {
			return false;
		}
		
		//
		// node is not null, so let's press forward
		//
		if (node.value.equals(val)) {
			return true;
		}
		if (leq(val, node)) { // look to the left
			return containsHelper(node.left, val);
		}
		else return containsHelper(node.right, val);
	}

	@Override
	public E first() {
		ticker.tick();
		if (root == null) {
			throw new NoSuchElementException();
		}
		return firstHelper(root);
	}
	
	private E firstHelper(TreeNode<E> node) {
		ticker.tick();
		// what condition tell us node is the leftmost node
		if (node.left == null) {
			return node.value;   // the least
		}
		else return firstHelper(node.left);
	}

	@Override
	public E last() {
		ticker.tick();
		if (root == null) {
			throw new NoSuchElementException();
		}
		return lastHelper(root);

	}
	private E lastHelper(TreeNode<E> node) {
		// what condition tell us node is the leftmost node
		if (node.right == null) {
			return node.value;   // the least
		}
		else return lastHelper(node.right);
	}
	
	private void fillList(TreeNode<E> node, List<E>list) {
		if (node == null)
			return;
		fillList(node.left, list);
		list.add(node.value);
		fillList(node.right, list);
	}
	
	@Override
	public Iterator<E> iterator() {
		List<E> list = new LinkedList<E>();
		fillList(this.root, list);
		return list.iterator();
	}

	//
	// we'll do this, but recurisvely (book is iterative)
	//
	@Override
	public boolean add(E e) {
		ticker.tick();
		if (contains(e)) 
			return false;
		this.root = addHelper(root, null, e);
		return true;
	}
	
	/**
	 * Is val < node's value?
	 * @param val
	 * @param node
	 * @return true if so
	 */
	private boolean leq(E val, TreeNode<E> node) {
		ticker.tick();
		// val better be Comparable so we can do the comparison
		Comparable<E> comp = (Comparable<E>) val;   // this will fail if val can't compare itself
		return comp.compareTo(node.value) <= 0;
	}
	
	//
	// returns one of two things
	//  It will return a new node with the val inside it, if we hit null
	//  otherwise, it returns its input node
	private TreeNode<E> addHelper(TreeNode<E> node, TreeNode<E> parent, E val) {
		ticker.tick();
		if (node == null) {
			TreeNode<E> newbie = new TreeNode<E>();
			newbie.value = val;
			newbie.parent = parent;
			// has no children, leave those null/
			// we need its parent, back to that later
			return newbie;
		}
			
		// see where to next, left or right, when done just retur node
		// is val <  node's value?
		if (leq(val, node)) {
			node.left = addHelper(node.left, node, val);  // look to the left
		}
		else {
			node.right = addHelper(node.right, node, val); // look to the right;
		}
		return node;
	}

	private String genIndents(int howmany) {
		String ans ="";
		for (int i=0; i < howmany; ++i) {
			ans = ans + "   ";
		}
		return ans;
	}
	
	private String dumpTreeHelper(TreeNode<E> node, int indent) {
		if (node == null) {
			return "";
		}
		String ans ="";
		ans = ans + dumpTreeHelper(node.right, indent+1);   // all the bigger stuff first
		ans = ans + genIndents(indent) + node.value + "\n"; // then me
		ans = ans + dumpTreeHelper(node.left, indent+1);    // all the smaller stuff
		return ans;
		
	}
	
	public String dumpTree() {
		return dumpTreeHelper(root, 0);
	}
	
	private String toStringHelper(TreeNode<E> node) {
		if (node == null) {
			return "";
		}
		return toStringHelper(node.left) + " " + node.value + toStringHelper(node.right);
	}
	
	public String toString() {
		return "{ " + toStringHelper(root) + " }";
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
	public boolean containsAll(Collection<?> c) {
		// FIXME Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
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
	public SortedSet<E> headSet(E toElement) {
		// FIXME Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> tailSet(E fromElement) {
		// FIXME Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<? super E> comparator() {
		// FIXME Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> subSet(E fromElement, E toElement) {
		// FIXME Auto-generated method stub
		return null;
	}


}
