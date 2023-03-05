import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.*;

import static java.nio.file.StandardOpenOption.APPEND;

public class NewPanelTry extends JFrame{

    JPanel pBasePanel, pMainMenu, pAddQuestion, pEditQuestions, pStartQuiz, pHighScore, pQuizRound;
    JButton bAddQuestion, bEditQuestions, bStartQuiz, bHighScore, bBackAddQuestion, bBackEditQuestions, bBackStartQuiz, bBackHighScore, bBackQuizRound, bAddAddQuestion, bSaveEditQuestions, bNextQuizRound, bGoStartQuiz, bAgainQuizRound;
    JTextField tNumberOfQuestionsStartQuiz, tUsernameStartQuiz, tQuestionAddQuestion, tAnswer1AddQuestion, tAnswer2AddQuestion, tAnswer3AddQuestion, tAnswerCorrectAddQuestion, tCheckSelectedAnswer;
    JLabel lNumberOfQuestionsStartQuiz, lUsernameStartQuiz, lQuestionAddQuestion, lAnswer1AddQuestion, lAnswer2AddQuestion, lAnswer3AddQuestion, lAnswerCorrectAddQuestion, lHighScore, lQuestionQuizRound;
    JTextArea taHighScore, taCheckSelectedAnswer;
    JRadioButton rbAnswer1, rbAnswer2, rbAnswer3, rbCorrectAnswer;
    List<String> reserveQuestionsList;
    ButtonGroup rbGroup;
    int questionCounter = 1, correctAnswerCounter = 0, numberOfQuestions;
    String pathQuestions, pathHighScore, checkSelectedAnswer, selectedAnswer, correctAnswer, username, result;
    double percentage;

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
        pMainMenu = new JPanel();//Main menu will show the 4 main buttons (layout grid)

        bAddQuestion = new JButton("Add Question");//buttons
        bEditQuestions = new JButton("Edit Questions");
        bStartQuiz = new JButton("Start Quiz");
        bHighScore = new JButton("High Score");
        bBackAddQuestion = new JButton("Back");//back button unshows all panels and shows main panel (back to main menu)
        bBackEditQuestions = new JButton("Back");
        bBackStartQuiz = new JButton("Back");
        bBackHighScore = new JButton("Back");
        bBackQuizRound = new JButton("Back to Main Menu");
        bAddAddQuestion = new JButton("Add");//saves the question into the questions.txt file as a new line
        bSaveEditQuestions = new JButton("Save");
        bGoStartQuiz = new JButton("Go");//number of questions & username is obtained here, used for highscore and results.
        bNextQuizRound = new JButton("Next Question ->");
        bAgainQuizRound = new JButton("Again");

        lQuestionQuizRound = new JLabel();
        rbAnswer1 = new JRadioButton();
        rbAnswer2 = new JRadioButton();
        rbAnswer3 = new JRadioButton();
        rbCorrectAnswer = new JRadioButton();
        rbGroup = new ButtonGroup();
        rbGroup.add(rbAnswer1);
        rbGroup.add(rbAnswer2);
        rbGroup.add(rbAnswer3);
        rbGroup.add(rbCorrectAnswer);
        rbAnswer1.addActionListener(e -> checkSelectedAnswer());
        rbAnswer2.addActionListener(e -> checkSelectedAnswer());
        rbAnswer3.addActionListener(e -> checkSelectedAnswer());
        rbCorrectAnswer.addActionListener(e -> checkSelectedAnswer());

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
        tCheckSelectedAnswer = new JTextField();
        tCheckSelectedAnswer.setEditable(false);
        taCheckSelectedAnswer = new JTextArea();

        bAddQuestion.addActionListener(e -> showAddQuestionPanel());//action listeners for buttons
        bEditQuestions.addActionListener(e -> showEditQuestionsPanel());
        bStartQuiz.addActionListener(e -> showStartQuizPanel());
        bHighScore.addActionListener(e -> {
            try {
                showHighScorePanel();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        bBackAddQuestion.addActionListener(e -> unshowPanels());//if user clicks back on any panel, he/she should land on the main menu
        bBackEditQuestions.addActionListener(e -> unshowPanels());
        bBackStartQuiz.addActionListener(e -> unshowPanels());
        bBackHighScore.addActionListener(e -> unshowPanels());
        bBackQuizRound.addActionListener(e -> {
            rbGroup.clearSelection();
            unshowPanels();});
        bAddAddQuestion.addActionListener(e -> saveQuestion());//save the entered question and relevant answers in the question pool
        bGoStartQuiz.addActionListener(e -> {
            username = tUsernameStartQuiz.getText();
            numberOfQuestions = Integer.parseInt(tNumberOfQuestionsStartQuiz.getText());
            try {
                reserveQuestionsList = getQuestions(numberOfQuestions);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                showQuestions(questionCounter);

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
        bNextQuizRound.addActionListener(e -> {
            try {
                rbGroup.clearSelection();
                showQuestions(questionCounter);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        bAgainQuizRound.addActionListener(e -> saveResults());

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
        pQuizRound.add(tCheckSelectedAnswer);
        pQuizRound.add(rbAnswer1);
        pQuizRound.add(rbAnswer2);
        pQuizRound.add(rbAnswer3);
        pQuizRound.add(rbCorrectAnswer);
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
    void showHighScorePanel() throws IOException {
        sortHighScore();
        pBasePanel.remove(pMainMenu);
        pBasePanel.add(pHighScore);
        pBasePanel.revalidate();
        pBasePanel.repaint();
    }
    void sortHighScore() throws IOException {
        File fHighScore = new File(pathHighScore);
        FileInputStream fis = new FileInputStream(fHighScore);
        byte[] data = new byte[(int) fHighScore.length()];
        fis.read(data);
        fis.close();
        String str = new String(data, "UTF-8");
        taHighScore.setText(str);
        }
    void showQuizRoundPanel() throws IOException {
        pBasePanel.removeAll();
        pBasePanel.add(pQuizRound);
        pBasePanel.revalidate();
        pBasePanel.repaint();
    }
    void unshowPanels(){
        pBasePanel.removeAll();
        bNextQuizRound.setVisible(true); //because in checkSelectedAnswer it is set invisible after last quetion is answered
        bBackQuizRound.setText("Back to Main Menu");
        questionCounter = 1;
        correctAnswerCounter = 0;
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
    int showQuestions(int questionCounterIn) throws IOException {
        if(questionCounter<=reserveQuestionsList.size()){
            removeCheckSelectedAnswerAddRadioButtons();
            String question1 = reserveQuestionsList.get(questionCounter-1);
            String[] tokens = question1.split(",");
            lQuestionQuizRound.setText("Question " + questionCounter+ "/" + numberOfQuestions + ". " + tokens[0]);
            List<Integer> listAnswer =Arrays.asList(1, 2, 3, 4);
            Collections.shuffle(listAnswer);
            rbAnswer1.setText(tokens[listAnswer.get(0)]);
            rbAnswer1.setActionCommand(tokens[listAnswer.get(0)]);
            rbAnswer2.setText(tokens[listAnswer.get(1)]);
            rbAnswer2.setActionCommand(tokens[listAnswer.get(1)]);
            rbAnswer3.setText(tokens[listAnswer.get(2)]);
            rbAnswer3.setActionCommand(tokens[listAnswer.get(2)]);
            rbCorrectAnswer.setText(tokens[listAnswer.get(3)]);
            rbCorrectAnswer.setActionCommand(tokens[listAnswer.get(3)]);
            questionCounter++;
        }
        return questionCounter;
    }
        void checkSelectedAnswer () {
            removeRadioButtonsAddCheckSelectedAnswer();
            selectedAnswer = rbGroup.getSelection().getActionCommand();
            System.out.println(selectedAnswer);
            for (String element : reserveQuestionsList){
                if (element.contains(reserveQuestionsList.get(questionCounter-2))) {
                    String question2 = element;
                    String[] tokens = question2.split(",");
                    correctAnswer =  tokens[4];
                }
            }
            if (Objects.equals(selectedAnswer, correctAnswer)){
                correctAnswerCounter++;
                checkSelectedAnswer = "Correct. " + selectedAnswer;
            }
            else {
                checkSelectedAnswer = "Wrong. Correct answer: " + correctAnswer;
            }

            tCheckSelectedAnswer.setText(checkSelectedAnswer);
            System.out.println(questionCounter);
            System.out.println(numberOfQuestions);
            if(numberOfQuestions + 1 == questionCounter){
                bNextQuizRound.setVisible(false);
                bBackQuizRound.setText("Play Again");
                lQuestionQuizRound.setText("Results");
                saveResults();
                showResults();
            }
        }

    void showResults() {
        percentage = (double)correctAnswerCounter * 100 / (numberOfQuestions);
        result = username + ": " + correctAnswerCounter + " correct in total of " + numberOfQuestions + " (" + percentage + "%)";
        tCheckSelectedAnswer.setText(result);
        String saveResult = username + ","+ percentage + "%\n";
        Path path = Paths.get(pathHighScore);
        byte[] arr = saveResult.getBytes();
        try {
            Files.write(path, arr, APPEND);
        }
        catch (IOException ex) {
            System.out.print("Invalid Path");
        }
        System.out.println(result);
    }

    void saveResults() {

    }

    void removeRadioButtonsAddCheckSelectedAnswer(){
            rbAnswer1.setVisible(false);
            rbAnswer2.setVisible(false);
            rbAnswer3.setVisible(false);
            rbCorrectAnswer.setVisible(false);
            tCheckSelectedAnswer.setVisible(true);
        }
        void removeCheckSelectedAnswerAddRadioButtons(){
            tCheckSelectedAnswer.setVisible(false);
            rbAnswer1.setVisible(true);
            rbAnswer2.setVisible(true);
            rbAnswer3.setVisible(true);
            rbCorrectAnswer.setVisible(true);
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


