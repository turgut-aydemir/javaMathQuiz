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

    public NewPanelTry() throws IOException {

        pBasePanel = new JPanel();//Base panel (every other panel will be displayed on this panel)
        setTitle("Math Quiz");
        //pBasePanel.setBackground(Color.WHITE);
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


        //tQuestionQuizRound = new JTextField();
        lQuestionQuizRound = new JLabel();
        //tQuestionQuizRound.add(lQuestionQuizRound);
        //lAnswer1QuizRound = new JLabel();
        //lAnswer2QuizRound = new JLabel();
        //lAnswer3QuizRound = new JLabel();
        //lCorrectAnswerQuizRound = new JLabel();
        rbAnswer1 = new JRadioButton();
        //rbAnswer1.add(lAnswer1QuizRound);
        rbAnswer2 = new JRadioButton();
        //rbAnswer1.add(lAnswer2QuizRound);
        rbAnswer3 = new JRadioButton();
        //rbAnswer1.add(lAnswer3QuizRound);
        rbCorrectAnswer = new JRadioButton();
        //rbAnswer1.add(lCorrectAnswerQuizRound);
        ButtonGroup rbGroup = new ButtonGroup();
        rbGroup.add(rbAnswer1);
        rbGroup.add(rbAnswer2);
        rbGroup.add(rbAnswer3);
        rbGroup.add(rbCorrectAnswer);
        //taQuestionQuizRound = new JTextArea();
        //taQuestionQuizRound.setEditable(false);
        //taAnser1QuizRound = new JTextArea();
        //taAnser1QuizRound.setEditable(false);

        tNumberOfQuestionsStartQuiz = new JTextField(1);//text fields and related labels
        lNumberOfQuestionsStartQuiz = new JLabel("how many questions do you want?");
        //tNumberOfQuestionsStartQuiz.add(lNumberOfQuestionsStartQuiz);
        tUsernameStartQuiz = new JTextField(8);
        lUsernameStartQuiz = new JLabel("enter your name: ");
        //tUsernameStartQuiz.add(lUsernameStartQuiz);
        tQuestionAddQuestion = new JTextField(128);
        lQuestionAddQuestion = new JLabel("write your question");
        //tQuestionAddQuestion.add(lQuestionAddQuestion);
        tAnswer1AddQuestion = new JTextField(32);
        lAnswer1AddQuestion = new JLabel("add a wrong answer");
        //tAnswer1AddQuestion.add(lAnswer1AddQuestion);
        tAnswer2AddQuestion = new JTextField(32);
        lAnswer2AddQuestion = new JLabel("add a wrong answer");
        //tAnswer2AddQuestion.add(lAnswer2AddQuestion);
        tAnswer3AddQuestion = new JTextField(32);
        lAnswer3AddQuestion = new JLabel("add a wrong answer");
        //tAnswer3AddQuestion.add(lAnswer3AddQuestion);
        tAnswerCorrectAddQuestion = new JTextField(32);
        lAnswerCorrectAddQuestion = new JLabel("add the correct answer");
        //tAnswerCorrectAddQuestion.add(lAnswerCorrectAddQuestion);
        taHighScore = new JTextArea();
        lHighScore = new JLabel("High Scores");
        //taHighScore.add(lHighScore);
        //tQuizRound = new JTextField();
        //lQuizRound = new JLabel("Question" + questionCounter);

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
                showFirstQuestion();
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
        bNextQuizRound.addActionListener(e -> showNextQuestion(2));
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
        //pStartQuiz.setLayout(new GridLayout(6,1));
        //pStartQuiz.setLayout(new BoxLayout(pStartQuiz, BoxLayout.PAGE_AXIS));


        pHighScore.add(lHighScore);
        pHighScore.add(taHighScore);
        pHighScore.add(bBackHighScore);
        pHighScore.setLayout(new BoxLayout(pHighScore, BoxLayout.PAGE_AXIS));

        //pQuizRound.add(taQuestionQuizRound);
        //pQuizRound.add(taAnser1QuizRound);
        //pQuizRound.add(lQuizRound);
        pQuizRound.add(lQuestionQuizRound);
        //pQuizRound.add(tQuestionQuizRound);
        pQuizRound.add(rbAnswer1);
        pQuizRound.add(rbAnswer2);
        pQuizRound.add(rbAnswer3);
        pQuizRound.add(rbCorrectAnswer);
        pQuizRound.add(bPreviousQuizRound);
        pQuizRound.add(bNextQuizRound);
        pQuizRound.add(bBackQuizRound);
        //pQuizRound.setLayout(new FlowLayout());
        //pQuizRound.setLayout(new BoxLayout(pQuizRound, BoxLayout.PAGE_AXIS));
        pQuizRound.setLayout(new GridLayout(0, 1, 5, 5));
        //pQuizRound.setLayout(new GridLayout());
        //pQuizRound.setLayout(new GroupLayout(pQuizRound));
        //pQuizRound.setLayout(new BoxLayout(pQuizRound, BoxLayout.PAGE_AXIS));
        /*GroupLayout layout = new GroupLayout(pQuizRound);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(lQuizRound)
                        .addComponent(lQuestionQuizRound)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(rbAnswer1)
                                .addComponent(rbAnswer2))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lQuizRound)
                                .addComponent(lQuestionQuizRound)
                                .addComponent(rbAnswer1))
                        .addComponent(rbAnswer2)
        );
        pQuizRound.setLayout(layout);*/

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
        try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\aydemirt\\IdeaProjects\\javaMathQuiz\\src\\highscore.txt"))) {
            highScoreList = lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.print(highScoreList);
        taHighScore.setText(highScoreList.toString());
        /*File fHighScore = new File("C:\\Users\\aydemirt\\IdeaProjects\\javaMathQuiz\\src\\highscore.txt");//High Scores are shown directly (no need an extra function like others)
        Scanner scanner = new Scanner(fHighScore);
        //scanner.useDelimiter("\\Z");
        String highscores="";
        int i = 1;
        while (scanner.hasNextLine()) {
            String highscore = i + ". " + scanner.nextLine ();
            i++;
            highscores = highscores + highscore + "\n"  ;
        }
        //taHighScore.setText("High Scores: \n" + highscores);
        taHighScore.setText(highscores);//may be improved like; getQuestions as list, and then sort according to highest scores*/
    }
    void showQuizRoundPanel() throws IOException {
        //String username = tUsernameStartQuiz.getText();
        //int numberOfQuestions = Integer.parseInt(tNumberOfQuestionsStartQuiz.getText());
        pBasePanel.removeAll();
        //getQuestions(numberOfQuestions);
        //startQuiz();
        pBasePanel.add(pQuizRound);
        pBasePanel.revalidate();
        pBasePanel.repaint();
        //String usernameAndNumberOFQuestions = username + "," + numberOfQuestions;
        //return usernameAndNumberOFQuestions;
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
        //File fAddQuestion = new File("C:\\Users\\turgu\\IdeaProjects\\javaMathQuiz\\src\\questions.txt");//High Scores are handled here
        String addQuestion = question + "," + answer1 + "," + answer2 + "," + answer3 + "," + correctAnswer + "\n";
        //fAddQuestion
        Path path = Paths.get("C:\\Users\\aydemirt\\IdeaProjects\\javaMathQuiz\\src\\questions.txt");
        byte[] arr = addQuestion.getBytes();
        try {
            Files.write(path, arr, APPEND);
        }
        catch (IOException ex) {
            System.out.print("Invalid Path");
        }
        //clearAddQuestionTextFields();
        unshowPanels();
    }

    List<String> getQuestions(int numberOFQuestions) throws IOException {
        List<String> questionList;
        try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\aydemirt\\IdeaProjects\\javaMathQuiz\\src\\questions.txt"))) {
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
        /*int reserveSizeOFQuestionsList = numberOfQuestions;
        String currentLine;
        List <String> reserveQuestionsList= new ArrayList<>(reserveSizeOFQuestionsList);
        int reserveCounter=0;
        Random ra = new Random();
        int randomNumber;
        Scanner sc = new Scanner(new File("C:\\Users\\aydemirt\\IdeaProjects\\javaMathQuiz\\src\\questions.txt")).useDelimiter("\n");
        while (sc.hasNext())
        {
            currentLine = sc.next();
            reserveCounter ++;
            if (reserveCounter<=reserveSizeOFQuestionsList)
            {
                reserveQuestionsList.add(currentLine);
            }
            else if ((randomNumber = ra.nextInt(reserveCounter))<reserveSizeOFQuestionsList)
            {
                reserveQuestionsList.set(randomNumber, currentLine);
            }
        }
        //showQuestions(numberOfQuestions, username, reserveQuestionsList);//list length is = numberOfQuestions,so 1 of them is enough
        //taQuizRound.setText(reserveQuestionsList.get(1));
        return reserveQuestionsList;*/
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

        void showFirstQuestion () throws IOException {
            int questionCounter = 1;
            String question1 = reserveQuestionsList.get(questionCounter-1);
            String[] tokens = question1.split(",");
            lQuestionQuizRound.setText("Question " + questionCounter+ ". " + tokens[0]);
            rbAnswer1.setText(tokens[1]);
            rbAnswer2.setText(tokens[2]);
            rbAnswer3.setText(tokens[3]);
            rbCorrectAnswer.setText(tokens[4]);

            System.out.println(reserveQuestionsList);
            }
            //String question1 = reserveQuestionsList.get(0);

        /*String question2 = reserveQuestionsList.get(1);
        String question3 = reserveQuestionsList.get(2);
        String question4 = reserveQuestionsList.get(3);
        String question5 = reserveQuestionsList.get(4);
        String question6 = reserveQuestionsList.get(5);

        taQuizRound.setText(question1 + "\n" + question2 + "\n" + question3 + "\n" + question4 + "\n" + question5 + "\n" + question6);*/
            //taQuizRound.setText(reserveQuestionsList.toString());
            //taQuizRound.setText(question2);


    /*{ //this will change removeAll() and add new components
        File fQuestions = new File("C:\\Users\\aydemirt\\IdeaProjects\\javaMathQuiz\\src\\questions.txt");//High Scores are handled here
        Scanner scanner = new Scanner(fQuestions);

        Random n = new Random(10);
        int Max=5,  Min=1, ii=0;
        String fileName="C:\\Users\\aydemirt\\IdeaProjects\\javaMathQuiz\\src\\questions.txt";
        Path path = Paths.get(fileName);
        long lines = 0;
        try {
            lines = Files.lines(path).count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = (int) (Math.random() * ((Max - Min) + 1));
        int nn=3;

        String questions = Files.readAllLines(Paths.get("C:\\Users\\aydemirt\\IdeaProjects\\javaMathQuiz\\src\\questions.txt")).get(nn);

        String question = Files.readAllLines(Paths.get("C:\\Users\\aydemirt\\IdeaProjects\\javaMathQuiz\\src\\questions.txt")).get(i);
        taQuizRound.setText(question);}

     */


        //scanner.useDelimiter("\\Z");
        /*String questions="";
        int i = 1;
        while (scanner.hasNextLine()) {
            String question = i + ". " + scanner.nextLine ();
            i++;
            questions = questions + question + "\n"  ;
        taQuizRound.setText(questions);
    }}*/

        void showPreviousQuestion () {
            //TODO go back to the previous question
        }

        int showNextQuestion (int questionCounter) {
            //TODO go to the next question
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


