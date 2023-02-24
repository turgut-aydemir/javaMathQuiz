import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JPanelMathQuiz extends JFrame { // Main class extending JFrame class
    //public JFrame f; // we will only have 1 frame, and more panels to show on it
    //public JButton buttonAddQuestion, buttonEditQuestions, buttonStartQuiz, buttonHighScore, buttonBack, buttonStart, buttonSave, buttonPrevious, buttonNext; // all buttons are defined at once here
    //public JPanel panelMain, panelAddQuestion, panelEditQuestions, panelStartQuiz, panelHighScore, panelQuizRound;
    public JPanel panelMain;
    private final CardLayout cardLayout;
    /*void otherPanel() //constructor
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
        buttonSave = new JButton("Start");
        buttonSave.addActionListener(this);
        buttonPrevious = new JButton("Start");
        buttonPrevious.addActionListener(this);
        buttonNext = new JButton("Start");
        buttonNext.addActionListener(this);


        JPanel panelMain = new JPanel(); // Creating a panel to add buttons and textfield and a layout

        JPanel panelEditQuestions = new JPanel();
        JPanel panelStartQuiz = new JPanel();
        JPanel panelHighScore = new JPanel();
        JPanel panelQuizRound = new JPanel();
        //panelMainMenu.setLayout(new BoxLayout(panelMainMenu, BoxLayout.Y_AXIS)); // Setting box layout

        panelMain.add(buttonAddQuestion); // Adding buttons and textfield to panel
        panelMain.add(buttonEditQuestions);
        panelMain.add(buttonStartQuiz);
        panelMain.add(buttonHighScore);
        panelMain.setBackground(Color.green); // Setting background of panel
        panelMain.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        panelMain.setLayout(new GridLayout(2,2));


        panelEditQuestions.add(buttonBack);
        panelEditQuestions.add(buttonSave);

        panelStartQuiz.add(buttonBack);
        panelStartQuiz.add(buttonStart);
        panelStartQuiz.setBackground(Color.yellow); // Setting background of panel
        panelStartQuiz.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        panelStartQuiz.setLayout(new GridLayout(4,2));

        panelHighScore.add(buttonBack);

        panelQuizRound.add(buttonPrevious);
        panelQuizRound.add(buttonNext);

        f.add(panelMain); // Adding panel to frame

        f.setSize(800, 800); // Setting the size of frame

        f.setVisible(true);
    }
*/
    JPanelMathQuiz(String s) //constructor
    {
        setTitle("Math Quiz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        //f = new JFrame("Math Quiz"); // Creating a new frame to store text field and button

        panelMain = new JPanel();
        setPreferredSize(new Dimension(700,700));
        cardLayout = new CardLayout();
        panelMain.setLayout(cardLayout);

        JPanel panelEditQuestions = new JPanel();
        JPanel panelStartQuiz = new JPanel();
        JPanel panelHighScore = new JPanel();
        JPanel panelQuizRound = new JPanel();
        JPanel panelAddQuestion = new JPanel();

        panelMain.add("Quiz Round",panelQuizRound);
        panelQuizRound.setBackground(Color.RED);
        panelMain.add("Add Question",panelAddQuestion);
        panelAddQuestion.setBackground(Color.GREEN);
        panelMain.add("Edit Questions",panelEditQuestions);
        panelEditQuestions.setBackground(Color.YELLOW);
        panelMain.add("Start Quiz",panelStartQuiz);
        panelMain.add("High Score",panelHighScore);
        panelMain.add("Quiz Round",panelQuizRound);

        JButton buttonAddQuestion = new JButton("Add Question"); // Creating a new buttons
        buttonAddQuestion.addActionListener(e -> showAddQuestionPanel());
        JButton buttonEditQuestions = new JButton("Edit Questions");
        buttonEditQuestions.addActionListener(e -> showAddQuestionPanel());
        JButton buttonStartQuiz = new JButton("Start Quiz");
        buttonStartQuiz.addActionListener(e -> showAddQuestionPanel());
        JButton buttonHighScore = new JButton("High Score");
        buttonHighScore.addActionListener(e -> showAddQuestionPanel());
        JButton buttonBack = new JButton("Back");
        buttonBack.addActionListener(e -> showAddQuestionPanel());
        JButton buttonStart = new JButton("Start");
        buttonStart.addActionListener(e -> showAddQuestionPanel());
        JButton buttonSave = new JButton("Start");
        buttonSave.addActionListener(e -> showAddQuestionPanel());
        JButton buttonPrevious = new JButton("Start");
        buttonPrevious.addActionListener(e -> showAddQuestionPanel());
        JButton buttonNext = new JButton("Start");
        buttonNext.addActionListener(e -> showAddQuestionPanel());

        panelMain.add(buttonAddQuestion); // Adding buttons and textfield to panel
        panelMain.add(buttonEditQuestions);
        panelMain.add(buttonStartQuiz);
        panelMain.add(buttonHighScore);

        cardLayout.show(panelMain,"High Score");
        getContentPane().setLayout(new GridLayout(4,1));
        getContentPane().add(panelMain);
        //add(panelMain,GridLayout());
        add(buttonAddQuestion,BorderLayout.WEST);
        pack();
        setVisible(true);

/*
        //JPanel panelMain = new JPanel(); // Creating a panel to add buttons and textfield and a layout

        JPanel panelEditQuestions = new JPanel();
        JPanel panelStartQuiz = new JPanel();
        JPanel panelHighScore = new JPanel();
        JPanel panelQuizRound = new JPanel();
        //panelMainMenu.setLayout(new BoxLayout(panelMainMenu, BoxLayout.Y_AXIS)); // Setting box layout

        panelMain.add(buttonAddQuestion); // Adding buttons and textfield to panel
        panelMain.add(buttonEditQuestions);
        panelMain.add(buttonStartQuiz);
        panelMain.add(buttonHighScore);
        panelMain.setBackground(Color.green); // Setting background of panel
        panelMain.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        panelMain.setLayout(new GridLayout(2,2));


        panelEditQuestions.add(buttonBack);
        panelEditQuestions.add(buttonSave);

        panelStartQuiz.add(buttonBack);
        panelStartQuiz.add(buttonStart);
        panelStartQuiz.setBackground(Color.yellow); // Setting background of panel
        panelStartQuiz.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        panelStartQuiz.setLayout(new GridLayout(4,2));

        panelHighScore.add(buttonBack);

        panelQuizRound.add(buttonPrevious);
        panelQuizRound.add(buttonNext);

        f.add(panelMain); // Adding panel to frame

        f.setSize(800, 800); // Setting the size of frame

        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== buttonAddQuestion)
        {
            JPanel panelAddQuestion = new JPanel();
            panelAddQuestion.add(buttonBack);
            buttonBack.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed( ActionEvent e ) {
                    otherPanel();
                }});
            panelAddQuestion.add(buttonSave);
            panelAddQuestion.revalidate();
            panelAddQuestion.repaint();
            f.setContentPane(panelAddQuestion);
            f.invalidate();
            f.validate();
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
                f.remove(panelStartQuiz); //BURALARI DÜZENLE
                f.add(panelMain); // Adding panel to frame

                f.setSize(400, 400); // Setting the size of frame

                f.setVisible(true);
            }
        if(e.getSource()==buttonStart)
            {
                new JPanelMathQuiz("Math Quiz");
            }
*/
    }
    void showAddQuestionPanel(){
        cardLayout.show(panelMain,"Add Question");
    }
    public static void main(String s[])
    {
        new JPanelMathQuiz("Math Quiz");
    }
}


