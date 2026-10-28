package uk.ac.sheffield.com114.assignment3_2526.gui;

import uk.ac.sheffield.com114.assignment3_2526.codeprovided.gui.AbstractMoviesDashboardPanel;

import javax.swing.*;

// This class provides a basic implementation of the main application window for the MoviesDashboardApp.
// You will probably not need to change this class.
// You will need to adequately document this class, including JavaDoc documentation, relevant comments
// You will need to remove these starting template comments provided to help your programming task but irrelevant
// to the programming task
// You will need to remove the TODO comments (if any)
// You will need to remove unused code
// You will need to remove unused variables
// You will need to use adequate naming...

public class MoviesDashboard extends JFrame {
    public MoviesDashboard(AbstractMoviesDashboardPanel panel){
        setTitle("Movies Dashboard");
        add(panel);
        //maximize the JFrame to fit the entire screen.
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
