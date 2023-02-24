import java.awt.*;
import javax.swing.*;

public class NewPanelTry extends JFrame{

    JPanel pBasePanel, pMainMenu, pAddQuestion, pEditQuestions, pStartQuiz, pHighScore, pQuizRound;
    JButton bAddQuestion, bEditQuestions, bStartQuiz, bHighScore, bBack, bSave, bPrevious, bNext;
    JTextField tNumberOfQuestion, tUsername, tQuestion, tAnswer1, tAnswer2, tAnswer3, tAnswerCorrect;

    public NewPanelTry(){

        pBasePanel = new JPanel();//Base panel (every other panel will be displayed on this panel)
        setTitle("Math Quiz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 700));
        pBasePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        pBasePanel.setLayout(new BorderLayout());

        pAddQuestion = new JPanel();//other panels
        pEditQuestions = new JPanel();
        pStartQuiz = new JPanel();
        pHighScore = new JPanel();
        pQuizRound = new JPanel();

        pMainMenu = new JPanel();//Main menu will show the 4 main buttons (layout can be grid)
        pMainMenu.setLayout(new GridLayout(2,2));

        bAddQuestion = new JButton("Add Question");//buttons
        bEditQuestions = new JButton("Edit Questions");
        bStartQuiz = new JButton("Start Quiz");
        bHighScore = new JButton("High Score");
        bBack = new JButton("Back");
        bSave = new JButton("Save");
        bPrevious = new JButton("<-");
        bPrevious.addActionListener(e -> showPreviousQuestion());
        bNext = new JButton("->");
        bNext.addActionListener(e -> showNextQuestion());

        tNumberOfQuestion = new JTextField("how many questions do you want?");//text fields
        tUsername = new JTextField("enter your name");
        tQuestion = new JTextField("write your question here");
        tAnswer1 = new JTextField("add a wrong answer");
        tAnswer2 = new JTextField("add a wrong answer");
        tAnswer3 = new JTextField("add a wrong answer");
        tAnswerCorrect = new JTextField("add the correct answer");

        bAddQuestion.addActionListener(e -> showAddQuestionPanel());//action listeners added, and stickt to MainMenu panel
        pMainMenu.add(bAddQuestion);
        bEditQuestions.addActionListener(e -> showEditQuestionsPanel());
        pMainMenu.add(bEditQuestions);
        bStartQuiz.addActionListener(e -> showStartQuizPanel());
        pMainMenu.add(bStartQuiz);
        bHighScore.addActionListener(e -> showHighScorePanel());
        pMainMenu.add(bHighScore);

        bBack.addActionListener(e -> unshowPanels());//if user clicks back on any panel, he/she should land on the main menu
        bSave.addActionListener(e -> saveQuestion());//save the entered question and relevant answers in the question pool
        pAddQuestion.add(bBack, BOTTOM_ALIGNMENT);
        pAddQuestion.add(bSave, BOTTOM_ALIGNMENT);
        pAddQuestion.setVisible(false);

        pBasePanel.add(pMainMenu, BorderLayout.WEST);
        pBasePanel.add(pAddQuestion,BorderLayout.CENTER);
        getContentPane().add(pBasePanel);
        pack();

        //f.add(pLeft);

    }

    void showAddQuestionPanel(){
        pBasePanel.setVisible(false);
        pAddQuestion.revalidate();
        pAddQuestion.setVisible(true);
        getContentPane().add(pBasePanel);
        pack();
        new NewPanelTry().setVisible(true);//CHANGE THIS AND FIND A WAY TO REFRESH PANEL LIKE IN JS
    }
    void showEditQuestionsPanel(){
        pBasePanel.setVisible(false);
        pEditQuestions.setVisible(true);
    }
    void showStartQuizPanel(){
        pBasePanel.setVisible(false);
        pStartQuiz.setVisible(true);
    }
    void showHighScorePanel(){
        pBasePanel.setVisible(false);
        pHighScore.setVisible(true);
    }
    void showQuizRoundPanel(){
        pBasePanel.setVisible(false);
        pQuizRound.setVisible(true);
    }

    void unshowPanels(){
        pHighScore.setVisible(false);
        pAddQuestion.setVisible(false);
        pEditQuestions.setVisible(false);
        pBasePanel.setVisible(true);
    }

    void saveQuestion(){
        //TODO save the entered question in the Question Pool
    }

    void showPreviousQuestion(){
        //TODO go back to the previous question
    }

    void showNextQuestion(){
        //TODO go to the next question
    }

    void showResult(){
        //TODO result screen will show how many questions are correct answered among total number of questions
    }

    void showHighScore(){
        //TODO high score will show the leaderboard (username & percentages of old users)
    }







public static void main(String args[]){
    EventQueue.invokeLater(new Runnable(){
        @Override
        public void run(){
                new NewPanelTry().setVisible(true);
            }
    });}
}


