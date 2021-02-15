package jmasters.algorithms.exercises.BinaryTreeSort;

import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {

    BinaryTreeNode<T> root;

    public BinaryTree() {
    }

    public void add(BinaryTreeNode<T> node, BinaryTreeNode<T> passRoot) {
        // Send root check to a non-overloaded method
        if (root == null) {
            root = node;
        } else {
            if (node.getData().compareTo(passRoot.getData()) <= 0) {
                if (passRoot.getLeft() != null) {
                    passRoot = passRoot.getLeft();
                    add(node, passRoot);
                } else {
                    passRoot.setLeft(node);
                    node.setParent(passRoot);
                }
            } else {
                if (passRoot.getRight() != null) {
                    passRoot = passRoot.getRight();
                    add(node, passRoot);
                } else {
                    passRoot.setRight(node);
                    node.setParent(passRoot);
                }

            }
        }

    }

    public BinaryTreeNode<T> find(T valueToFind) {
            return find(valueToFind, root);
    }

    private BinaryTreeNode<T> find(T valueToFind, BinaryTreeNode<T> passRoot) {
        if(passRoot == null){
            return null;
        }
        if (passRoot.getData().compareTo(valueToFind) == 0) {
            System.out.println("Found It!");
            return passRoot;
        } else {
            if (valueToFind.compareTo(passRoot.getData()) < 0) {
                    return find(valueToFind, passRoot.getLeft());
                } else {
                    return find(valueToFind, passRoot.getRight());
            }
        }
    }

    public ArrayList<T> walkAndSort() {
        ArrayList<T> arrayToReturn = new ArrayList<>();
        if (root == null){
            return null;
        }
        return walkAndSort(arrayToReturn, root);
    }

    private ArrayList<T> walkAndSort(ArrayList<T> arrayToReturn, BinaryTreeNode<T> passRoot) {
        if (passRoot.getLeft() != null) {
            walkAndSort(arrayToReturn, passRoot.getLeft());
        }

        arrayToReturn.add(passRoot.getData());

        if (passRoot.getRight() != null) {
            walkAndSort(arrayToReturn, passRoot.getRight());
        }
        return arrayToReturn;

    }
    // SCOTTS walkAndSort() METHOD
    // public ArrayList<T> walkAndSort() {
    //     ArrayList<T> arrayToReturn = new ArrayList<>();
    //     walkAndSort(arrayToReturn, root);
    //     return arrayToReturn;
    // }

    // private void walkAndSort(ArrayList<T> arrayToReturn, BinaryTreeNode<T> passRoot) {
    //     if (passRoot != null)
    //     {
    //         walkAndSort(arrayToReturn, passRoot.getLeft());
    //         arrayToReturn.add(passRoot.getData());
    //         walkAndSort(arrayToReturn, passRoot.getRight());
    //     }
    // }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>(6, null, null, null);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>(3, null, null, null);
        BinaryTreeNode<Integer> node3 = new BinaryTreeNode<>(8, null, null, null);
        BinaryTreeNode<Integer> node4 = new BinaryTreeNode<>(4, null, null, null);
        BinaryTreeNode<Integer> node5 = new BinaryTreeNode<>(10, null, null, null);
        BinaryTreeNode<Integer> node6 = new BinaryTreeNode<>(10, null, null, null);
        BinaryTreeNode<Integer> node7 = new BinaryTreeNode<>(7, null, null, null);
        tree.add(node1, tree.root);
        tree.add(node2, tree.root);
        tree.add(node3, tree.root);
        tree.add(node4, tree.root);
        tree.add(node5, tree.root);
        tree.add(node6, tree.root);
        tree.add(node7, tree.root);
        tree.find(6);
        System.out.println(tree.walkAndSort());

        // tree.find(7, tree.root);

    }

}