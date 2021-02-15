package jmasters.algorithms.exercises;

import javax.swing.JFrame;

import sdossey.algorithms.datasets.RandomData;
import sdossey.algorithms.util.InstrumentedList;
import sdossey.algorithms.util.IntArrayVisualizer;
public class BubbleSort {
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
        int j = list.size() - 1;
        boolean exchangeFlag = true;
        while ((j > 0) && exchangeFlag) {
            exchangeFlag = false;
            for(int i = 0; i < j; i++){
                int next = i+1;
                if(list.compare(i, next) > 0){
                  list.exchange(i, next);  
                  exchangeFlag = true;
                }
            }  
            j--;
        }
       
       
            
    }       
}
