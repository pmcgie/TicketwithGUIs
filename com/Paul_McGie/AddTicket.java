package com.Paul_McGie;

import sun.applet.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pmcgie on 4/2/2017.
 */
public class AddTicket extends JFrame {
    private JLabel NewTixForm;
    private JLabel DateReport;
    private JTextField ProbEntry;
    private JLabel ProbText;
    private JTextField PersonName;
    private JButton submitCurrentEntryButton;
    private JPanel MainPanel;
    private JComboBox Priority;

    //Values to pull into file and lists
    protected String problem = "";
    protected String reporterName = "";
    protected String priorityEntry = "";

    //getter and setters
    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getPriorityEntry() {
        return priorityEntry;
    }

    public void setPriorityEntry(String priority) {
        this.priorityEntry = priority;
    }

    //Add Ticket process
    public AddTicket() {
        super("Add Ticket Information");
        setContentPane(MainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        submitCurrentEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setProblem(ProbEntry.getText());
                setReporterName(PersonName.getText());
                setPriorityEntry((String)Priority.getSelectedItem());
                setVisible(false);
            }
        });
    }


}
