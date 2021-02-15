package jmasters.algorithms.exercises.BinaryTreeSort;

public class BinaryTreeNode<T extends Comparable<T>> {

    T data;
    BinaryTreeNode<T> parent;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

    public BinaryTreeNode(T data, BinaryTreeNode<T> parent, BinaryTreeNode<T> left,  BinaryTreeNode<T> right){
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }


    

    

}
