import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.nio.file.*;
import java.util.*;

public class Graph extends JPanel{

    //TODO read from result.text file, parse values for "percentage" & "average time", add them in array (coordinates) and plot graphs (x2).
    //2 graphs: improements in time and percentage + total number of questions (show interesting statistics)
    public static Scanner scanner = new Scanner("C:\\Users\\turgu\\IdeaProjects\\javaMathQuiz\\src\\results.txt");
        String line = scanner.nextLine();
        //System.out.println(line);
       // scanner.close();
    int[] coordinates={100,20}; //will be changed after reading data from text file
    int mar=50; //this will be i++ (just the number of tries File.length)

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g1=(Graphics2D)g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int width=getWidth();
        int height=getHeight();
        g1.draw(new Line2D.Double(mar,mar,mar,height-mar));
        g1.draw(new Line2D.Double(mar,height-mar,width-mar,height-mar));
        double x=(double)(width-2*mar)/(coordinates.length-1);
        double scale=(double)(height-2*mar)/getMax();
        g1.setPaint(Color.BLUE);
        for(int i=0;i<coordinates.length;i++){
            double x1=mar+i*x;
            double y1=height-mar-scale*coordinates[i];
            g1.fill(new Ellipse2D.Double(x1-2,y1-2,4,4));
        }
    }
    private int getMax(){
        int max=-Integer.MAX_VALUE;
        for(int i=0;i<coordinates.length;i++){
            if(coordinates[i]>max)
                max=coordinates[i];

        }return max;
    }
}