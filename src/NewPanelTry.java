import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.*;

import static java.nio.file.StandardOpenOption.APPEND;

public class NewPanelTry extends JFrame{

    JPanel pBasePanel, pMainMenu, pAddQuestion, pEditQuestions, pStartQuiz, pHighScore, pQuizRound;
    JButton bAddQuestion, bEditQuestions, bStartQuiz, bHighScore, bBackAddQuestion, bBackEditQuestions, bBackStartQuiz, bBackHighScore, bBackQuizRound, bAddAddQuestion, bSaveEditQuestions, bPreviousQuizRound, bNextQuizRound, bGoStartQuiz, bAgainQuizRound;
    JTextField tNumberOfQuestionsStartQuiz, tUsernameStartQuiz, tQuestionAddQuestion, tAnswer1AddQuestion, tAnswer2AddQuestion, tAnswer3AddQuestion, tAnswerCorrectAddQuestion, tHighScore, tQuizRound, tQuestionQuizRound;
    JLabel lNumberOfQuestionsStartQuiz, lUsernameStartQuiz, lQuestionAddQuestion, lAnswer1AddQuestion, lAnswer2AddQuestion, lAnswer3AddQuestion, lAnswerCorrectAddQuestion, lHighScore, lQuizRound, lQuestionQuizRound, lAnswer1QuizRound, lAnswer2QuizRound, lAnswer3QuizRound, lCorrectAnswerQuizRound;
    JTextArea taHighScore, taQuizRound, taQuestionQuizRound, taAnser1QuizRound;
    JRadioButton rbAnswer1, rbAnswer2, rbAnswer3, rbCorrectAnswer;
    List<String> reserveQuestionsList;
    int counterQuestionQuizRound;
    String pathQuestions, pathHighScore;

    public NewPanelTry() throws IOException {

        pathHighScore = "C:\\Users\\turgu\\IdeaProjects\\javaMathQuiz\\src\\highscore.txt";
        pathQuestions = "C:\\Users\\turgu\\IdeaProjects\\javaMathQuiz\\src\\questions.txt";

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
        bBackAddQuestion = new JButton("Back");//back button unshows all panels and shows main panel (back to main menu)
        bBackEditQuestions = new JButton("Back");
        bBackStartQuiz = new JButton("Back");
        bBackHighScore = new JButton("Back");
        bBackQuizRound = new JButton("Back");
        bAddAddQuestion = new JButton("Add");//saves the question into the questions.txt file as a new line
        bSaveEditQuestions = new JButton("Save");
        bGoStartQuiz = new JButton("Go");//number of questions & username is obtained here, don't forget to use them after quiz round for highscore and results
        bPreviousQuizRound = new JButton("<-");
        bNextQuizRound = new JButton("->");
        bAgainQuizRound = new JButton("Again");//different than back, again goes to startQuiz panel

        lQuestionQuizRound = new JLabel();
        rbAnswer1 = new JRadioButton();
        rbAnswer2 = new JRadioButton();
        rbAnswer3 = new JRadioButton();
        rbCorrectAnswer = new JRadioButton();
        ButtonGroup rbGroup = new ButtonGroup();
        rbGroup.add(rbAnswer1);
        rbGroup.add(rbAnswer2);
        rbGroup.add(rbAnswer3);
        rbGroup.add(rbCorrectAnswer);

        tNumberOfQuestionsStartQuiz = new JTextField(1);//text fields and related labels
        lNumberOfQuestionsStartQuiz = new JLabel("how many questions do you want?");
        tUsernameStartQuiz = new JTextField(8);
        lUsernameStartQuiz = new JLabel("enter your name: ");
        tQuestionAddQuestion = new JTextField(128);
        lQuestionAddQuestion = new JLabel("write your question");
        tAnswer1AddQuestion = new JTextField(32);
        lAnswer1AddQuestion = new JLabel("add a wrong answer");
        tAnswer2AddQuestion = new JTextField(32);
        lAnswer2AddQuestion = new JLabel("add a wrong answer");
        tAnswer3AddQuestion = new JTextField(32);
        lAnswer3AddQuestion = new JLabel("add a wrong answer");
        tAnswerCorrectAddQuestion = new JTextField(32);
        lAnswerCorrectAddQuestion = new JLabel("add the correct answer");
        taHighScore = new JTextArea();
        lHighScore = new JLabel("High Scores");

        bAddQuestion.addActionListener(e -> showAddQuestionPanel());//action listeners for buttons
        bEditQuestions.addActionListener(e -> showEditQuestionsPanel());
        bStartQuiz.addActionListener(e -> showStartQuizPanel());
        bHighScore.addActionListener(e -> {
            try {
                showHighScorePanel();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        bBackAddQuestion.addActionListener(e -> unshowPanels());//if user clicks back on any panel, he/she should land on the main menu
        bBackEditQuestions.addActionListener(e -> unshowPanels());
        bBackStartQuiz.addActionListener(e -> unshowPanels());
        bBackHighScore.addActionListener(e -> unshowPanels());
        bBackQuizRound.addActionListener(e -> unshowPanels());
        bAddAddQuestion.addActionListener(e -> saveQuestion());//save the entered question and relevant answers in the question pool
        bGoStartQuiz.addActionListener(e -> {
            int numberOfQuestions = Integer.parseInt(tNumberOfQuestionsStartQuiz.getText());
            try {
                reserveQuestionsList = getQuestions(numberOfQuestions);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                counterQuestionQuizRound = showFirstQuestion();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                showQuizRoundPanel();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        bPreviousQuizRound.addActionListener(e -> showPreviousQuestion());
        bNextQuizRound.addActionListener(e -> counterQuestionQuizRound = showNextQuestion(counterQuestionQuizRound));//we take it and use it again, BUT at the last question we should stop using it. IF clauses
        bAgainQuizRound.addActionListener(e -> saveResult());//if user doesn't click on that, results would not be saved!

        pMainMenu.add(bAddQuestion);
        pMainMenu.add(bEditQuestions);
        pMainMenu.add(bStartQuiz);
        pMainMenu.add(bHighScore);
        pMainMenu.setLayout(new GridLayout(2,2));

        pAddQuestion.add(lQuestionAddQuestion);
        pAddQuestion.add(tQuestionAddQuestion);
        pAddQuestion.add(lAnswer1AddQuestion);
        pAddQuestion.add(tAnswer1AddQuestion);
        pAddQuestion.add(lAnswer2AddQuestion);
        pAddQuestion.add(tAnswer2AddQuestion);
        pAddQuestion.add(lAnswer3AddQuestion);
        pAddQuestion.add(tAnswer3AddQuestion);
        pAddQuestion.add(lAnswerCorrectAddQuestion);
        pAddQuestion.add(tAnswerCorrectAddQuestion);
        pAddQuestion.add(bBackAddQuestion);
        pAddQuestion.add(bAddAddQuestion);
        pAddQuestion.setLayout(new BoxLayout(pAddQuestion, BoxLayout.PAGE_AXIS));

        pEditQuestions.add(bBackEditQuestions);
        pEditQuestions.add(bSaveEditQuestions);
        pEditQuestions.setLayout(new GridLayout());

        pStartQuiz.add(lUsernameStartQuiz);
        pStartQuiz.add(tUsernameStartQuiz);
        pStartQuiz.add(lNumberOfQuestionsStartQuiz);
        pStartQuiz.add(tNumberOfQuestionsStartQuiz);
        pStartQuiz.add(bBackStartQuiz);
        pStartQuiz.add(bGoStartQuiz);
        pStartQuiz.setLayout(new FlowLayout());

        pHighScore.add(lHighScore);
        pHighScore.add(taHighScore);
        pHighScore.add(bBackHighScore);
        pHighScore.setLayout(new BoxLayout(pHighScore, BoxLayout.PAGE_AXIS));

        pQuizRound.add(lQuestionQuizRound);
        pQuizRound.add(rbAnswer1);
        pQuizRound.add(rbAnswer2);
        pQuizRound.add(rbAnswer3);
        pQuizRound.add(rbCorrectAnswer);
        pQuizRound.add(bPreviousQuizRound);
        pQuizRound.add(bNextQuizRound);
        pQuizRound.add(bBackQuizRound);
        pQuizRound.setLayout(new GridLayout(0, 1, 5, 5));

        pBasePanel.add(pMainMenu);//basePanel will 1st show the mainMenu
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
    void showHighScorePanel() throws FileNotFoundException {
        sortHighScore();
        pBasePanel.remove(pMainMenu);
        pBasePanel.add(pHighScore);
        pBasePanel.revalidate();
        pBasePanel.repaint();
    }
    void sortHighScore() throws FileNotFoundException {
        List<String> highScoreList;
        try (Stream<String> lines = Files.lines(Paths.get(pathHighScore))) {
            highScoreList = lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        taHighScore.setText(highScoreList.toString());
        }
    void showQuizRoundPanel() throws IOException {
        pBasePanel.removeAll();
        pBasePanel.add(pQuizRound);
        pBasePanel.revalidate();
        pBasePanel.repaint();
    }
    void unshowPanels(){
        pBasePanel.removeAll();
        pBasePanel.add(pMainMenu);
        clearAddQuestionTextFields();
        clearStartQuizTextFields();
        pBasePanel.revalidate();
        pBasePanel.repaint();
    }
    void clearAddQuestionTextFields(){ //to clean the text fields in AddQuestion panel after adding a question.
        tQuestionAddQuestion.setText("");
        tAnswer1AddQuestion.setText("");
        tAnswer2AddQuestion.setText("");
        tAnswer3AddQuestion.setText("");
        tAnswerCorrectAddQuestion.setText("");
    }
    void clearStartQuizTextFields(){ //to clean the text fields in AddQuestion panel after adding a question.
        tUsernameStartQuiz.setText("");
        tNumberOfQuestionsStartQuiz.setText("");
    }
    void saveQuestion(){
        //TODO save the entered question in the Question Pool
        String question = tQuestionAddQuestion.getText();
        String answer1 = tAnswer1AddQuestion.getText();
        String answer2 = tAnswer2AddQuestion.getText();
        String answer3 = tAnswer3AddQuestion.getText();
        String correctAnswer = tAnswerCorrectAddQuestion.getText();
        String addQuestion = question + "," + answer1 + "," + answer2 + "," + answer3 + "," + correctAnswer + "\n";
        Path path = Paths.get(pathQuestions);
        byte[] arr = addQuestion.getBytes();
        try {
            Files.write(path, arr, APPEND);
        }
        catch (IOException ex) {
            System.out.print("Invalid Path");
        }
        unshowPanels();
    }
    List<String> getQuestions(int numberOFQuestions) throws IOException {
        List<String> questionList;
        try (Stream<String> lines = Files.lines(Paths.get(pathQuestions))) {
            questionList = lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> reducedQuestionList = new ArrayList<>();
        for (int i = 0; i < numberOFQuestions; i++) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(questionList.size());
            reducedQuestionList.add(questionList.get(randomIndex));
            questionList.remove(randomIndex);
        }
        return reducedQuestionList;
        }
        void showQuestions(int questionCounter) throws IOException {
            int numberOfQuestions = Integer.parseInt(tNumberOfQuestionsStartQuiz.getText());
            List<String> reserveQuestionsList = getQuestions(numberOfQuestions);
            String question1 = reserveQuestionsList.get(questionCounter-1);
            String[] tokens = question1.split(",");
            lQuestionQuizRound.setText("Question " + questionCounter+ ". " + tokens[0]);
            rbAnswer1.setText(tokens[1]);
            rbAnswer2.setText(tokens[2]);
            rbAnswer3.setText(tokens[3]);
            rbCorrectAnswer.setText(tokens[4]);
        }
        int showFirstQuestion () throws IOException {
            int questionCounter = 1;
            String question1 = reserveQuestionsList.get(questionCounter-1);
            String[] tokens = question1.split(",");
            lQuestionQuizRound.setText("Question " + questionCounter+ ". " + tokens[0]);
            rbAnswer1.setText(tokens[1]);
            rbAnswer2.setText(tokens[2]);
            rbAnswer3.setText(tokens[3]);
            rbCorrectAnswer.setText(tokens[4]);
            questionCounter++;
            return questionCounter;
            }
        void showPreviousQuestion () {
            //TODO go back to the previous question
        }
        int showNextQuestion (int counterQuestionQuizRound) {
            //TODO go to the next question
            String question1 = reserveQuestionsList.get(counterQuestionQuizRound-1);
            String[] tokens = question1.split(",");
            lQuestionQuizRound.setText("Question " + counterQuestionQuizRound+ ". " + tokens[0]);
            rbAnswer1.setText(tokens[1]);
            rbAnswer2.setText(tokens[2]);
            rbAnswer3.setText(tokens[3]);
            rbCorrectAnswer.setText(tokens[4]);
            counterQuestionQuizRound++;
            return counterQuestionQuizRound;
        }
        void showResult () {
            //TODO result screen will show how many questions are correct answered among total number of questions
        }
        void saveResult () {
            //TODO save the results obtained from the QuizRound with username on the result.txt file and dont forget highscores
            String username = tUsernameStartQuiz.getText();//this will be used in saveResult()
            int numberOfQuestions = Integer.parseInt(tNumberOfQuestionsStartQuiz.getText());
        }
        void showHighScore () {
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


