import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class NewPanelTry extends JFrame{

    JPanel pBasePanel, pMainMenu, pAddQuestion, pEditQuestions, pStartQuiz, pHighScore, pQuizRound;
    JButton bAddQuestion, bEditQuestions, bStartQuiz, bHighScore, bBackAddQuestion, bBackEditQuestions, bBackStartQuiz, bBackHighScore, bBackQuizRound, bSaveAddQuestion, bSaveEditQuestions, bPrevious, bNext, bGo;
    JTextField tNumberOfQuestion, tUsername, tQuestion, tAnswer1, tAnswer2, tAnswer3, tAnswerCorrect, tHighScore;
    JLabel lNumberOfQuestions, lUsername;

    public NewPanelTry() throws IOException {

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
        bBackAddQuestion = new JButton("Back");
        bBackEditQuestions = new JButton("Back");
        bBackStartQuiz = new JButton("Back");
        bBackHighScore = new JButton("Back");
        bBackQuizRound = new JButton("Back");
        //bBack.setBounds(600, 600, 300, 30);
        bSaveAddQuestion = new JButton("Save");
        bSaveEditQuestions = new JButton("Save");
        bGo = new JButton("Go");
        bPrevious = new JButton("<-");
        bNext = new JButton("->");

        tNumberOfQuestion = new JTextField(1);//text fields
        lNumberOfQuestions = new JLabel("how many questions do you want?");
        lUsername = new JLabel("name: ");
        tNumberOfQuestion.add(lNumberOfQuestions);
        tUsername = new JTextField(8);
        tUsername.setToolTipText("<html><b><font color=clay>" + "enter your name" + "</font></b></html>");
        tQuestion = new JTextField("write your question here");
        tAnswer1 = new JTextField("add a wrong answer");
        tAnswer2 = new JTextField("add a wrong answer");
        tAnswer3 = new JTextField("add a wrong answer");
        tAnswerCorrect = new JTextField("add the correct answer");
        tHighScore = new JTextField();

        bAddQuestion.addActionListener(e -> showAddQuestionPanel());//action listeners added, and stickt to MainMenu panel
        bEditQuestions.addActionListener(e -> showEditQuestionsPanel());
        bStartQuiz.addActionListener(e -> showStartQuizPanel());
        bHighScore.addActionListener(e -> showHighScorePanel());
        bPrevious.addActionListener(e -> showPreviousQuestion());
        bNext.addActionListener(e -> showNextQuestion());
        bBackAddQuestion.addActionListener(e -> unshowPanels());//if user clicks back on any panel, he/she should land on the main menu
        bBackEditQuestions.addActionListener(e -> unshowPanels());
        bBackStartQuiz.addActionListener(e -> unshowPanels());
        bBackHighScore.addActionListener(e -> unshowPanels());
        bBackQuizRound.addActionListener(e -> unshowPanels());
        bSaveAddQuestion.addActionListener(e -> saveQuestion());//save the entered question and relevant answers in the question pool
        bGo.addActionListener(e -> startQuiz());

        pMainMenu.add(bAddQuestion);
        pMainMenu.add(bEditQuestions);
        pMainMenu.add(bStartQuiz);
        pMainMenu.add(bHighScore);
        pMainMenu.setLayout(new GridLayout(2,2));

        pAddQuestion.add(tQuestion);
        pAddQuestion.add(tAnswer1);
        pAddQuestion.add(tAnswer2);
        pAddQuestion.add(tAnswer3);
        pAddQuestion.add(tAnswerCorrect);
        pAddQuestion.add(bBackAddQuestion);
        pAddQuestion.add(bSaveAddQuestion);
        pAddQuestion.setLayout(new GridLayout(4,4));

        pEditQuestions.add(bBackEditQuestions);
        pEditQuestions.add(bSaveEditQuestions);
        pEditQuestions.setLayout(new GridLayout());

        pStartQuiz.add(bBackStartQuiz);
        pStartQuiz.add(bGo);
        pStartQuiz.add(lUsername);
        pStartQuiz.add(tUsername);
        pStartQuiz.add(lNumberOfQuestions);
        pStartQuiz.add(tNumberOfQuestion);
        pStartQuiz.setLayout(new FlowLayout());

        pHighScore.add(bBackHighScore);
        File fHighScore = new File("C:\\Users\\turgu\\IdeaProjects\\javaMathQuiz\\src\\highscore.txt");
        Scanner scanner = new Scanner(fHighScore);
        scanner.useDelimiter("\\Z");
        String content = scanner.next();
        tHighScore.setText(content);
        pHighScore.add(tHighScore);
        pHighScore.setLayout(new GridLayout());

        pQuizRound.add(bBackQuizRound);
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
        pStartQuiz.setVisible(false);
        pQuizRound.setVisible(false);
        pMainMenu.setVisible(true);
    }

    void startQuiz(){
        unshowPanels();
        pMainMenu.setVisible(false);
        String username = tUsername.getText();
        int numberOfQuestions = Integer.parseInt(tNumberOfQuestion.getText());
        JLabel lUsername = new JLabel(username);
        JLabel lNumberOfQuestions = new JLabel(String.valueOf(numberOfQuestions));
        pQuizRound.add(lUsername);
        pQuizRound.add(lNumberOfQuestions);
        pQuizRound.setVisible(true);

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
            try {
                new NewPanelTry().setVisible(true);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    });}
}


