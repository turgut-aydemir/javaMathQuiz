import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;
class SharkQuiz{

    int numRight;
    String temp, keyText;
    ButtonGroup answers;

    public class Question extends JPanel{

        public Question(String queryText, String a1Text, String a2Text,
                        String a3Text, String a4Text, final String keyText){
            JPanel answ = new JPanel();
            JRadioButton a1 = new JRadioButton(a1Text);
            a1.setActionCommand(a1Text);
            JRadioButton a2 = new JRadioButton(a2Text);
            a2.setActionCommand(a2Text);
            JRadioButton a3 = new JRadioButton(a3Text);
            a3.setActionCommand(a3Text);
            JRadioButton a4 = new JRadioButton(a4Text);
            a4.setActionCommand(a4Text);
            TitledBorder ans = new TitledBorder("Answers");
            final ButtonGroup answers = new ButtonGroup();
            JLabel query = new JLabel(queryText);
            String key = keyText;
            answ.setBorder(ans);
            answers.add(a1);
            answers.add(a2);
            answers.add(a3);
            answers.add(a4);
            answ.add(a1);
            answ.add(a2);
            answ.add(a3);
            answ.add(a4);
            answ.setAlignmentX(0);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(Box.createVerticalStrut(10));
            this.add(query);
            this.add(Box.createVerticalStrut(10));
            this.add(answ);

        }
    }
    public void setUpGame(){

        final CardLayout layout = new CardLayout();
        final JPanel root = new JPanel();
        root.setLayout(layout);



        Question q1 = new Question("What shark inspired the movie Jaws?", "Bullshark", "Great White", "Mako", "Thresher", "Bullshark");
        Question q2 = new Question("What is 1 + 1?", "3", "24", "2", "11", "2");
        Question q3 = new Question("What sport does Tiger Woods play?", "Baseball", "Golf", "Car Racing", "Surfing", "Golf");

        JFrame base = new JFrame("Shark Quiz v1.0");

        final JButton prevBtn = new JButton("Previous");
        final JButton nextBtn = new JButton("Next");
        prevBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                layout.previous(root);
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(keyText.equals(answers.getSelection().getActionCommand())){
                    numRight++;{
                        layout.next(root);
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1,0));
        buttonPanel.add(prevBtn); buttonPanel.add(nextBtn);

        root.add(q1, "1");
        root.add(q2, "2");
        root.add(q3, "3");
        base.add(root);
        base.add(buttonPanel,BorderLayout.SOUTH);
        base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        base.setSize(400, 300);
        base.setLocationRelativeTo(null);
        base.setVisible(true);
    }
    public static void main(String[] args){
        SharkQuiz game = new SharkQuiz();
        game.setUpGame();
    }
}