package AVLTree.trees;

import AVLTree.nodes.TreeNode;

public abstract class BST<T extends Comparable<T>>{
    T value;
    TreeNode<T> root;
    int size;
    public int capacity(){
        return 100;
    }
    public T peek(int n){
        return value;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public abstract TreeNode<T> Root();
    public abstract TreeNode<T> Find(T value);
    public abstract TreeNode<T> Insert(T value);


}