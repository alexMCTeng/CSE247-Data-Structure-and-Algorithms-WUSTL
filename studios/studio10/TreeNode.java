package studio10;

/**
 * Note here that E does not implement Comparable<E>, though it probably should.
 * We are trying the "other style" of creating this kind of software:
 *     We don't insist a TreeNode can compare itself with other TreeNodes,
 *     and instead will cast to Comparable when the time is right.
 *     If the cast fails, the program is terminated.
 * @author roncytron
 *
 * @param <E>
 */
public class TreeNode<E> {
	
	TreeNode<E> left, right, parent;
	E value;

	public TreeNode() {
		// nothing here?  that makes everything by default
		// Java initalizes everything to "0" -- but here 0 is null
	}
	
	public String toString() {
		return ""+value;
	}

}
