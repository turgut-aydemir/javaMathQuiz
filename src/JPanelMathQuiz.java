import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JPanelMathQuiz extends JFrame { // Main class extending JFrame class
    static JFrame f; // JFrame
    static JButton b, b1, b2, b3; // JButton
    static JLabel l; // Label to display text

    public static void main(String[] args)
    {
        f = new JFrame("Math Quiz"); // Creating a new frame to store text field and button

        //l = new JLabel("MathQuiz"); // Creating a label to display text

        b = new JButton("Add Question"); // Creating a new buttons
        b1 = new JButton("Start Quiz");
        b2 = new JButton("High Score");
        b3 = new JButton("Exit");

        JPanel p = new JPanel(); // Creating a panel to add buttons and textfield and a layout

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS)); // Setting box layout

        p.add(b); // Adding buttons and textfield to panel
        p.add(b1);
        p.add(b2);
        p.add(b3);
        //p.add(l);

        p.setBackground(Color.yellow); // Setting background of panel

        f.add(p); // Adding panel to frame

        f.setSize(400, 400); // Setting the size of frame

        f.show();
    }
}


