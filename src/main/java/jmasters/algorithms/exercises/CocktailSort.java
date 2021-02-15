package jmasters.algorithms.exercises;

import javax.swing.JFrame;

import sdossey.algorithms.datasets.RandomData;
import sdossey.algorithms.util.InstrumentedList;
import sdossey.algorithms.util.IntArrayVisualizer;
public class CocktailSort {
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
    }
}

