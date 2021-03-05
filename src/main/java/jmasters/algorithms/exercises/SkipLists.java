package jmasters.algorithms.exercises;

import java.util.ArrayList;
import java.util.Random;

public class SkipLists<T> {

    int size = 0;
    SkipListNode<T> head;
    SkipListNode<T> tail;
    SkipListNode<T> nodeScan;
    static Random random = new Random();

    public SkipLists() {
        head = new SkipListNode<T>(null);
        tail = new SkipListNode<T>(null);
        head.links.add(tail);
        head.skipCounts.add(1);
        nodeScan = head;
    }

    public static class SkipListNode<U> {
        U data;
        ArrayList<SkipListNode<U>> links = new ArrayList<>();
        ArrayList<Integer> skipCounts = new ArrayList<>();

        public SkipListNode(U data) {
            this.data = data;
        }
    }

    private int getLevels() {
        int nodeLevels = 1;
        for (int i = 0; i < head.links.size(); i++) {
            if (random.nextBoolean() == true) {
                nodeLevels++;
            } else {
                break;
            }
        }
        return nodeLevels;
    }

    public void scanList() {
        for (int i = 0; i < size; i++) {
            System.out.println("Data at index " + i + " is " + nodeScan.links.get(0).data);
            nodeScan = nodeScan.links.get(0);
        }
        System.out.println("List complete");
        nodeScan = head;
    }

    public void add(int index, T valueToAdd) throws Exception {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index is outside of valid range");
        }
        SkipListNode<T> node = new SkipListNode<>(valueToAdd);
        int numOfNodeLevels = getLevels();
        if (numOfNodeLevels > head.links.size()) {
            head.links.add(tail);
            head.skipCounts.add(size + 1);
        }
        int maxLevels = head.links.size();
        int skipCounts = 0;
        for (int listLevel = maxLevels - 1; listLevel >= 0; listLevel--) {
            while (skipCounts + nodeScan.skipCounts.get(listLevel) < index + 1) {
                if (nodeScan.skipCounts.size() <= listLevel) {
                    break;
                }
                skipCounts += nodeScan.skipCounts.get(listLevel);
                nodeScan = nodeScan.links.get(listLevel);
            }
            if (numOfNodeLevels >= listLevel + 1) {
                if (node.links.size() <= listLevel) {
                    while (node.links.size() < listLevel) {
                        node.links.add(null);
                        node.skipCounts.add(null);
                    }
                    node.links.add(listLevel, nodeScan.links.get(listLevel));
                    node.skipCounts.add(listLevel, (skipCounts + nodeScan.skipCounts.get(listLevel)) - index);
                } else {
                    node.links.set(listLevel, nodeScan.links.get(listLevel));
                    node.skipCounts.set(listLevel, (skipCounts + nodeScan.skipCounts.get(listLevel)) - index);
                }
                nodeScan.links.set(listLevel, node);
                nodeScan.skipCounts.set(listLevel, (index + 1) - (skipCounts));
            } else {
                nodeScan.skipCounts.set(listLevel, nodeScan.skipCounts.get(listLevel) + 1);
            }
        }
        size++;
        nodeScan = head;
    }

    public T get(int index) throws Exception {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index is outside of valid range");
        }
        int skipCounts = 0;
        for (int listLevel = head.links.size() - 1; listLevel >= 0; listLevel--) {
            while (skipCounts + nodeScan.skipCounts.get(listLevel) < index + 1) {
                skipCounts += nodeScan.skipCounts.get(listLevel);
                nodeScan = nodeScan.links.get(listLevel);
            }
            if (skipCounts + nodeScan.skipCounts.get(listLevel) == index + 1) {
                SkipListNode<T> nodeToReturn = nodeScan.links.get(listLevel);
                nodeScan = head;
                System.out.println("Get method returns " + nodeToReturn.data);
                return nodeToReturn.data;
            }
        }

        nodeScan = head;
        return null;
    }

    public T set(int index, T dataToChange) throws Exception {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index is outside of valid range");
        }
        int skipCounts = 0;
        for (int listLevel = head.links.size() - 1; listLevel >= 0; listLevel--) {
            while (skipCounts + nodeScan.skipCounts.get(listLevel) < index + 1) {
                skipCounts += nodeScan.skipCounts.get(listLevel);
                nodeScan = nodeScan.links.get(listLevel);
            }
            if (skipCounts + nodeScan.skipCounts.get(listLevel) == index + 1) {
                SkipListNode<T> nodeToReturn = nodeScan.links.get(listLevel);
                System.out.println("Replaced " + nodeToReturn.data + " with " + dataToChange);
                nodeToReturn.data = dataToChange;
                nodeScan = head;
                return nodeToReturn.data;
            }
        }

        nodeScan = head;
        return null;
    }

    public int size() {
        return size;
    }

    public T remove(int index) throws Exception {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index is outside of valid range");
        }
        int skipCounts = 0;
        for (int listLevel = head.links.size() - 1; listLevel >= 0; listLevel--) {
            while (skipCounts + nodeScan.skipCounts.get(listLevel) < index + 1) {
                skipCounts += nodeScan.skipCounts.get(listLevel);
                nodeScan = nodeScan.links.get(listLevel);
            }
            if (skipCounts + nodeScan.skipCounts.get(listLevel) == index + 1) {
                SkipListNode<T> nodeToRemove = nodeScan.links.get(listLevel);
                if (nodeToRemove.links.size() >= listLevel + 1) {
                    nodeScan.links.set(listLevel, nodeToRemove.links.get(listLevel));
                    nodeScan.skipCounts.set(listLevel, nodeScan.skipCounts.get(listLevel) + nodeToRemove.skipCounts.get(listLevel) - 1);
                    if(listLevel != 0){
                        continue;
                    }
                } else {
                    continue;
                }
                size--;
                nodeScan = head;
                System.out.println("Removed " + nodeToRemove.data);
                return nodeToRemove.data;
            } else {
                nodeScan.skipCounts.set(listLevel, nodeScan.skipCounts.get(listLevel) - 1);
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        SkipLists<Integer> list = new SkipLists<>();
        list.add(0, 7);
        list.add(1, 5);
        list.add(0, 43);
        list.add(0, 43);
        list.add(0, 43);
        list.add(0, 43);
        list.add(0, 43);
        list.add(0, 43);
        list.add(3, 49);
        list.add(3, 467);
        list.add(5, 444);
        list.scanList();
        list.remove(0);
        list.scanList();
        list.get(4);
        list.set(4, 543);
        list.get(4);

    }
}