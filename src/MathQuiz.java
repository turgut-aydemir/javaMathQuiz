import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class MathQuiz extends JFrame {

    // Initialization the value of current card is 1 .
    private int currentCard = 1;

    // Declaration of objects of CardLayout class.
    private CardLayout cl;

    public MathQuiz()
    {

        // Functions to set visibility of JFrame
        setTitle("Math Quiz");
        setSize(800, 800);

        JPanel jpMain = new JPanel(); // Creating Object of "Jpanel" class

        cl = new CardLayout(); // Initialization of object "cl" of CardLayout class.

        jpMain.setLayout(cl); // set the layout on jpMain

        // Initialization of other panels (AddQuestion, EditQuestions, StartQuiz, HighScore, QuizRound)
        JPanel jpAddQuestion = new JPanel();
        JPanel jpEditQuestions = new JPanel();
        JPanel jpStartQuiz = new JPanel();
        JPanel jpHighScore = new JPanel();
        JPanel jpQuizRound = new JPanel();
        JPanel jpLeftMenu = new JPanel();

        // Adding panels to the jpMain with constraints (we will show each panel on constraints)
        jpMain.add(jpAddQuestion,"1");
        jpMain.add(jpEditQuestions,"2");
        jpMain.add(jpStartQuiz,"3");
        jpMain.add(jpHighScore,"4");
        jpMain.add(jpQuizRound,"5");

        // Initialization of buttons
        JButton jbAddQuestion = new JButton("Add Question");
        JButton jbEditQuestions = new JButton("Edit Questions");
        JButton jbStartQuiz = new JButton("Start Quiz");
        JButton jbHighScore = new JButton("High Score");
        JButton jbBack = new JButton("Back");
        JButton jbSave = new JButton("Save");
        JButton jbStart = new JButton("Start");
        JButton jbPrevious = new JButton("Previous");
        JButton jbNext = new JButton("Next");

        // Adding buttons on Main panel
        jpLeftMenu.add(jbAddQuestion);
        jpLeftMenu.add(jbEditQuestions);
        jpLeftMenu.add(jbStartQuiz);
        jpLeftMenu.add(jbHighScore);

        // Adding buttons on HighScore panel
        jpHighScore.add(jbBack);

        // Adding buttons on AddQuestion panel
        jpAddQuestion.add(jbBack);
        jpAddQuestion.add(jbSave);

        // Adding buttons and fields on StartQuiz panel
        jpStartQuiz.add(jbBack);
        jpStartQuiz.add(jbStart);

        // Adding buttons and fields on EditQuestions panel
        jpEditQuestions.add(jbBack);
        jpEditQuestions.add(jbSave);

        // Adding buttons and fields on QuizRound panel
        jpEditQuestions.add(jbPrevious);
        jpEditQuestions.add(jbNext);

        jbAddQuestion.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                // used first c1 CardLayout
                cl.first(jpMain);

                // value of currentcard is 1
                currentCard = 1;
            }
        });

        // add firstbtn in ActionListener
        jbBack.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                // used first c1 CardLayout
                cl.first(jpMain);

                // value of currentcard is 1
                currentCard = 1;
            }
        });

        // add lastbtn in ActionListener
        jbNext.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                // used last c1 CardLayout
                cl.last(jpMain);

                // value of currentcard is 4
                currentCard = 4;
            }
        });

        // add nextbtn in ActionListener
        jbSave.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                // if condition apply
                if (currentCard < 4)
                {

                    // increment the value of currentcard by 1
                    currentCard += 1;

                    // show the value of currentcard
                    cl.show(jpMain, "" + (currentCard));
                }
            }
        });

        // add previousbtn in ActionListener
        jbPrevious.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                // if condition apply
                if (currentCard > 1) {

                    // decrement the value
                    // of currentcard by 1
                    currentCard -= 1;

                    // show the value of currentcard
                    cl.show(jpMain, "" + (currentCard));
                }
            }
        });

        // used to get content pane
        //getContentPane().add(jpMain, BorderLayout.WEST);

        jpMain.add(jpLeftMenu);
        getContentPane().add(jpMain, BorderLayout.WEST);
        // used to get content pane
        //getContentPane().add(jpLeftMenu, BorderLayout.WEST);
    }

    // Main Method
    public static void main(String[] args)
    {

        // Creating Object of CardLayoutDemo class.
        MathQuiz cl = new MathQuiz();

        // Function to set default operation of JFrame.
        cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Function to set visibility of JFrame.
        cl.setVisible(true);
    }
}
