import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class NewPanelTry extends JFrame{

    JPanel pBasePanel, pMainMenu, pAddQuestion, pEditQuestions, pStartQuiz, pHighScore, pQuizRound;
    JButton bAddQuestion, bEditQuestions, bStartQuiz, bHighScore, bBackAddQuestion, bBackEditQuestions, bBackStartQuiz, bBackHighScore, bBackQuizRound, bSaveAddQuestion, bSaveEditQuestions, bPreviousQuizRound, bNextQuizRound, bGoStartQuiz;
    JTextField tNumberOfQuestionsStartQuiz, tUsernameStartQuiz, tQuestionAddQuestion, tAnswer1AddQuestion, tAnswer2AddQuestion, tAnswer3AddQuestion, tAnswerCorrectAddQuestion, tHighScore;
    JLabel lNumberOfQuestionsStartQuiz, lUsernameStartQuiz, lQuestionAddQuestion, lAnswer1AddQuestion, lAnswer2AddQuestion, lAnswer3AddQuestion, lAnswerCorrectAddQuestion, lHighScore;

    public NewPanelTry() throws IOException {

        pBasePanel = new JPanel();//Base panel (every other panel will be displayed on this panel)
        setTitle("Math Quiz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 700));
        pBasePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        pBasePanel.setLayout(new GridLayout());

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
        bSaveAddQuestion = new JButton("Save");
        bSaveEditQuestions = new JButton("Save");
        bGoStartQuiz = new JButton("Go");
        bPreviousQuizRound = new JButton("<-");
        bNextQuizRound = new JButton("->");

        tNumberOfQuestionsStartQuiz = new JTextField(1);//text fields
        lNumberOfQuestionsStartQuiz = new JLabel("how many questions do you want?");
        tNumberOfQuestionsStartQuiz.add(lNumberOfQuestionsStartQuiz);
        tUsernameStartQuiz = new JTextField(8);
        lUsernameStartQuiz = new JLabel("enter your name: ");
        tUsernameStartQuiz.add(lUsernameStartQuiz);
        tQuestionAddQuestion = new JTextField(128);
        lQuestionAddQuestion = new JLabel("write your question");
        tQuestionAddQuestion.add(lQuestionAddQuestion);
        tAnswer1AddQuestion = new JTextField(32);
        lAnswer1AddQuestion = new JLabel("add a wrong answer");
        tAnswer1AddQuestion.add(lAnswer1AddQuestion);
        tAnswer2AddQuestion = new JTextField(32);
        lAnswer2AddQuestion = new JLabel("add a wrong answer");
        tAnswer2AddQuestion.add(lAnswer2AddQuestion);
        tAnswer3AddQuestion = new JTextField(32);
        lAnswer3AddQuestion = new JLabel("add a wrong answer");
        tAnswer3AddQuestion.add(lAnswer3AddQuestion);
        tAnswerCorrectAddQuestion = new JTextField(32);
        lAnswerCorrectAddQuestion = new JLabel("add the correct answer");
        tAnswerCorrectAddQuestion.add(lAnswerCorrectAddQuestion);
        tHighScore = new JTextField();
        lHighScore = new JLabel("here will be shown highscore");

        bAddQuestion.addActionListener(e -> showAddQuestionPanel());//action listeners added, and stickt to MainMenu panel
        bEditQuestions.addActionListener(e -> showEditQuestionsPanel());
        bStartQuiz.addActionListener(e -> showStartQuizPanel());
        bHighScore.addActionListener(e -> showHighScorePanel());
        bPreviousQuizRound.addActionListener(e -> showPreviousQuestion());
        bNextQuizRound.addActionListener(e -> showNextQuestion());
        bBackAddQuestion.addActionListener(e -> unshowPanels());//if user clicks back on any panel, he/she should land on the main menu
        bBackEditQuestions.addActionListener(e -> unshowPanels());
        bBackStartQuiz.addActionListener(e -> unshowPanels());
        bBackHighScore.addActionListener(e -> unshowPanels());
        bBackQuizRound.addActionListener(e -> unshowPanels());
        bSaveAddQuestion.addActionListener(e -> saveQuestion());//save the entered question and relevant answers in the question pool
        bGoStartQuiz.addActionListener(e -> startQuiz());

        pMainMenu.add(bAddQuestion);
        pMainMenu.add(bEditQuestions);
        pMainMenu.add(bStartQuiz);
        pMainMenu.add(bHighScore);
        pMainMenu.setLayout(new GridLayout(2,2));

        pAddQuestion.add(tQuestionAddQuestion);
        pAddQuestion.add(tAnswer1AddQuestion);
        pAddQuestion.add(tAnswer2AddQuestion);
        pAddQuestion.add(tAnswer3AddQuestion);
        pAddQuestion.add(tAnswerCorrectAddQuestion);
        pAddQuestion.add(bBackAddQuestion);
        pAddQuestion.add(bSaveAddQuestion);
        pAddQuestion.setLayout(new BoxLayout(pAddQuestion, BoxLayout.PAGE_AXIS));

        pEditQuestions.add(bBackEditQuestions);
        pEditQuestions.add(bSaveEditQuestions);
        pEditQuestions.setLayout(new GridLayout());

        pStartQuiz.add(tUsernameStartQuiz);
        pStartQuiz.add(tNumberOfQuestionsStartQuiz);
        pStartQuiz.add(bBackStartQuiz);
        pStartQuiz.add(bGoStartQuiz);
        pStartQuiz.setLayout(new BoxLayout(pStartQuiz, BoxLayout.PAGE_AXIS));

        File fHighScore = new File("C:\\Users\\turgu\\IdeaProjects\\javaMathQuiz\\src\\highscore.txt");
        Scanner scanner = new Scanner(fHighScore);
        scanner.useDelimiter("\\Z");
        String content = scanner.next();
        tHighScore.setText(content);
        pHighScore.add(tHighScore);
        pHighScore.add(bBackHighScore);
        pHighScore.setLayout(new BoxLayout(pHighScore, BoxLayout.PAGE_AXIS));

        pQuizRound.add(bBackQuizRound);
        pQuizRound.add(bPreviousQuizRound);
        pQuizRound.add(bNextQuizRound);
        pQuizRound.setLayout(new BoxLayout(pQuizRound, BoxLayout.PAGE_AXIS));

        pBasePanel.add(pMainMenu);//here we add all panels "as invisible" to the basePanel
        getContentPane().add(pBasePanel);
        pack();

    }

    void showAddQuestionPanel(){
        pBasePanel.remove(pMainMenu);
        pBasePanel.add(pAddQuestion);
        pBasePanel.revalidate();
        pBasePanel.repaint();

    }
    void showEditQuestionsPanel(){
        pBasePanel.remove(pMainMenu);
        pBasePanel.add(pEditQuestions);
        pBasePanel.revalidate();
        pBasePanel.repaint();
    }
    void showStartQuizPanel(){
        pBasePanel.remove(pMainMenu);
        pBasePanel.add(pStartQuiz);
        pBasePanel.revalidate();
        pBasePanel.repaint();
    }
    void showHighScorePanel(){
        pBasePanel.remove(pMainMenu);
        pBasePanel.add(pHighScore);
        pBasePanel.revalidate();
        pBasePanel.repaint();
    }
    void showQuizRoundPanel(){
        pBasePanel.remove(pStartQuiz);
        pBasePanel.add(pQuizRound);
        pBasePanel.revalidate();
        pBasePanel.repaint();
    }

    void unshowPanels(){
        pBasePanel.removeAll();
        pBasePanel.add(pMainMenu);
        pBasePanel.revalidate();
        pBasePanel.repaint();
    }

    void startQuiz(){ //this will change removeAll() and add new components
        unshowPanels();
        pMainMenu.setVisible(false);
        String username = tUsernameStartQuiz.getText();
        int numberOfQuestions = Integer.parseInt(tNumberOfQuestionsStartQuiz.getText());
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


