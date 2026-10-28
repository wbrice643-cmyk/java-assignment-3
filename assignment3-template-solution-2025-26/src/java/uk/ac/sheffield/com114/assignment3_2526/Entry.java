package uk.ac.sheffield.com114.assignment3_2526;

import uk.ac.sheffield.com114.assignment3_2526.codeprovided.AbstractEntry;
import uk.ac.sheffield.com114.assignment3_2526.codeprovided.AbstractEntryCatalog;

import java.util.List;

// This class is used to create objects from entries.
// This class provides a non-operational basic implementation of it.
// You will need to change this class A LOT, meaning adding imports, variables, and methods!
// You will need to adequately document this class, including JavaDoc documentation, relevant comments
// You will need to remove these starting template comments provided to help your programming task but irrelevant
// to the programming task
// You will need to remove the TODO comments (if any)
// You will need to remove unused code
// You will need to remove unused variables
// You will need to use adequate naming...

public class Entry extends AbstractEntry {
    private List<String> entryLine;

    /**
     * Constructor which passes the id and entry line to it's parent
     * whilst also assigining the entry line to an attribute
     * @param id
     * @param entryLine
     */
    public Entry(int id, List<String> entryLine) {
        super(id, entryLine);
        this.entryLine = entryLine;
    }

    /**
     *
     * @return entryLine.get(0)
     */
    public String getMovieId() {
        return entryLine.get(0);
    }
    /**
     *
     * @return entryLine.get(1)
     */
    public String getTitle() {
        return entryLine.get(1);
    }
    /**
     *
     * @return entryLine.get(2)
     */
    public AbstractEntryCatalog.Type getType() {
        return AbstractEntryCatalog.Type.valueOf(entryLine.get(2));
    }
    /**
     *
     * @return entryLine.get(3)
     */
    public String getDescription() {
        return entryLine.get(3);
    }
    /**
     *
     * @return entryLine.get(4)
     */
    public int getYear() {
        return Integer.valueOf(entryLine.get(4));
    }
    /**
     *
     * @return entryLine.get(5)
     */
    public AbstractEntryCatalog.Certification getAgeCertification(){
        return AbstractEntryCatalog.Certification.valueOf(entryLine.get(5).replace("-","_"));
    }
    /**
     * A function that checks if age certification is provided or not
     * @return boolean
     */
    public boolean checkAgeCertification(){
        if(entryLine.get(5) != ""){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     *
     * @return entryLine.get(6)
     */
    public int getRuntime(){
        return Integer.valueOf(entryLine.get(6));
    }
    /**
     *
     * @return entryLine.get(7)
     */
    public double getImdbRating(){
        return Double.valueOf(entryLine.get(7));
    }
}
