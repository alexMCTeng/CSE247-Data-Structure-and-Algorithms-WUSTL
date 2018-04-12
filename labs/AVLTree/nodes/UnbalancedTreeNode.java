package AVLTree.nodes;

public class UnbalancedTreeNode<T extends Comparable<T>> extends TreeNode<T>{

    UnbalancedTreeNode<T> left;
    UnbalancedTreeNode<T> right;
    UnbalancedTreeNode<T> parent;
    public UnbalancedTreeNode(T data){
        super();
        this.setValue(data);
    }


    public UnbalancedTreeNode<T> Left() {
        return left;
    }

    public void setLeft(UnbalancedTreeNode<T> left) {
        this.left = left;
    }

    public UnbalancedTreeNode<T> Right() {
        return right;
    }

    public void setRight(UnbalancedTreeNode<T> right) {
        this.right = right;
    }

    public UnbalancedTreeNode<T> Parent() {
        return parent;
    }

    public void setParent(UnbalancedTreeNode<T> parent) {
        this.parent = parent;
    }

    public String toString() {
        return this.getValue().toString();
    }

}
