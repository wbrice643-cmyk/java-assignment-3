package uk.ac.sheffield.com114.assignment3_2526.gui;

import uk.ac.sheffield.com114.assignment3_2526.Entry;
import uk.ac.sheffield.com114.assignment3_2526.EntryCatalog;
import uk.ac.sheffield.com114.assignment3_2526.codeprovided.AbstractEntry;
import uk.ac.sheffield.com114.assignment3_2526.codeprovided.AbstractEntryCatalog;
import uk.ac.sheffield.com114.assignment3_2526.codeprovided.gui.AbstractMoviesDashboardPanel;
import uk.ac.sheffield.com114.assignment3_2526.codeprovided.gui.RangeSlider;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// This class provides a basic implementation of the Movies dashboard panel containing all GUI components.
// You will need to change this class A LOT, meaning adding imports, variables, and methods!
// You will need to adequately document this class, including JavaDoc documentation, relevant comments
// You will need to remove these starting template comments provided to help your programming task but irrelevant
// to the programming task
// You will need to remove the TODO comments (if any)
// You will need to remove unused code
// You will need to remove unused variables
// You will need to use adequate naming...

public class MoviesDashboardPanel extends AbstractMoviesDashboardPanel {

    private RangeSlider imdbScoreSlider;
    private RangeSlider runtimeSlider;
    private RangeSlider releaseYearSlider;

    private JComboBox<String> comboBoxMovieTitles;
    private JComboBox<String> comboBoxType;
    private JComboBox<String> comboBoxAgeCertification;

    private JTable statsTable;

    private PieChart pieChart;
    private PieChartPanel pieChartPanel;

    /**
     * Constuctor which sets the layout and calls functions
     * to set up all components
     * @param entryCatalog
     */
    public MoviesDashboardPanel(EntryCatalog entryCatalog) {
        // thanks to the next line, this class has access to entryCatalog
        super(entryCatalog);
        // TODO you can edit/add/change the following lines
        // setting up the layout to be a BorderLayout
        this.setLayout(new BorderLayout());
        // Setting up range sliders for imdbScoreSlider, runtimeSlider and releaseYearSlider
        setupRangeSliders();
        // Setting up the comboboxes for the variables comboBoxMovieTitles, comboBoxType and comboBoxAgeCertification
        setupComboBoxes();
        // Setting up the controls panel (sliders + combo boxes)
        setupControlsPanel();
        // Setting up the pie chart
        setupPieChartPanel();
        // Setting up the panel containing the statistics
        setupStatsPanel();
        //updateStatsPanelContents();

        // Setting up listeners
        setupListeners();
    }

    /**
     * Function which sets up the listeners and events for
     * all the different filters
     */
    protected void setupListeners() {
        comboBoxMovieTitles.addActionListener(e -> {
            filteredEntriesList = new ArrayList<AbstractEntry>();
            if (comboBoxMovieTitles.getSelectedItem().equals("")){
                filteredEntriesList = entryCatalog.getEntriesList();
            }
            else {
                for (AbstractEntry en : entryCatalog.getEntriesList()) {
                    Entry entry = (Entry) en;
                    if (entry.getTitle().equals(comboBoxMovieTitles.getSelectedItem())) {
                        filteredEntriesList.add(entry);
                    }
                }
            }
            changeStatTable();
            updatePieChart();
        });
        comboBoxType.addActionListener(e -> {
            filteredEntriesList = new ArrayList<AbstractEntry>();
            if (comboBoxType.getSelectedItem().equals("")){
                filteredEntriesList = entryCatalog.getEntriesList();
            }
            else {
                for (AbstractEntry en : entryCatalog.getEntriesList()) {
                    Entry entry = (Entry) en;
                    if (entry.getType() == AbstractEntryCatalog.Type.valueOf(comboBoxType.getSelectedItem().toString())) {
                        filteredEntriesList.add(entry);
                    }
                }
            }
            changeStatTable();
            updatePieChart();
        });
        comboBoxAgeCertification.addActionListener(e -> {
            filteredEntriesList = new ArrayList<AbstractEntry>();
            if (comboBoxAgeCertification.getSelectedItem().equals("")){
                filteredEntriesList = entryCatalog.getEntriesList();
            }
            else {
                for (AbstractEntry en : entryCatalog.getEntriesList()) {
                    Entry entry = (Entry) en;
                    if (entry.checkAgeCertification()) {
                        if (entry.getAgeCertification() == AbstractEntryCatalog.Certification.valueOf(comboBoxAgeCertification.getSelectedItem().toString())) {
                            filteredEntriesList.add(entry);
                        }
                    }
                }
            }
            changeStatTable();
            updatePieChart();
        });
        imdbScoreSlider.addChangeListener(e -> {
            filteredEntriesList = new ArrayList<AbstractEntry>();
            for (AbstractEntry en : entryCatalog.getEntriesList()) {
                Entry entry = (Entry) en;
                if (entry.getImdbRating()>= imdbScoreSlider.getLowerValue() && entry.getImdbRating()<= imdbScoreSlider.getUpperValue()) {
                    filteredEntriesList.add(entry);
                }
            }
            changeStatTable();
            updatePieChart();
        });
        runtimeSlider.addChangeListener(e -> {
            filteredEntriesList = new ArrayList<AbstractEntry>();
            for (AbstractEntry en : entryCatalog.getEntriesList()) {
                Entry entry = (Entry) en;
                if (entry.getRuntime()>= runtimeSlider.getLowerValue() && entry.getRuntime()<= runtimeSlider.getUpperValue()) {
                    filteredEntriesList.add(entry);
                }
            }
            changeStatTable();
            updatePieChart();
        });
        releaseYearSlider.addChangeListener(e -> {
            filteredEntriesList = new ArrayList<AbstractEntry>();
            for (AbstractEntry en : entryCatalog.getEntriesList()) {
                Entry entry = (Entry) en;
                if (entry.getYear()>= releaseYearSlider.getLowerValue() && entry.getYear()<= releaseYearSlider.getUpperValue()) {
                    filteredEntriesList.add(entry);
                }
            }
            changeStatTable();
            updatePieChart();
        });
    }

    /**
     *Function which can be called to re paint the pie chart
     */
    public void updatePieChart(){
        pieChart.updateFilteredEntriesList(filteredEntriesList);
        pieChartPanel = new PieChartPanel(pieChart);
        pieChartPanel.setPreferredSize(new Dimension(300, 400));
        this.add(pieChartPanel, BorderLayout.CENTER);
    }

    /**
     * Function which displays all of the filer options at the top
     * of the gui.
     */
    @Override
    protected void setupControlsPanel() {
        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        controlsPanel.add(createLabeledSlider("IMDB Score", imdbScoreSlider));
        controlsPanel.add(createLabeledSlider("Runtime (min)", runtimeSlider));
        controlsPanel.add(createLabeledSlider("Release Year", releaseYearSlider));
        controlsPanel.add(new JLabel("Title:"));
        controlsPanel.add(comboBoxMovieTitles);
        controlsPanel.add(new JLabel("Type:"));
        controlsPanel.add(comboBoxType);
        controlsPanel.add(new JLabel("Age Cert:"));
        controlsPanel.add(comboBoxAgeCertification);
        this.add(controlsPanel, BorderLayout.NORTH);
    }

    /**
     * A function which sets up all of the
     * combo boxes
     */
    @Override
    protected void setupComboBoxes() {
        comboBoxMovieTitles = new JComboBox<>();
        comboBoxMovieTitles.addItem("");
        for(AbstractEntry e : entryCatalog.getEntriesList()) {
            Entry entry = (Entry)e;
            comboBoxMovieTitles.addItem(entry.getTitle());
        }
        comboBoxMovieTitles.setPreferredSize(new Dimension(150, 25));

        comboBoxType = new JComboBox<>();
        comboBoxType.addItem(""); // All types
        for(AbstractEntryCatalog.Type type : AbstractEntryCatalog.Type.values()) {
            comboBoxType.addItem(type.toString());
        }
        comboBoxType.setPreferredSize(new Dimension(100, 25));

        comboBoxAgeCertification = new JComboBox<>();
        comboBoxAgeCertification.addItem(""); // All
        for(AbstractEntryCatalog.Certification certification : AbstractEntryCatalog.Certification.values()) {
            comboBoxAgeCertification.addItem(certification.toString());
        }
        comboBoxAgeCertification.setPreferredSize(new Dimension(100, 25));
    }

    /**
     * A function to set up the range sliders
     */
    @Override
    protected void setupRangeSliders() {
        imdbScoreSlider = new RangeSlider(0.0, 10.0, 0.0, 10.0);
        imdbScoreSlider.setPreferredSize(new Dimension(150, 50));
        runtimeSlider = new RangeSlider(0, 300, 0, 300);
        runtimeSlider.setPreferredSize(new Dimension(150, 50));
        releaseYearSlider = new RangeSlider(1980, 2025, 1980, 2025);
        releaseYearSlider.setPreferredSize(new Dimension(150, 50));
    }

    /**
     * A function to set up the pie chart panel
     */
    @Override
    protected void setupPieChartPanel() {
        filteredEntriesList = entryCatalog.getEntriesList();
        pieChart = new PieChart(entryCatalog, filteredEntriesList);
        pieChartPanel = new PieChartPanel(pieChart);
        pieChartPanel.setPreferredSize(new Dimension(300, 400));
        this.add(pieChartPanel, BorderLayout.CENTER);
    }

    /**
     * A function which sets up and outputs the initial values
     * for the stats table
     */
    @Override
    protected void setupStatsPanel() {
        statsTable = new JTable(4,4);
        statsTable.getColumn("A").setHeaderValue("");
        statsTable.getColumn("B").setHeaderValue("Release Year");
        statsTable.getColumn("C").setHeaderValue("Imdb rating");
        statsTable.getColumn("D").setHeaderValue("Runtime");
        statsTable.setValueAt("Minimum:", 1, 0);
        statsTable.setValueAt("Maximum:", 2, 0);
        statsTable.setValueAt("Mean:", 3, 0);
        filteredEntriesList = entryCatalog.getEntriesList();
        statsTable.setValueAt("Total entries: "+filteredEntriesList.size(), 0, 0);
        statsTable.setValueAt(getEarliestReleaseYear(),1,1);
        statsTable.setValueAt(getMinimumImdbScore(),1,2);
        statsTable.setValueAt(getMinRuntime(),1,3);
        statsTable.setValueAt(getLatestRelease(),2,1);
        statsTable.setValueAt(getMaximumImdbScore(),2,2);
        statsTable.setValueAt(getMaxRuntime(),2,3);
        statsTable.setValueAt(getAverageRelease(),3,1);
        statsTable.setValueAt(getAverageImdbScore(),3,2);
        statsTable.setValueAt(getAverageRuntime(),3,3);
        JScrollPane statisticsScrollPane = new JScrollPane(statsTable);
        statisticsScrollPane.setPreferredSize(new Dimension(600, 400));
        this.add(statisticsScrollPane, BorderLayout.EAST);
    }

    /**
     * a function which can be called to
     * chnage the stats table
     */
    public void changeStatTable() {
        statsTable.setValueAt("Total entries: "+filteredEntriesList.size(), 0, 0);
        statsTable.setValueAt(getEarliestReleaseYear(),1,1);
        statsTable.setValueAt(getMinimumImdbScore(),1,2);
        statsTable.setValueAt(getMinRuntime(),1,3);
        statsTable.setValueAt(getLatestRelease(),2,1);
        statsTable.setValueAt(getMaximumImdbScore(),2,2);
        statsTable.setValueAt(getMaxRuntime(),2,3);
        statsTable.setValueAt(getAverageRelease(),3,1);
        statsTable.setValueAt(getAverageImdbScore(),3,2);
        statsTable.setValueAt(getAverageRuntime(),3,3);
    }

    /**
     * creates a slider with a label
     * @param label
     * @param slider
     * @return
     */
    private JPanel createLabeledSlider(String label, RangeSlider slider) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);
        return panel;
    }

    /**
     *
     * @return total/filteredEntriesList.size()
     */
    public double getAverageImdbScore(){
        double total = 0;
        for (AbstractEntry abstractEntry : filteredEntriesList) {
            Entry e = (Entry) abstractEntry;
            total += e.getImdbRating();
        }
        return total/filteredEntriesList.size();
    }

    /**
     *
     * @return earliestReleaseYear
     */
    public int getEarliestReleaseYear(){
        int earliestReleaseYear = 2029;
        for (AbstractEntry abstractEntry : filteredEntriesList) {
            Entry e = (Entry) abstractEntry;
            if(e.getYear() < earliestReleaseYear){
                earliestReleaseYear = e.getYear();
            }
        }
        return earliestReleaseYear;
    }

    /**
     *
     * @return minimum
     */
    public double getMinimumImdbScore(){
        double minimum = 100;
        for (AbstractEntry abstractEntry : filteredEntriesList) {
            Entry e = (Entry) abstractEntry;
            if(e.getImdbRating() < minimum){
                minimum = e.getImdbRating();
            }
        }
        return minimum;
    }

    /**
     *
     * @return minimum
     */
    public int getMinRuntime(){
        int minimum = 100;
        for (AbstractEntry abstractEntry : filteredEntriesList) {
            Entry e = (Entry) abstractEntry;
            if(e.getRuntime() < minimum){
                minimum = e.getRuntime();
            }
        }
        return minimum;
    }

    /**
     *
     * @return latestRelease
     */
    public int getLatestRelease(){
        int latestRelease = 0;
        for (AbstractEntry abstractEntry : filteredEntriesList) {
            Entry e = (Entry) abstractEntry;
            if(e.getYear()>latestRelease){
                latestRelease = e.getYear();
            }
        }
        return latestRelease;
    }

    /**
     *
     * @return maximum
     */
    public double getMaximumImdbScore(){
        double maximum = -1;
        for (AbstractEntry abstractEntry : filteredEntriesList) {
            Entry e = (Entry) abstractEntry;
            if(e.getImdbRating() > maximum){
                maximum = e.getImdbRating();
            }
        }
        return maximum;
    }

    /**
     *
     * @return maximum
     */
    public int getMaxRuntime(){
        int maximum = -1;
        for (AbstractEntry abstractEntry : filteredEntriesList) {
            Entry e = (Entry) abstractEntry;
            if(e.getRuntime() > maximum){
                maximum = e.getRuntime();
            }
        }
        return maximum;
    }

    /**
     *
     * @return total / filteredEntriesList.size()
     */
    public int getAverageRelease(){
        int total = 0;
        for (AbstractEntry abstractEntry : filteredEntriesList) {
            Entry e = (Entry) abstractEntry;
            total += e.getYear();
        }
        return total / filteredEntriesList.size();
    }

    /**
     *
     * @return total / filteredEntriesList.size()
     */
    public int getAverageRuntime(){
        int total = 0;
        for (AbstractEntry abstractEntry : filteredEntriesList) {
            Entry e = (Entry) abstractEntry;
            total += e.getRuntime();
        }
        return total / filteredEntriesList.size();
    }

}
