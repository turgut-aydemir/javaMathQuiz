import java.awt.*;
import javax.swing.*;

public class NewPanelTry extends JFrame{

    private final JPanel pLeft;
    //private final JPanel pMain;
    public NewPanelTry(){

        pLeft = new JPanel();
        setTitle("Math Quiz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1700, 1100));
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(pLeft);
        pack();

        JButton bAddQuestion = new JButton("Add Question");
        pLeft.add(bAddQuestion);

    }

public static void main(String args[]){
    EventQueue.invokeLater(new Runnable(){
        @Override
        public void run(){
                new NewPanelTry().setVisible(true);
            }
    });}
}


