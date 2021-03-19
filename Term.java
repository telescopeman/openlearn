import java.util.ArrayList;
/**
 * Represents a term and definition.
 *
 * @author Caleb Copeland
 * @version 3/19/21
 */
public class Term
{
    // instance variables - replace the example below with your own
    private String dispTerm;
    private String definition;
    private ArrayList<String> aliases = new ArrayList<String>();
    private ArrayList<String> hints = new ArrayList<String>();
    private int knowledge = 0;
    
    
    private String key = "";
    
    /**
     * Constructor for objects of class Term.
     * 
     */
    public Term(String disp, String def)
    {
        // initialise instance variables
        setDisp(disp);
        setDefinition(def);
    }
    
    /**
     * Constructor for objects of class Term
     */
    public Term(String disp, String def, ArrayList<String> alia)
    {
        // initialise instance variables
        setDisp(disp);
        setDefinition(def);
        aliases.addAll(alia);
    }
    
    public void addHints(ArrayList<String> newHints)
    {
        hints.addAll(newHints);
    }
    /**
     * Sets the term name.
     *
     * @param  newDisp New term
     * @return    if dispTerm was unchanged
     */
    public boolean setDisp(String newDisp)
    {
        boolean ret = (dispTerm == newDisp);
        dispTerm = newDisp;
        return ret;
    }
    
    public String getDisp()
    {
        return dispTerm;
    }
    
    public int getKnowledge()
    {
        return knowledge;
    }
    
    public String getDefinition()
    {
        return definition;
    }
    
    public ArrayList<String> getAliases()
    {
        return aliases;
    }
    
    public ArrayList<String> getHints()
    {
        return hints;
    }

    public void setKey(String k)
    {
        key = k;
    }
    
    public String getKey()
    {
        return key;
    }
    
    /**
     * Sets the term definition.
     *
     * @param  newDef New term
     * @return    if definition was unchanged
     */
    public boolean setDefinition(String newDef)
    {
        boolean ret = (definition == newDef);
        definition = newDef;
        return ret;
    }
    
    public boolean isValid()
    {
        return (dispTerm != "" && definition != "" && key != "Invalid");
    }
}
