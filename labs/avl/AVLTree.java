package avl;

import java.util.LinkedList;

public class AVLTree<T extends Comparable<T>> {

	private TreeNode<T> root;
	public int size;

	public AVLTree() {
		this.root = null;
		this.size = 0;
	}

	////////////////////////////////////////////////////////

	//
	// exists()
	// Check whether a specified value exists in the set
	//
	public boolean exists(T value) {
		return existsHelper(value, this.root);
	}

	//
	// existsHelper()
	// (Optionally) recursive procedure to traverse a tree
	// rooted at "root" to find a specified value.  
	//
	// RETURNS: true if the value is found, else false
	//
	private boolean existsHelper(T value, TreeNode<T> root) {
		if (root == null) { // not found
			return false;
		} else {
			int comparison = value.compareTo(root.value);

			if (comparison == 0) { // found
				return true;
			} else if (comparison < 0) { // still looking - go left
				return existsHelper(value, root.left);
			} else { // still looking - go right
				return existsHelper(value, root.right);
			}
		}
	}

	////////////////////////////////////////////////////////

	//
	// min()
	// Return the minimum value in the set
	//
	// If the set is empty, result is undefined.
	//
	public T min() {
		return minValueInSubtree(this.root);
	}

	//
	// minValueInSubTree()
	// Find the smallest value in the subtree rooted at
	// the specified node.
	//
	// ASSUMED: root is not null.
	//
	private T minValueInSubtree(TreeNode<T> root) {
		while (root.left != null)
			root = root.left;

		return root.value;
	}

	//
	// max()
	// Return the maximum value in the set
	//
	// If the set is empty, result is undefined.
	//

	public T max() {
		return maxValueInSubtree(this.root);
	}


	//
	// maxValueInSubTree()
	// Find the largest value in the subtree rooted at
	// the specified node.
	//
	// ASSUMED: root is not null.
	//
	private T maxValueInSubtree(TreeNode<T> root) {
		while (root.right != null)
			root = root.right;

		return root.value;
	}

	////////////////////////////////////////////////////////

	//
	// insert()
	// Insert the specified value in the set if it does not
	// already exist.
	//
	// RETURNS: the size of the set after insertion.
	//
	public int insert(T value) 
	{
		this.root = insertHelper(value, this.root);
		return size;
	}

	//
	// insertHelper()
	// Recursive procedure to insert a value into the subtree
	// rooted at "root".  If value is already present in the
	// tree, nothing is inserted.
	//
	// RETURNS: root node of subtree after insertion
	//
	// FIXME: add the necessary code to this function
	// to maintain height and rebalance the tree when
	// a node is removed.
	//
	private TreeNode<T> insertHelper(T value,
			TreeNode<T> root) {
		if (root == null) {
			// add new element as leaf of tree
			TreeNode<T> newNode = new TreeNode<T>(value);
			size++;
			// set the height of the newNode to 0
			newNode.height = 0;
			return newNode;
		} else {
			int comparison = value.compareTo(root.value);
			if (comparison == 0) {
				// duplicate element -- return existing node
				return root;
			} else if (comparison < 0) {
				// still looking -- go left
				root.setLeft(insertHelper(value, root.left));
			} else {
				// still looking -- go right
				root.setRight(insertHelper(value, root.right));
			}
		}
		//	after insertion, update the height of the subtree of a given root
		updateHeight(root);
		//	rebalance the subtree after update the height of a given root
		return rebalance(root);
	}

	////////////////////////////////////////////////////////

	//
	// remove()
	// Remove a value from the set if it is present
	//
	public void remove(T value) {
		this.root = removeHelper(value, this.root);
	}

	//
	// removeHelper()
	// Recursive procedure to remove a value from the
	// subtree rooted at "root", if it exists.
	//
	// RETURNS root node of subtree after insertion
	//
	// FIXME: add the necessary code to this function
	// to maintain height and rebalance the tree when
	// a node is removed.
	//
	private TreeNode<T> removeHelper(T value,
			TreeNode<T> root) {

		if (root == null) { // did not find element
			return null;
		} else {
			int comparison = value.compareTo(root.value);

			if (comparison == 0) { // found element to remove
				if (root.left == null || root.right == null) {
					// base case -- root has at most one subtree,
					// so return whichever one is not null (or null
					// if both are)
					size--;
					return (root.left == null ? root.right : root.left);
				} else {
					// node with two subtrees -- replace key
					// with successor and recursively remove
					// the successor.
					T minValue = minValueInSubtree(root.right);
					root.value = minValue;

					root.setRight(removeHelper(minValue, root.right));
				}
			} else if (comparison < 0) {
				// still looking for element to remove -- go left
				root.setLeft(removeHelper(value, root.left));
			} else {
				// still looking for element to remove -- go right
				root.setRight(removeHelper(value, root.right));
			}
		}
		//	just like insertHelper, after remove a node, update height and rebalance.
		updateHeight(root);
		return rebalance(root);
	}


	////////////////////////////////////////////////////////
	//
	// INTERNAL METHODS FOR MAINTAINING BALANCE
	//

	// updateHeight()
	//
	// Recompute the height of the subtree rooted at "root",
	// assuming that its left and right children (if any)
	// have correct heights for their respective subtrees.
	//
	// EFFECT: Set the root's height field to the updated value
	//
	private void updateHeight(TreeNode<T> root) {
		//	if there's nothing there, just return
		if (root == null) {
			return;
		}
		//	for the height of the subtrees, if the root.left or root.right equals to null
		//	then the height of subtrees is -1, otherwise, is the maximum value between right and left
		int rightHeight = root.right != null ? root.right.height : -1;
		int leftHeight = root.left != null ? root.left.height : -1;
		root.height = rightHeight > leftHeight ? rightHeight + 1 : leftHeight + 1;
	}
	//
	// getBalance()
	// Return the balance factor of a subtree rooted at "root"
	// (right subtree height - left subtree height)
	//
	private int getBalance(TreeNode<T> root) {
		if (root.left == null) {
			if (root.right == null) {
				//	if the root has no child, return 0
				return 0;
			} else { //	if the root doesn't have left child but has a right one, return the height of right + 1
				return root.right.height + 1;
			}
		//	if left is not null, but right == null, since null has height of -1, so -1 - left
		} else if (root.right == null) {
			return -1-root.left.height;
		} else {
			//	else return the difference of right and left
			return root.right.height - root.left.height;
		}
	}



	//
	// rebalance()
	//
	// Rebalance an AVL subtree, rooted at "root", that has possibly
	// been unbalanced by a single node's insertion or deletion.
	//
	// RETURNS: the root of the subtree after rebalancing
	//
	private TreeNode<T> rebalance(TreeNode<T> root) {
		//	 if a root is left heavy
		if (getBalance(root) < -1) {
			// 	if the left side of the root is left heavy (balanced but left heavy) 
			if (getBalance(root.left) < 0) {
				//	rotate right
				return rightRotate(root);
				//	otherwise, perform left rotation on root.left, then perform a right rotation on root
			} else {
				root.setLeft(leftRotate(root.left));
				return rightRotate(root);
			}
		//	same strategy here
		} else if (getBalance(root) > 1) {
			if(getBalance(root.right) > 0) {
				return leftRotate(root);
			} else {
				root.setRight(rightRotate(root.right));
				return leftRotate(root);
			}
		} 
		return root;
	}

	//
	// rightRotate()
	// Perform a right rotation on a tree rooted at "root"
	// The tree's root is assumed to have a left child.
	//
	// RETURNS: the new root after rotation.
	//
	
	/**
	 * Here, I comment out the defensive code, 
	 * I'm still using the JUnit test to see if I need to add it back, since sometimes
	 * The console would report an error.
	 * 
	 * But I don't think I need these code, since in the methods of insertHelper and removeHelper
	 * it will handle the parent stuffs, i.e. set the new root to the left of right side of the original
	 * parent. 
	 * I think I don't really need to do anything like that at here.
	 * @param root
	 * @return
	 */
	private TreeNode<T> rightRotate(TreeNode<T> root) {
		TreeNode<T> left = root.left;
		TreeNode<T> shift = left.right;
//		if (root.parent == null) {
//			this.root = left;
//			left.parent = null;
//		} else if (root.parent.right == root) {
//			root.parent.setRight(left);
//		} else {
//			root.parent.setLeft(left);
//		}
		left.setRight(root);
		root.setLeft(shift);
		updateHeight(root);
		updateHeight(left);
//		updateHeight(left.parent);
		return left;
	}

	//
	// leftRotate()
	// Perform a left rotation on a tree rooted at "root"
	// The tree's root is assumed to have a right child.
	//
	// RETURNS: the new root after rotation.
	//
	private TreeNode<T> leftRotate(TreeNode<T> root) {
		TreeNode<T> right = root.right;
		TreeNode<T> shift = right.left;
//		if (root.parent == null) {
//			this.root = right;
//			right.parent = null;
//		} else if(root.parent.left == root) {
//			root.parent.setLeft(right);
//		} else {
//			root.parent.setRight(right);
//		}
		right.setLeft(root);
		root.setRight(shift);
		updateHeight(root);
		updateHeight(right);
//		updateHeight(right.parent);
		return right;
	}

	/////////////////////////////////////////////////////////////
	//
	// METHODS USED TO VALIDATE CORRECTNESS OF TREE
	// (You should not have to touch these)
	//

	//
	// getRoot()
	// Return the root node of the tree (for validation only!)
	//
	public TreeNode<T> getRoot() {
		return this.root;
	}


	//
	// enumerate()
	// Return the contents of the tree as an ordered list
	//
	public LinkedList<T> enumerate() {
		return enumerateHelper(this.root);
	}

	//
	// enumerateHelper()
	// Enumerate the contents of the tree rooted at "root" in order
	// as a linked list
	//
	private LinkedList<T> enumerateHelper(TreeNode<T> root) {
		if (root == null) 
		{
			return new LinkedList<T>();
		}
		else
		{
			LinkedList<T> list = enumerateHelper(root.left);
			list.addLast(root.value);
			list.addAll(enumerateHelper(root.right));

			return list;
		}
	}
}
