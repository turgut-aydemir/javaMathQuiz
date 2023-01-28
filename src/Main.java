import java.io.IOException;
import java.util.*;
import java.nio.file.*;
import javax.swing.*;

import static java.nio.file.StandardOpenOption.APPEND;

public class Main {
    public static Scanner keyboard = new Scanner(System.in); //to get the user input
    //public static Timer timeCounter = new Timer(); //to see the time used for quiz

    public static void main(String[] args)
    {
        System.out.print("Number of questions: ");
        int j = keyboard.nextInt(); //user input will decide the number of questions
        long startTime = System.currentTimeMillis(); //we start time counter right after user enters desired number of questions
        keyboard.nextLine(); //clears buffer (needed for further use of scanner)

        if (j == 0 || j > 10) //checking if j is 0 or greater than 10
        {
            System.out.println("Max 10 Questions are allowed in the free version!\n");
            System.exit(0); //closes program
        }

        if (j < 0) //number of questions cannot be negative
            j = j * -1; //we multiply it with "-1" and use the positive value as the number of questions

        int i = 0; //will be used as a counter
        while (i < j) //we will keep asking questions until we reach the given value
        {
            randomizer(i); //we call randomizer each time,
            i++;
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        results(elapsedTime); //we show the results
    }

    public static void randomizer(int i) //randy method is used for creating random numbers and passing them to calc method
    {
        Random randomNumber1 = new Random(); //will be used for the values asked in the quiz
        Random randomNumber2 = new Random(); //will be used for operations (+,-,*)
        int n1 = randomNumber1.nextInt(10)+1; //n1 will be between 1 and 10
        int n2 = randomNumber1.nextInt(10)+1; //n2 same logic as n1
        int n3 = randomNumber2.nextInt(3); //n3 same logic but between 1 and 3
        int i2 = i + 1; //will be used as question counter in calc()
        calc(n1,n2,n3,i2); //we have 3 random values created
        i2++; //increased by 1 for each calc run (see main)
    }

    public static int numCorrect = 0; //number of correctly answered questions
    public static int numFalse = 0; //number of wrong answered questions

    public static void calc(int a, int b, int c, int i2) //value created by randomizer will be the parameters here
    {
        double answer = 0; //asnwer can be decimal

        if (c == 0) //if n3=0 we will have addition as the operation
        {
            answer = a + b; //correct answer
            System.out.print("Question " + i2 + "->  "  + a + " + " + b + " = ? ");
        }

        if (c == 1) //conditional if random number is 1,
        {
            answer = a - b;
            System.out.print("Question " + i2 + "->  "  + a + " - " + b + ": "); //subtraction
        }

        if (c == 2) //if random number is 2,
        {
            answer = a * b;
            System.out.print("Question " + i2 + "->  "  + a + " x " + b + ": "); //multiplication
        }

        double input = keyboard.nextDouble(); //users answer obtained here

        if (input == answer) //if same with the actual answer
        {
            System.out.println("Correct :)\n");
            numCorrect++; //increase the number of correct answers by 1
        }

        else //if wrong,
        {
            System.out.println("False! The correct answer is: " + answer + "\n");
            numFalse++; //increase the number of correct answers by 1
        }
    }

    public static void results(long elapsedTime)
    {
        double percentage = (double)numCorrect * 100 / (numCorrect + numFalse); //amount of correct answered questions divided by total number of questions to get the note over 100
        double timePerQuestion = (double)elapsedTime / (numCorrect + numFalse) / 1000;
        String results = "Correct answers: " + numCorrect + " | False answers: " + numFalse + " | Result: " + percentage + " % | Average time: " + timePerQuestion + " seconds\n";
        System.out.println(results); //average time per question
        saveResult(results);
    }

    public static void saveResult(String results)
    {
        Path path = Paths.get("C:\\Users\\turgu\\IdeaProjects\\javaMathQuiz\\src\\results.txt");
        byte[] arr = results.getBytes();

        try {
            Files.write(path, arr, APPEND);
        }
        catch (IOException ex) {
            System.out.print("Invalid Path");
        }
        printGraph();
    }

    public static void printGraph()
    {
        JFrame frame =new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Graph());
        frame.setSize(400,400);
        frame.setLocation(200,200);
        frame.setVisible(true);
    }
}