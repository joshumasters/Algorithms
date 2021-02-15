package jmasters.algorithms.exercises;
import javax.swing.JFrame;

import sdossey.algorithms.datasets.RandomData;
import sdossey.algorithms.util.InstrumentedList;
import sdossey.algorithms.util.IntArrayVisualizer;
public class InsertionSort {

    public static final void main(String[] args)
    {
        double delay = 1; //Second delay between visualization step.
        IntArrayVisualizer visualizer = 
            new IntArrayVisualizer(RandomData.randomIntList(1, 51, 30), .2);

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
        for (int i = 1; i < list.size(); i++){
            for (int j = i - 1; j >= 0; j--){
                int result = list.compare(j, j+1);
                if(result <= 0){
                    break;
                } else {
                    list.exchange(j, j+1);
                }
            }
        }
    }
}
