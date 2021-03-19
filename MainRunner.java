import javax.swing.*;
import java.awt.Dimension;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Arrays;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

import java.io.DataInputStream;
import java.io.FileInputStream;
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

    private String curName = "Untitled";
    final String DONE = "Done";
    final String CONTINUE = "Next";
    final String EXIT = "Exit";

    /**
     * Creates the window and kicks off basic startup stuff.
     */
    public MainRunner()  throws IOException 
    {
        myWindow = new JFrame(TITLE);
        myWindow.pack();
        myWindow.setSize(new Dimension(400, 1000));
        //myWindow.setVisible(true);

        startup();

    }

    /**
     * Loads the startup screen.
     */
    private void startup() throws IOException 
    {

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
                options[0]);

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

        
        saveToFile();
        // save to file
        //public static void main(String[] args) {
      

    }
    
    public void saveToFile() throws IOException 
    {
        DataStreams.writeFile(curName + ".set", myTerms);
        System.out.println("Successfully saved " + curName + ".set to file.");
    }
    
    
    public void readFromFile(name) throws IOException 
    {
        DataStreams.readFile(name);
        System.out.println("Successfully saved " + curName + ".set to file.");
    }
    
    public void setKnowledge(int amount)
    {
        knowledge = amount
    }
    
    
    public void loadSet()
    {

        if (myTerms.size() < 1)
        {
            Runtime.getRuntime().exit(0);
        }
        myWindow.setVisible(true);
        //myWindow.add(new JScrollPane(listContainer), BorderLayout.CENTER);
    }

    /**
     * Creates a new set from scratch.
     */
    public void createNewSet()  throws IOException 
    {
        curName = singleInputPrompt("What will you call this set?");

        
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

    public String singleInputPrompt(String question)
    {
        JTextField word = new JTextField();
        RequestFocusListener rfl = new RequestFocusListener();

        word.addAncestorListener( rfl );

        String[] options = {CONTINUE};
        int option = -1;
        boolean freeToGo = false;

        while (option < 2 && !freeToGo)
        {
            // message[focusObj].requestFocusInWindow();
            freeToGo = true;
            option = JOptionPane.showOptionDialog(
                null,
                word, 
                question,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

            boolean wordEmpty = word.getText().equals("");

            if (wordEmpty)
            {
                freeToGo = false;
            }
        }

        return word.getText();

    }

    public Term getNewTerm()
    {
        JTextField word = new JTextField();
        JTextField def = new JTextField();
        JTextField aliases = new JTextField();
        JTextField hints = new JTextField();

        int focusObj = 0;
        RequestFocusListener rfl = new RequestFocusListener();

        word.addAncestorListener( rfl );
        Object[] message = {
                "Term:", word,
                "Definition:", def,
                "Hint(s):", hints,
                "Alias(es) (Advanced):", aliases};

        String[] options = {CONTINUE,
                DONE,
                EXIT};
        int option = -1;
        boolean freeToGo = false;

        String[] autofill = {"",""};
        String question = "Enter Terms:";

        while (option < 2 && !freeToGo)
        {
            // message[focusObj].requestFocusInWindow();
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
                //autofocuses definition instead of word
                word.removeAncestorListener( rfl );
                def.addAncestorListener( rfl );
                //def.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        }

        ArrayList<String> newHints = parseList(hints.getText());

        ArrayList<String> newAliases = parseList(aliases.getText());

        Term aTerm = new Term(word.getText(),def.getText(),newAliases);

        if (option == -1) // if user manually closed the window, don't throw an error.
        {
            option = 2;
        }

        aTerm.setKey(options[option]);
        // this just lets the program be able to tell what button you pressed

        return aTerm;

    }

    public ArrayList<String> parseList(String str)
    {
        ArrayList<String> elephantList = new ArrayList<>(Arrays.asList(str.split(",")));
        return elephantList;
    }

    private void openFromFile()
    {

    }

}
