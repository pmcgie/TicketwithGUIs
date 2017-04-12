package com.Paul_McGie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pmcgie on 4/2/2017.
 */
public class InitialOptionsGUI extends JFrame {
    protected JRadioButton enterTicketRadioButton;
    protected JRadioButton deleteTicketByIDRadioButton;
    protected JRadioButton deleteTicketByIssueRadioButton;
    protected JRadioButton searchTicketsByIssueRadioButton;
    protected JRadioButton displayAllTicketsRadioButton;
    protected JRadioButton quitRadioButton;
    protected JButton submitButton;
    protected JPanel MainPanel;


    //Define responses in Main menu
    protected int mainresponse = 0;

    protected int getMainresponse() {
        return mainresponse;
    }

    protected void setMainresponse(int mainresponse) {
        this.mainresponse = mainresponse;
    }


    //Create actions for buttons
    protected InitialOptionsGUI() {
        setContentPane(MainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainProcess();
                setVisible(false);
            }
        });
    }

    //Run Main process to determine main response
    protected void MainProcess() {
        //Check JRadio Button Selected

        int response = 0;

        if (enterTicketRadioButton.isSelected()) {
            response = 1;
        } else if (deleteTicketByIDRadioButton.isSelected()) {
            response = 2;
        } else if (deleteTicketByIssueRadioButton.isSelected()) {
            response = 3;
        } else if (searchTicketsByIssueRadioButton.isSelected()) {
            response = 4;
        } else if (displayAllTicketsRadioButton.isSelected()) {
            response = 5;
        } else if (quitRadioButton.isSelected()) {
            response = 6;
        }
        setMainresponse(response);
    }
}
