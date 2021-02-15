package jmasters.algorithms.exercises.Heaps;

import java.util.ArrayList;

public class HeapSort<T extends Comparable<T>> {

    ArrayList<T> heapStorage = new ArrayList<>();

    public HeapSort() {
        heapStorage.add(null);
    }

    public ArrayList<T> add(T valueToAdd) {
        heapStorage.add(valueToAdd);
        if (heapStorage.size() != 1) {
            swimUpHeap(heapStorage, heapStorage.size() - 1);
        }
        System.out.println("Final add " + heapStorage.toString());
        return heapStorage;
    }

   

    // Parent is index/2, left child is index2, right child is index2+1
    private void swimUpHeap(ArrayList<T> data, int index) {
        while (index != 1) {
            if (data.get(index).compareTo(data.get(index / 2)) > 0) {
                swap(data, index / 2, index);
                index = index / 2;
            } else {
                break;
            }
        }

    }

    private void swap(ArrayList<T> data, int index1, int index2) {
            T value1 = data.get(index1);
            T value2 = data.get(index2);
            data.set(index1, value2);
            data.set(index2, value1);
            System.out.println("Values after swap " + heapStorage.toString());
        }
        
    private void sinkDownHeap(ArrayList<T> data, int maxIndex){
        T headValue = heapStorage.get(1);
        int currentIndex = 1;
        int biggestValueIndex;
        
        while(currentIndex * 2 < maxIndex - 1){
            if(currentIndex * 2 == heapStorage.size() - 1){
                if(data.get(currentIndex * 2).compareTo(headValue) > 0){
                    swap(data, currentIndex * 2, currentIndex);
                    currentIndex = currentIndex * 2;
                } else {
                    break;
                }
            } else {
                if(data.get(currentIndex * 2).compareTo(data.get((currentIndex * 2) + 1)) > 0){
                    biggestValueIndex = currentIndex * 2;
                } else {
                    biggestValueIndex = (currentIndex * 2) + 1;
                }

                if(data.get(biggestValueIndex).compareTo(headValue) > 0){
                    swap(data, biggestValueIndex, currentIndex);
                    currentIndex = biggestValueIndex;
                } else {
                    break;
                }

            }
        }


    }

    public ArrayList<T> heapSort(){
        for(int i = heapStorage.size() - 1; i > 1; i--){
            if(i == 6){
                System.out.println("potato");
            }
            swap(heapStorage, 1, i);
            sinkDownHeap(heapStorage, i);
            System.out.println(i);
        }
        System.out.println(heapStorage.toString());
        return heapStorage;
    }

    public static void main(String[] args) {
        HeapSort<Integer> heapObject = new HeapSort<>();
        heapObject.add(56);
        heapObject.add(38);
        heapObject.add(82);
        heapObject.add(0);
        heapObject.add(567);
        heapObject.add(7885);
        heapObject.add(758);
        heapObject.add(749);
        heapObject.add(60);
        heapObject.heapSort();


    }
}
