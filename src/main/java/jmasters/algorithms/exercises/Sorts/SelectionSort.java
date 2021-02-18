package jmasters.algorithms.exercises.Sorts;
import javax.swing.JFrame;

import sdossey.algorithms.datasets.RandomData;
import sdossey.algorithms.util.InstrumentedList;
import sdossey.algorithms.util.IntArrayVisualizer;
public class SelectionSort {

    public static final void main(String[] args)
    {
        double delay = .1; //Second delay between visualization step.
        IntArrayVisualizer visualizer = 
            new IntArrayVisualizer(RandomData.randomIntList(1, 51, 30), delay);

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
        

        for (int i = 0; i < list.size(); i++){
            int minIndex = i;
            for (int j = i+1; j < list.size(); j++){
                if(list.compare(minIndex, j) > 0){
                    minIndex = j;
                    
                }
            }
            list.exchange(i, minIndex);
        }    
    }
}
