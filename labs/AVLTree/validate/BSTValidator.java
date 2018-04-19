package AVLTree.validate;

import AVLTree.nodes.AVLTreeNode;
import AVLTree.nodes.TreeNode;
import AVLTree.util.TreeToStrings;
import AVLTree.trees.*;

public class BSTValidator<T extends Comparable<T>> {
    final public BST<T> tree;
    private String before;

    public BSTValidator(final BST<T> tree) {
        this.tree = tree;
        this.before = tree.toString();
    }

    /**
     * The instance variable "before" captures the state of the heap
     * last time we looked.   This method runs our validation methods,
     * and if something goes wrong, it reports the before tree, the error,
     * and the after tree.  This should provide enough information to
     * diagnose your problems with your binary heap implementation.
     */
    private int sizeVerify;
    public void check() {
        try {
            TreeNode<T> root = tree.Root();
            sizeVerify = 0;
            CheckTree(root, null);
            if (sizeVerify!=tree.size()){
                throw new BSTValidationError(String.format("The tree does not contain all the elements. it should contain %s, but instead contains %s",
                        tree.size(), sizeVerify));
            }
            before = TreeToStrings.toTree(tree);
        } catch (Throwable t) {
            String oops = "\nTree before the problem occurred:\n";
            oops += before + "\n";
            oops += "What went wrong: " + t.getMessage() + "\n";
            // System.out.println("Its stack trace is ");
            // t.printStackTrace();
            oops += "Tree that triggered this problem:" + "\n";
            oops += TreeToStrings.toTree(tree);
            t.printStackTrace();
            throw new BSTValidationError(t + "" + oops);
        }
    }

    private int getHeight(TreeNode<T> curr) {
        if (curr == null) {
            return 0;
        }
        int lHeight = getHeight(curr.Left());
        int rHeight = getHeight(curr.Right());
        return Math.max(lHeight, rHeight) + 1;
    }

    /**
     * Make sure that the tree is a valid BST
     */
    public void CheckTree(TreeNode<T> child, TreeNode<T> parent) {
        if (child == null) {
            return;
        } else if (parent == null) {
            sizeVerify++;
            CheckTree(child.Left(), child);
            CheckTree(child.Right(), child);
        } else {
            sizeVerify++;
            if (child == parent.Left() && child.getValue().compareTo(parent.getValue()) > 0) {
                throw new BSTValidationError(String.format("The left child {%s} is larger than its parent {%s} ", child.getValue(), parent.getValue()));
            }
            if (child == parent.Right() && child.getValue().compareTo(parent.getValue()) < 0) {
                throw new BSTValidationError(String.format("The right child {%s} is smaller than its parent {%s} ", child.getValue(), parent.getValue()));
            }
            if (child != parent.Left() && child != parent.Right()) {
                throw new BSTValidationError(String.format("Parent {%s} and child {%s} are not properly linked. make sure to update any relationships when the tree is modified.", parent.getValue(), child.getValue()));
            }
            if (child.getClass() == AVLTreeNode.class) {
                int lHeight = getHeight(child.Left());
                int rHeight = getHeight(child.Right());
                AVLTreeNode<T> typed = (AVLTreeNode) child;
                if (typed.height != getHeight(child)) {
                    throw new BSTValidationError(String.format("The node {%s} does not return the correct getHeight(), expected %s, got %s",
                            child.getValue(), getHeight(child), typed.height));
                }
                if (typed.getBalance()!=rHeight-lHeight){
                    throw new BSTValidationError(String.format("The node {%s} does not return the correct getBalance(), expected %s, got %s",
                            child.getValue(), rHeight-lHeight, typed.getBalance()));
                }
                if (typed.getBalance()<-1 || typed.getBalance()>1){
                    throw new BSTValidationError(String.format("The tree is not balanced starting at node %s", child.getValue()));
                }
            }
            int rHeight = getHeight(child.Right());

            CheckTree(child.Left(), child);
            CheckTree(child.Right(), child);
        }
    }
}