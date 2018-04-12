package AVLTree.nodes;

public class AVLTreeNode<T extends Comparable<T>> extends TreeNode<T> {
    private AVLTreeNode<T> left;
    private AVLTreeNode<T> right;
    private AVLTreeNode<T> parent;
    public int height;
    private T data;

    public AVLTreeNode(T value) {
        this.setValue(value);
        this.left = null;
        this.right = null;
        this.parent = null;
        this.height = 0;

    }

    /* Returns the balance of a tree node,
     *   where balance is defined as the height of a node's right subtree
     *   minus the height of a node's left subtree.
     *
     *   @return current balance value of this node
     */
    public int getBalance() {
        //FIXME replace with your code here
        // Note: you should not need to modify the instance variables of this class.
        return 0;
    }

    public AVLTreeNode<T> Left() {
        return this.left;
    }

    public void setLeft(AVLTreeNode<T> left) {
        this.left = left;
    }

    public AVLTreeNode<T> Right() {
        return this.right;
    }

    public void setRight(AVLTreeNode<T> right) {
        this.right = right;
    }

    public AVLTreeNode<T> Parent() {
        return this.parent;
    }

    public void setParent(AVLTreeNode<T> parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return right == null && left == null;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }
}
