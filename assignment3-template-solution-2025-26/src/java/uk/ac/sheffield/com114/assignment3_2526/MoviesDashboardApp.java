// TODO WE DO PROVIDE THIS ONE TO STUDENTS, BUT NEEDS DOCUMENTING AND CLEANING
// TODO REVISE CLASSES IN CODEPROVIDED
package uk.ac.sheffield.com114.assignment3_2526;

import uk.ac.sheffield.com114.assignment3_2526.codeprovided.gui.AbstractMoviesDashboardPanel;
import uk.ac.sheffield.com114.assignment3_2526.gui.MoviesDashboard;
import uk.ac.sheffield.com114.assignment3_2526.gui.MoviesDashboardPanel;

import java.io.IOException;

// This is the main class used to run the assignment's GUI.
// DO NOT change the implementation as we will use this class to run and test your application
// Please document this class using JavaDoc
// You will need to adequately document this class, including JavaDoc documentation, relevant comments
// You will need to remove these starting template comments provided to help your programming task but irrelevant
// to the programming task
// You will need to remove the TODO comments (if any)

public class MoviesDashboardApp {
    private final EntryCatalog entryCatalog;

    /**
     * Constructor which will create a new entry catalog with the file name
     * passed in, and if theres an exception thrown it will catch it.
     * @param entriesFileName name of the csv file
     */
    public MoviesDashboardApp(String entriesFileName) {
        EntryCatalog abstractEntryCatalog = null;
        try {
            abstractEntryCatalog = new EntryCatalog(entriesFileName);
        } catch (IllegalArgumentException | IOException e) {
            System.err.println(e);
            System.exit(-1);
        }
        this.entryCatalog = abstractEntryCatalog;
    }

    /**
     * Main method that starts the gui with either whats in the args
     * or if theres nothing there then the titles.csv file
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{
                    "./resources/titles.csv"
            };
        }
        MoviesDashboardApp moviesDashboardApp = new MoviesDashboardApp(args[0]);
        moviesDashboardApp.startGUI();
    }

    /**
     * A function to start running the gui
     */
    public void startGUI() {
        // Start GUI
        AbstractMoviesDashboardPanel moviesDashboardPanel = new MoviesDashboardPanel(entryCatalog);
        MoviesDashboard eDashboard = new MoviesDashboard(moviesDashboardPanel);
        eDashboard.setVisible(true);
    }
}