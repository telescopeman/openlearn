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
    
    final String DONE = "Done";
    final String CONTINUE = "Next";
    final String EXIT = "Exit";
    
    /**
     * Creates the window and kicks off basic startup stuff.
     */
    public MainRunner()
    {
        myWindow = new JFrame(TITLE);
        startup();
        
    }

    /**
     * Loads the startup screen.
     */
    private void startup()
    {
        
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
            System.out.println("Question_");
            if (t.getKey() == DONE)
            {
                done = true;
            }
            else if (t.getKey() == EXIT)
            {
                done = true;
                startup();
                return;
            }
            
            if (t != null && t.isValid())
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
            
        String[] options = {CONTINUE,
                DONE,
                EXIT};
        int option = -1;
        boolean freeToGo = false;
        
        String[] autofill = {"",""};
        String question = "Enter Terms:";
        int focusObj = 0;

        while (option < 2 && !freeToGo)
        {
            message[focusObj].requestFocusInWindow();
            word.setText(autofill[0]);
            def.setText(autofill[1]);
            freeToGo = true;
            option = JOptionPane.showOptionDialog(
                null,
                message, 
                question,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
            System.out.println("Word = " + word.getText() + ", Definition = " + def.getText()); 
            
            boolean wordEmpty = word.getText().equals("");
            boolean defEmpty = def.getText().equals("");
            
            if (!wordEmpty && defEmpty)
            {
                freeToGo = false;
                question = "Sorry, invalid formatting."; 
                autofill[0] = word.getText();
                focusObj = 1; //autofocuses definition instead of word
                //def.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        }
        Term aTerm = new Term(word.getText(),def.getText());
        
        aTerm.setKey(options[option]);
        // this just lets the program be able to tell what button you pressed
        
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
