import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JPanelMathQuiz extends JFrame implements ActionListener { // Main class extending JFrame class
    static JFrame f; // JFrame
    static JButton buttonAddQuestion, buttonEditQuestions, buttonStartQuiz, buttonHighScore, buttonBack, buttonStart; // JButton
    //public JPanel panelMainMenu;

    JPanelMathQuiz(String s)
    {
        f = new JFrame("Math Quiz"); // Creating a new frame to store text field and button

        buttonAddQuestion = new JButton("Add Question"); // Creating a new buttons
        buttonAddQuestion.addActionListener(this);
        buttonEditQuestions = new JButton("Edit Questions");
        buttonEditQuestions.addActionListener(this);
        buttonStartQuiz = new JButton("Start Quiz");
        buttonStartQuiz.addActionListener(this);
        buttonHighScore = new JButton("High Score");
        buttonHighScore.addActionListener(this);
        buttonBack = new JButton("Back");
        buttonBack.addActionListener(this);
        buttonStart = new JButton("Start");
        buttonStart.addActionListener(this);
        JPanel panelMainMenu = new JPanel(); // Creating a panel to add buttons and textfield and a layout

        //panelMainMenu.setLayout(new BoxLayout(panelMainMenu, BoxLayout.Y_AXIS)); // Setting box layout

        panelMainMenu.add(buttonAddQuestion); // Adding buttons and textfield to panel
        panelMainMenu.add(buttonEditQuestions);
        panelMainMenu.add(buttonStartQuiz);
        panelMainMenu.add(buttonHighScore);

        panelMainMenu.setBackground(Color.green); // Setting background of panel
        panelMainMenu.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        panelMainMenu.setLayout(new GridLayout(2,2));

        f.add(panelMainMenu); // Adding panel to frame

        f.setSize(400, 400); // Setting the size of frame

        f.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== buttonAddQuestion)
        {
            buttonAddQuestion.setEnabled(false);
            buttonAddQuestion.setText("you can add questions");
        }
        if(e.getSource()== buttonEditQuestions)
        {
            buttonEditQuestions.setText("editing questions");
            setVisible(false);
            setVisible(true);
        }
        if(e.getSource()== buttonStartQuiz)
            {
                JPanel panelStartQuiz = new JPanel();
                JLabel usernameLabel = new JLabel("Name: ");
                JLabel numberOFQuestionsLabel = new JLabel("Number of questions: ");
                JTextField usernameTextField = new JTextField("",1);
                JTextField numberOFQuestionsTextField = new JTextField("",1);
                panelStartQuiz.add(usernameLabel);
                panelStartQuiz.add(usernameTextField);
                panelStartQuiz.add(numberOFQuestionsLabel);
                panelStartQuiz.add(numberOFQuestionsTextField);
                JButton bBack = new JButton("Back"); // Creating a new buttons
                bBack.addActionListener(this);
                JButton bStart = new JButton("Start");
                bStart.addActionListener(this);
                panelStartQuiz.add(bBack); // Adding buttons and textfield to panel
                panelStartQuiz.add(bStart);
                panelStartQuiz.setBackground(Color.yellow); // Setting background of panel
                panelStartQuiz.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
                panelStartQuiz.setLayout(new GridLayout(3,2));
                f.setContentPane(panelStartQuiz);
                f.invalidate();
                f.validate();
                //b3.setText("how many questions do you want AND what is your name?");
                //Main myMain = new Main();
                //myMain.randomizer(0);
                if(e.getSource()==bBack)
                {
                    new JPanelMathQuiz("Math Quiz");
                }
                if(e.getSource()==bStart)
                {
                    new JPanelMathQuiz("Math Quiz");
                }
        }
        if(e.getSource()== buttonHighScore)
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


