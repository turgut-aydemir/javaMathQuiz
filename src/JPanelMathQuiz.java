import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JPanelMathQuiz extends JFrame implements ActionListener { // Main class extending JFrame class
    public static JFrame f; // we will only have 1 frame, and more panels to show on it
    public static JButton buttonAddQuestion, buttonEditQuestions, buttonStartQuiz, buttonHighScore, buttonBack, buttonStart; // all buttons are defined at once here
    public static JPanel panelMainMenu, startQuizMenu;

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
        JPanel startQuizMenu = new JPanel();

        //panelMainMenu.setLayout(new BoxLayout(panelMainMenu, BoxLayout.Y_AXIS)); // Setting box layout

        panelMainMenu.add(buttonAddQuestion); // Adding buttons and textfield to panel
        panelMainMenu.add(buttonEditQuestions);
        panelMainMenu.add(buttonStartQuiz);
        panelMainMenu.add(buttonHighScore);

        startQuizMenu.add(buttonBack);
        startQuizMenu.add(buttonStart);

        panelMainMenu.setBackground(Color.green); // Setting background of panel
        panelMainMenu.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        panelMainMenu.setLayout(new GridLayout(2,2));

        startQuizMenu.setBackground(Color.yellow); // Setting background of panel
        startQuizMenu.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        startQuizMenu.setLayout(new GridLayout(4,2));

        f.add(panelMainMenu); // Adding panel to frame

        f.setSize(400, 400); // Setting the size of frame

        f.setVisible(true);
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
            JTextField usernameTextField = new JTextField("", 1);
            JTextField numberOFQuestionsTextField = new JTextField("", 1);
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
            panelStartQuiz.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
            panelStartQuiz.setLayout(new GridLayout(3, 2));
            f.setContentPane(panelStartQuiz);
            f.invalidate();
            f.validate();
            //b3.setText("how many questions do you want AND what is your name?");
            //Main myMain = new Main();
            //myMain.randomizer(0);
        }
        if(e.getSource()==buttonBack)
            {
                //new JPanelMathQuiz("Math Quiz");
                f.remove(startQuizMenu); //BURALARI DÜZENLE
                f.add(panelMainMenu); // Adding panel to frame

                f.setSize(400, 400); // Setting the size of frame

                f.setVisible(true);
            }
        if(e.getSource()==buttonStart)
            {
                new JPanelMathQuiz("Math Quiz");
            }

    }
    public static void main(String s[])
    {
        new JPanelMathQuiz("Math Quiz");
    }
}


