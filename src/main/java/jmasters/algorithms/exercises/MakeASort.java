package jmasters.algorithms.exercises;

import javax.swing.JFrame;

import sdossey.algorithms.datasets.RandomData;
import sdossey.algorithms.util.InstrumentedList;
import sdossey.algorithms.util.IntArrayVisualizer;

public class MakeASort
{
    public static final void main(String[] args)
    {
        double delay = .1; //Second delay between visualization step.
        IntArrayVisualizer visualizer = 
            new IntArrayVisualizer(RandomData.randomIntList(1, 51, 31), delay);

        JFrame frame = new JFrame("Sorting Visualization App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(visualizer);        
        frame.setResizable(true);
        frame.setSize(800,600);
        frame.setVisible(true);

        sort(visualizer.getInstrumentedList());        
    }    

    static final void sort(InstrumentedList<Integer> list)
    {
        // SOLUTION 1 (Works but takes an eternity)
        
        // for (int i = 1; i < list.size(); i++){
        //     for (int j = i - 1; j >= 0; j--){
        //         int result = list.compare(j, j+1);
        //         if(result <= 0){
        //             break;
        //         } else {
        //             list.exchange(j, j+1);
        //         }
        //     }
        // }

        // SOLUTION 2 (Same as 1 but with While Loop) DOESNT SORT FIRST INDEX
        // for(int i = 0; i < list.size() - 1; i++){
        //     int j = i + 1;
        //     while(list.compare(i, j) > 0){
        //         list.exchange(i, j);
        //         i = 0;
        //     }
        // }

        // SOLUTION 3 (Starts from beginning numerically, and sorts backwards numberically)
        // for (int i = 0; i < list.size(); i++){
        //     for (int j = i + 1; j < list.size(); j++){
        //         if(list.compare(i, j) < 0 || list.compare(i, j) == 0){
        //             break;
        //         } else {
        //             while(list.compare(i, j) > 0){
        //                 list.exchange(i, j);
        //                 if (i > 0){
        //                     i--;
        //                     j--;
        //                 } else {
        //                     break;
        //                 }
                        
        //             }
        //             i = 0; 
        //         }
        //     }
        // }

        // SOLUTION 4 - Improved Front Search
        // for (int i = 0; i < list.size(); i++){
        //     for (int j = i+1; j < list.size(); j++){
        //         int counter = 0;
        //         if(list.compare(i,j) <= 0){
        //             break;
        //         } else {
        //             while(list.compare(i,j) > 0){
        //                 list.exchange(i,j);
                        
        //                 counter++;
        //                 if (i > 0){
        //                     i--;
        //                     j--;
        //                 } else {
        //                 break;
        //                 }
        //             }
        //             i = i + counter -1;
        //             j = j + counter -1;
        //             break;

        //         }
        //     }
        // }
        // MEATHEAD SOLUTION
            // // for (int i = 0; i < list.size(); i++){
            // //     for (int j = i+1; j < list.size(); j++){
            // //         if(list.compare(i,j) > 0){
            //             list.exchange(i, j);
            // //         }
            // //     }
            // // }
            
            // for (int i = 0; i < list.size(); i++){
            //     int minIndex = i+1;
            //     for (int j = i+2; j < list.size(); j++){
            //         if(list.compare(minIndex, j) > 0){
            //             minIndex = j;
                        
            //         }
            //     }
            //     list.exchange(i, minIndex);
            // }

            
        int beginning = 0;
        int end = list.size() -1;
        
        
        while(beginning < end){
            int biggest = list.get(beginning);
            int smallest = list.get(beginning);
            int bigIndex = beginning;
            int smallIndex = beginning;
            for (int i = beginning + 1; i <= end; i++){
                if (list.get(i) > biggest){
                    biggest = list.get(i);
                    bigIndex = i;
                }
                if (list.get(i) < smallest){
                    smallest = list.get(i);
                    smallIndex = i;
                }
            }
            System.out.println("The biggest number is " + biggest + " and the smallest is " + smallest);
            
                list.exchange(beginning, smallIndex);
            // Comment: Check for whether bigIndex was at beginning, in which case it is now at smallIndex after above exchange
            if(bigIndex != beginning){
                list.exchange(end, bigIndex);
            } else {
                list.exchange(end, smallIndex);
            } 
            System.out.println(beginning + " , " + end);
            System.out.println(bigIndex + " , " + smallIndex);
            beginning++;
            end--;
            
        }
    
        //We are only allowed to use the following methods:
        //
        //Integer list.get(index) -- returns what is at an index.
        //
        //int list.compare(indexA, indexB); 
        //     returns negative number is a<b, 0 if a.equals(b), and 
        //             positive number if a>b
        //
        //int list.exchange(indexA, indexB);
        //      exchange the element at indexA with the element at indexB        
    }
}