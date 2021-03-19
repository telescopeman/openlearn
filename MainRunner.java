import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 * Write a description of class MainRunner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainRunner
{
    // instance variables - replace the example below with your own
    private int x;
    JFrame myWindow;
    final String TITLE = "OpenStudy";
    ArrayList<Term> myTerms = new ArrayList<Term>();

    /**
     * Constructor for objects of class MainRunner
     */
    public MainRunner()
    {
        startup();
    }

    private void startup()
    {
        myWindow = new JFrame(TITLE);
        myWindow.setVisible(true);
        Object[] options = {"Create a new set",
                "Open an existing set",
                "Close " + TITLE};
        int choice = JOptionPane.showOptionDialog(myWindow,
                "What do you want to do?",
                TITLE,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);

        if (choice == 0)
        {
            createNewSet();
        }
        else if (choice ==1)
        {
            openFromFile();
        }
        else
        {
            Runtime.getRuntime().exit(0);
        }
    }

    private void createNewSet()
    {
        boolean done = false;
        Term t;
        while (done == false)
        {
            t = getNewTerm();
            if (t.isValid())
            {
                myTerms.add(t);
            }
        }
    }

    
    private Term getNewTerm()
    {
        JTextField word = new JTextField();
        JTextField def = new JTextField();
        Object[] message = {
                "Term:", word,
                "Definition:", def
            };

        int option = JOptionPane.showConfirmDialog(
            null,
            message, 
            "Login",
            JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            //idk
        } else {
            System.out.println("Login canceled");
        }
        Term aTerm = new Term(word.getText(),def.getText());
        return aTerm;

    }

    private void openFromFile()
    {

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
