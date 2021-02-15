package jmasters.algorithms.exercises;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ruler extends JComponent
{
	private static final long serialVersionUID = 1L;

	public Ruler()
    {
        super();        
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Ruler App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Ruler());
        frame.setSize(1200,50);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Rectangle r=this.getBounds();
        int width = (int)r.getWidth();
    
        markRuler(g, width);
    }

    public void makeMark(Graphics g, double xOffset, double size)
    {
        g.drawLine((int)xOffset, 0, (int)xOffset, (int)size);
    }

    public void markRuler(Graphics g, double width)
    {

        double start = 0;

        double end = width;

        int markSize = 30;
        
        markRuler(g, start, end, markSize);
        
        

        

        //Change the code so that:
        //The ruler is marked as:
        // 1/2 point on the ruler is marked 30 units long
        // 1/4 points on the ruler are marked 25 units long (if not already marked)
        // 1/8 points on the ruler are marked 20 untis long
        // 1/16 points on the ruler are marked 15 units long
        // 1/32 points on the ruler are marked 10 units long
        // 1/64 points on the ruler are marked 5 units long

        //It is okay to add methods.
    }
    
    public void markRuler(Graphics g, double begin, double end, int markSize){
        if(markSize >=5){
        double midPoint = (begin + end) / 2;
        makeMark(g, midPoint, markSize);
        markRuler(g, begin, midPoint, markSize - 5);
        markRuler(g, midPoint, end, markSize - 5);
        }
    }

}
