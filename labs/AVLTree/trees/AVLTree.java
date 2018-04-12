package AVLTree.trees;

import AVLTree.nodes.AVLTreeNode;
import timing.Ticker;

import java.util.LinkedList;

public class AVLTree<T extends Comparable<T>> extends BST<T> {
	private AVLTreeNode<T> root;
	public Ticker ticker;

	public AVLTree(Ticker t) {
		super();
		this.root = null;
		this.ticker = t;

	}

	public AVLTreeNode<T> Root() {
		return root;
	}

	/**
	 * Attempts to locate a value in the Binary Search Tree.
	 * Returns the node the node if it exists, null otherwise
	 * Utilizes a helper function that is recursively called.
	 *
	 * @param value the value to be found
	 * @return the node element if it exists/otherwise null
	 */
	public AVLTreeNode<T> Find(T value) {
		return findHelper(value, this.root);
	}

	// helper function for find, see above for description.
	private AVLTreeNode<T> findHelper(T value, AVLTreeNode<T> curr) {
        //FIXME: Find the node that has the value 'value'.
        return null;
	}

	/**
	 * Attempts to insert a value into the AVL Binary Search Tree.
	 * Returns the node that was inserted.
	 *
	 * @param value the value to be inserted
	 * @return the node element that was inserted
     */
	public AVLTreeNode<T> Insert(T value) {
		AVLTreeNode<T> toInsert = new AVLTreeNode<T>(value);
        //FIXME: Insert toInsert into the tree and update any instance variables.
		return toInsert;
	}

	/**
	 * helper method for insertion into the AVL Binary Search Tree.
	 * Returns the (possibly different) root of the rebalanced
	 *   subtree.
	 *
	 * @param toInsert the value being inserted
     * @param node the root of the subtree to insert into.
	 * @return the node element that was inserted
	 */
	private AVLTreeNode<T> insertHelper(AVLTreeNode<T> node, AVLTreeNode<T> toInsert){
        //FIXME: insert toInsert into the tree starting at 'curr'
        return null;
	}

	/**
	 * Rebalances the subtree rooted at the input node (if necessary).
	 * Returns the (possibly different) root of the rebalanced
     *   subtree.
	 *
	 * @param node the root of the subtree to rebalance
	 * @return the node at the root of the rebalanced subtree
     */
    private AVLTreeNode<T> rebalance(AVLTreeNode<T> node) {
	    //FIXME: rebalance the tree starting at 'node' as needed
        return null;
	}

	/**
	 * Performs a standard right-rotation on a subtree rooted
     *   at the input node.
     * This node corresponds to node 'y' on the left half of
     *   slide 158 of the Lecture 9 notes.
	 * Returns the (possibly different) root of the rebalanced
     *   subtree.
	 *
	 * @param parent the root of the subtree to rotate
	 * @return the new root of the rotated subtree; i.e. the 
     *         node taking the place of parent
	 * <p>
     */
	private AVLTreeNode<T> rightRotate(AVLTreeNode<T> parent) {
	    //FIXME: rotate a parent node to the right
        return null;
	}

	/**
	 * Performs a standard left-rotation on a subtree rooted
     *   at the input node.
     * This node corresponds to node 'x' on the right half of
     *   slide 158 of the Lecture 9 notes.
	 * Returns the (possibly different) root of the rebalanced
     *   subtree.
	 *
	 * @param parent the root of the subtree to rotate
	 * @return the new root of the rotated subtree; i.e. the 
     *         node taking the place of parent
     */
	private AVLTreeNode<T> leftRotate(AVLTreeNode<T> parent) {
        //FIXME: rotate a parent node to the left
        return null;
	}

	/**
	 * Recompute the height of the input node and store in its
     *   corresponding instance variable.
	 *
	 * @param node the node whose height is computed
     */
	private void fixHeight(AVLTreeNode<T> node){
	    //FIXME: fix the height variable of a node
        // Recommended: use a helper method to compute the height
        //   of any subtrees necessary (see below).
	}

	//FIXME (recommended): create a helper method to determine the height of a subtree.

	public boolean isEmpty() {
		return this.root == null;
	}

	public AVLTreeNode<T> minimum() {
		return minimumOfSubtree(this.root);
	}

	public AVLTreeNode<T> maximum() {
		return maximumOfSubtree(this.root);
	}

	public AVLTreeNode<T> minimumOfSubtree(AVLTreeNode<T> curr) {
		if (curr == null) {
			return null;
		}
		while (curr.Left() != null) {
			curr = curr.Left();
		}
		return curr;
	}

	public AVLTreeNode<T> maximumOfSubtree(AVLTreeNode<T> curr) {
		if (curr == null) {
			return null;
		}
		while (curr.Right() != null) {
			curr = curr.Right();
		}
		return curr;
	}

	public LinkedList<T> InorderTraversal(AVLTreeNode<T> curr) {
		if (curr == null) {
			return new LinkedList<T>();
		}
		LinkedList<T> list = InorderTraversal(curr.Left());
		list.addLast(curr.getValue());
		list.addAll(InorderTraversal(curr.Right()));
		return list;
	}

	public void PrintTree() {
		InorderTraversal(this.root);
	}

}
