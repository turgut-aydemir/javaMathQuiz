import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JPanelMathQuiz extends JFrame implements ActionListener { // Main class extending JFrame class
    static JFrame f; // JFrame
    static JButton b1, b2, b3, b4; // JButton

    JPanelMathQuiz(String s)
    {
        f = new JFrame("Math Quiz"); // Creating a new frame to store text field and button

        b1 = new JButton("Add Question"); // Creating a new buttons
        b1.addActionListener(this);
        b2 = new JButton("Edit Questions");
        b2.addActionListener(this);
        b3 = new JButton("Start Quiz");
        b3.addActionListener(this);
        b4 = new JButton("High Score");
        b4.addActionListener(this);

        JPanel panelMainMenu = new JPanel(); // Creating a panel to add buttons and textfield and a layout

        //panelMainMenu.setLayout(new BoxLayout(panelMainMenu, BoxLayout.Y_AXIS)); // Setting box layout

        panelMainMenu.add(b1); // Adding buttons and textfield to panel
        panelMainMenu.add(b2);
        panelMainMenu.add(b3);
        panelMainMenu.add(b4);

        panelMainMenu.setBackground(Color.green); // Setting background of panel
        panelMainMenu.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        panelMainMenu.setLayout(new GridLayout(2,2));

        f.add(panelMainMenu); // Adding panel to frame

        f.setSize(400, 400); // Setting the size of frame

        f.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1)
        {
            b1.setEnabled(false);
            b1.setText("you can add questions");
        }
        if(e.getSource()==b2)
        {
            b2.setText("editing questions");
            setVisible(false);
            setVisible(true);
        }
        if(e.getSource()==b3)
            {
                JPanel panelStartQuiz = new JPanel();
                JLabel usernameLabel = new JLabel("Name");
                JLabel numberOFQuestionsLabel = new JLabel("# of Questions");
                JTextField usernameTextField = new JTextField("0",10);
                JTextField numberOFQuestionsTextField = new JTextField("0",10);
                panelStartQuiz.add(usernameLabel);
                panelStartQuiz.add(usernameTextField);
                panelStartQuiz.add(numberOFQuestionsLabel);
                panelStartQuiz.add(numberOFQuestionsTextField);
                b1 = new JButton("Back"); // Creating a new buttons
                b1.addActionListener(this);
                b2 = new JButton("Start");
                b2.addActionListener(this);
                panelStartQuiz.add(b1); // Adding buttons and textfield to panel
                panelStartQuiz.add(b2);
                panelStartQuiz.setBackground(Color.yellow); // Setting background of panel
                panelStartQuiz.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
                panelStartQuiz.setLayout(new GridLayout(4,2));
                f.setContentPane(panelStartQuiz);
                f.invalidate();
                f.validate();
                //b3.setText("how many questions do you want AND what is your name?");
                //Main myMain = new Main();
                //myMain.randomizer(0);
        }
        if(e.getSource()==b4)
        {
            JOptionPane.showMessageDialog(this,"High Score");
            System.exit(0);
        }
    }
    public static void main(String s[])
    {
        new JPanelMathQuiz("Math Quiz");
    }
}


