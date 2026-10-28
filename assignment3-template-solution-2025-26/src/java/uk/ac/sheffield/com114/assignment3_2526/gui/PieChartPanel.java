package uk.ac.sheffield.com114.assignment3_2526.gui;

import uk.ac.sheffield.com114.assignment3_2526.Entry;
import uk.ac.sheffield.com114.assignment3_2526.codeprovided.AbstractEntry;
import uk.ac.sheffield.com114.assignment3_2526.codeprovided.AbstractEntryCatalog;
import uk.ac.sheffield.com114.assignment3_2526.codeprovided.gui.AbstractPieChart;
import uk.ac.sheffield.com114.assignment3_2526.codeprovided.gui.AbstractPieChartPanel;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

// This class provides a basic implementation of the pie chart drawing.
// You will need to change this class A LOT, meaning adding imports, variables, and methods!
// You will need to adequately document this class, including JavaDoc documentation, relevant comments
// You will need to remove these starting template comments provided to help your programming task but irrelevant
// to the programming task
// You will need to remove the TODO comments (if any)
// You will need to remove unused code
// You will need to remove unused variables
// You will need to use adequate naming...

public class PieChartPanel extends AbstractPieChartPanel {


    public PieChartPanel(AbstractPieChart pieChart) {
        super(pieChart);
    }

    /**
     * A funtion which is used to paint all of the parts of the gui
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        PieChart pie = (PieChart) getPieChart();

        int width = getWidth();
        int height = getHeight();

        List<List<AbstractEntry>> splitEntries = new ArrayList<>(12);
        for(int i = 0; i < 12; i++) {
            splitEntries.add(new ArrayList<>());
        }
        List<AbstractEntry> filteredEntries = pie.getFilteredEntriesList();
        List<AbstractEntry> notSpecifiedEntries = new ArrayList<>();
        splitEntries.set(0,notSpecifiedEntries);
        List<AbstractEntry> GEntries = new ArrayList<>();
        splitEntries.set(1,GEntries);
        List<AbstractEntry> PGEntries = new ArrayList<>();
        splitEntries.set(2,PGEntries);
        List<AbstractEntry> PG_13Entries = new ArrayList<>();
        splitEntries.set(3,PG_13Entries);
        List<AbstractEntry> REnties = new ArrayList<>();
        splitEntries.set(4,REnties);
        List<AbstractEntry> NC_17Enties = new ArrayList<>();
        splitEntries.set(5,NC_17Enties);
        List<AbstractEntry> TV_MAEnties = new ArrayList<>();
        splitEntries.set(6,TV_MAEnties);
        List<AbstractEntry> TV_14Enties = new ArrayList<>();
        splitEntries.set(7,TV_14Enties);
        List<AbstractEntry> TV_GEnties = new ArrayList<>();
        splitEntries.set(8,TV_GEnties);
        List<AbstractEntry> TV_PGEnties = new ArrayList<>();
        splitEntries.set(9,TV_PGEnties);
        List<AbstractEntry> TV_YEnties = new ArrayList<>();
        splitEntries.set(10,TV_YEnties);
        List<AbstractEntry> TV_Y7Enties = new ArrayList<>();
        splitEntries.set(11,TV_Y7Enties);

        for (AbstractEntry e : filteredEntries) {
            Entry entry = (Entry)e;
            if(entry.checkAgeCertification()) {
                switch (entry.getAgeCertification()) {
                    case G -> GEntries.add(entry);
                    case PG -> PGEntries.add(entry);
                    case R -> REnties.add(entry);
                    case PG_13 -> PG_13Entries.add(entry);
                    case TV_G -> TV_GEnties.add(entry);
                    case TV_MA -> TV_MAEnties.add(entry);
                    case NC_17 -> NC_17Enties.add(entry);
                    case TV_Y -> TV_YEnties.add(entry);
                    case TV_Y7 -> TV_Y7Enties.add(entry);
                    case TV_PG -> TV_PGEnties.add(entry);
                    case TV_14 -> TV_14Enties.add(entry);
                }
            }
            else{
                notSpecifiedEntries.add(entry);
            }
        }

        double angle = 0;
        int count=0;
        Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.ORANGE, Color.DARK_GRAY, Color.PINK, new Color(20,34,120), Color.BLACK, new Color(255,30,120)};
        String[] types = {"Not-specified","G","PG","PG_13","R","NC_17","TV_MA","TV_14","TV_G","TV_PG","TV_Y","TV_Y7"};
        for (int i = 0; i < 12; i++) {
            g2.setColor(colors[i]);
            if(!splitEntries.get(i).isEmpty()) {
                g2.fillRect(1065,35+35*count,20,20);
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                g2.drawString(types[i]+"("+splitEntries.get(i).size()+")",1100,50+35*count);
                count+=1;
            }
            g2.setColor(colors[i]);
            Arc2D arc = new Arc2D.Double(0.0,0.0,width*2/3,height*2/3, angle, (double)splitEntries.get(i).size()*360 / (double)filteredEntries.size(), Arc2D.PIE);
            g2.fill(arc);
            angle+= (double)splitEntries.get(i).size()*360 / (double)filteredEntries.size();
        }
        repaint();
    }

}
