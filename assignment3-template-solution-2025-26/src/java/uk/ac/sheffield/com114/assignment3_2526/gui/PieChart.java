package uk.ac.sheffield.com114.assignment3_2526.gui;

import uk.ac.sheffield.com114.assignment3_2526.Entry;
import uk.ac.sheffield.com114.assignment3_2526.codeprovided.*;
import uk.ac.sheffield.com114.assignment3_2526.codeprovided.gui.AbstractPieChart;

import java.util.ArrayList;
import java.util.List;

// This class provides a basic implementation of the pie chart.
// You will need to change this class A LOT, meaning adding imports, variables, and methods!
// You will need to adequately document this class, including JavaDoc documentation, relevant comments
// You will need to remove these starting template comments provided to help your programming task but irrelevant
// to the programming task
// You will need to remove the TODO comments (if any)
// You will need to remove unused code
// You will need to remove unused variables
// You will need to use adequate naming...

public class PieChart extends AbstractPieChart {

    /**
     * Constructor which passes the catalog and filtered entry to parent class
     * @param catalog
     * @param filteredEntries
     */
    public PieChart(AbstractEntryCatalog catalog, List<AbstractEntry> filteredEntries) {
        super(catalog, filteredEntries);
    }

    /**
     * changes the filtered entries list attribute
     * @param filteredEntriesList
     */
    public void updateFilteredEntriesList(List<AbstractEntry> filteredEntriesList) {
        this.filteredEntriesList = filteredEntriesList;
    }

    /**
     * gets the filtered entry list attribute
     * @return filteredEntriesList
     */
    public List<AbstractEntry> getFilteredEntriesList() {
        return filteredEntriesList;
    }
}
