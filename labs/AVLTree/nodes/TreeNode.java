package AVLTree.nodes;

public abstract class TreeNode<T extends Comparable<T>>{
    private T value;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private TreeNode<T> parent;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<T> Left() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> Right() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode<T> Parent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public boolean isLeaf(){
        return right==null && left==null;
    }
}
