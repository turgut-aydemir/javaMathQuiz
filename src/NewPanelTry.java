import java.awt.*;
import javax.swing.*;

public class NewPanelTry extends JFrame{

    JPanel pBasePanel, pMainMenu, pAddQuestion, pEditQuestions, pStartQuiz, pHighScore, pQuizRound;
    JButton bAddQuestion, bEditQuestions, bStartQuiz, bHighScore, bBack, bSave, bPrevious, bNext, bGo;
    JTextField tNumberOfQuestion, tUsername, tQuestion, tAnswer1, tAnswer2, tAnswer3, tAnswerCorrect, tHighScore;

    public NewPanelTry(){

        pBasePanel = new JPanel();//Base panel (every other panel will be displayed on this panel)
        setTitle("Math Quiz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 700));
        pBasePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        pBasePanel.setLayout(new GridLayout(2,2));

        pAddQuestion = new JPanel();//other panels
        pEditQuestions = new JPanel();
        pStartQuiz = new JPanel();
        pHighScore = new JPanel();
        pQuizRound = new JPanel();
        pMainMenu = new JPanel();//Main menu will show the 4 main buttons (layout can be grid)

        bAddQuestion = new JButton("Add Question");//buttons
        bEditQuestions = new JButton("Edit Questions");
        bStartQuiz = new JButton("Start Quiz");
        bHighScore = new JButton("High Score");
        bBack = new JButton("Back");
        //bBack.setBounds(200, 200, 300, 30);
        bSave = new JButton("Save");
        bGo = new JButton("Go");
        bPrevious = new JButton("<-");
        bNext = new JButton("->");

        tNumberOfQuestion = new JTextField("how many questions do you want?");//text fields
        tUsername = new JTextField("enter your name");
        tQuestion = new JTextField("write your question here");
        tAnswer1 = new JTextField("add a wrong answer");
        tAnswer2 = new JTextField("add a wrong answer");
        tAnswer3 = new JTextField("add a wrong answer");
        tAnswerCorrect = new JTextField("add the correct answer");
        tHighScore = new JTextField("HighScore");

        bAddQuestion.addActionListener(e -> showAddQuestionPanel());//action listeners added, and stickt to MainMenu panel
        bEditQuestions.addActionListener(e -> showEditQuestionsPanel());
        bStartQuiz.addActionListener(e -> showStartQuizPanel());
        bHighScore.addActionListener(e -> showHighScorePanel());
        bPrevious.addActionListener(e -> showPreviousQuestion());
        bNext.addActionListener(e -> showNextQuestion());
        bBack.addActionListener(e -> unshowPanels());//if user clicks back on any panel, he/she should land on the main menu
        bSave.addActionListener(e -> saveQuestion());//save the entered question and relevant answers in the question pool

        pMainMenu.add(bAddQuestion);
        pMainMenu.add(bEditQuestions);
        pMainMenu.add(bStartQuiz);
        pMainMenu.add(bHighScore);
        //pMainMenu.setLayout(new GridLayout(2,2));

        pAddQuestion.add(bBack);
        pAddQuestion.add(bSave);
        pAddQuestion.add(tQuestion);
        pAddQuestion.add(tAnswer1);
        pAddQuestion.add(tAnswer2);
        pAddQuestion.add(tAnswer3);
        pAddQuestion.add(tAnswerCorrect);
        pAddQuestion.setLayout(new GridLayout(4,2));

        pEditQuestions.add(bBack);
        pEditQuestions.add(bSave);
        //pEditQuestions.setLayout(new GridLayout(8,8));

        pStartQuiz.add(bBack);
        pStartQuiz.add(bGo);
        pStartQuiz.add(tUsername);
        pStartQuiz.add(tNumberOfQuestion);
        //pStartQuiz.setLayout(new GridLayout(8,8));

        pHighScore.add(bBack);
        pHighScore.add(tHighScore);
        //pHighScore.setLayout(new GridLayout(8,8));

        pQuizRound.add(bPrevious);
        pQuizRound.add(bNext);

        pAddQuestion.setVisible(false);
        pEditQuestions.setVisible(false);
        pStartQuiz.setVisible(false);
        pHighScore.setVisible(false);
        pQuizRound.setVisible(false);

        pBasePanel.add(pMainMenu);//here we add all panels "as invisible" to the basePanel
        pBasePanel.add(pAddQuestion);
        pBasePanel.add(pEditQuestions,BorderLayout.CENTER);
        pBasePanel.add(pStartQuiz,BorderLayout.CENTER);
        pBasePanel.add(pHighScore,BorderLayout.CENTER);
        pBasePanel.add(pQuizRound,BorderLayout.CENTER);
        getContentPane().add(pBasePanel);
        pack();

    }

    void showAddQuestionPanel(){
        pMainMenu.setVisible(false);
        //pAddQuestion.revalidate();
        pAddQuestion.setVisible(true);
        //getContentPane().add(pBasePanel);
        //pack();
        //new NewPanelTry().setVisible(true);//CHANGE THIS AND FIND A WAY TO REFRESH PANEL LIKE IN JS
    }
    void showEditQuestionsPanel(){
        pMainMenu.setVisible(false);
        pEditQuestions.setVisible(true);
    }
    void showStartQuizPanel(){
        pMainMenu.setVisible(false);
        pStartQuiz.setVisible(true);
    }
    void showHighScorePanel(){
        pMainMenu.setVisible(false);
        pHighScore.setVisible(true);
    }
    void showQuizRoundPanel(){
        pStartQuiz.setVisible(false);
        pQuizRound.setVisible(true);
    }

    void unshowPanels(){
        pHighScore.setVisible(false);
        pAddQuestion.setVisible(false);
        pEditQuestions.setVisible(false);
        pMainMenu.setVisible(true);
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


