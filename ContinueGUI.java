package com.Paul_McGie;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;

/**
 * Created by pmcgie on 4/9/2017.
 */
public class ContinueGUI extends JFrame{
    private JRadioButton yesRadioButton;
    private JPanel MainPanel;
    private JRadioButton noRadioButton;
    private JLabel Question;

    protected boolean DetermineContinue;

    public boolean isDetermineContinue() {
        return DetermineContinue;
    }

    public void setDetermineContinue(boolean determineContinue) {
        DetermineContinue = determineContinue;
    }

    public ContinueGUI() {
        setContentPane(MainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        yesRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (yesRadioButton.isSelected()) {
                    DetermineContinue = true;
                    setVisible(false);
                }
            }
        });
        noRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (noRadioButton.isSelected()) {
                    DetermineContinue = false;
                    setVisible(false);
                }
            }
        });
    }


}
