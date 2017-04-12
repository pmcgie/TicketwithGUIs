package com.Paul_McGie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pmcgie on 4/10/2017.
 */
public class TicketDeletion extends JFrame {
    private JPanel MainPanel;
    private JTextField IDEntry;
    private JLabel EnterID;
    private JButton Submit;


    private JLabel DeletedTicket;

    protected String TicketMessage;

    protected int IDNum;

    public int getIDNum() {
        return IDNum;
    }

    public void setDeletedTicket(JLabel deletedTicket) {
        DeletedTicket.setText(TicketMessage);
    }

    public String getTicketMessage() {
        return TicketMessage;
    }

    public void setTicketMessage(String ticketMessage) {
        TicketMessage = ticketMessage;
    }

    public TicketDeletion() {
        setContentPane(MainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDNum = Integer.parseInt(IDEntry.getText());
                setVisible(false);
            }
        });
    }
}
