package com.asdmorning3.test;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String args[])
    {
        JFrame frame = new JFrame("GUI");

        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JButton btnSubmit = new JButton("Add");
        JTextField txtFld1 = new JTextField();
        JTextField txtFld2 = new JTextField();
        JLabel lblLang1 = new JLabel("Language:");
        JLabel lblLang2 = new JLabel("Language:");
        JLabel lblWord1 = new JLabel("Word:");
        JLabel lblWord2 = new JLabel("Word:");
        JComboBox comboBoxLang1 = new JComboBox();
        JComboBox comboBoxLang2 = new JComboBox();

        frame.setSize(600, 600);

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        pane.add(lblLang1, c);
        c.gridx = 1;
        c.gridy = 0;
        pane.add(lblLang2, c);
        c.gridx = 0;
        c.gridy = 1;
        pane.add(comboBoxLang1, c);
        c.gridx = 1;
        c.gridy = 1;
        pane.add(comboBoxLang2, c);

        c.gridx = 0;
        c.gridy = 2;
        pane.add(lblWord1, c);
        c.gridx = 1;
        c.gridy = 2;
        pane.add(lblWord2, c);

        c.gridx = 0;
        c.gridy = 3;
        pane.add(txtFld1, c);
        c.gridx = 1;
        c.gridy = 3;
        pane.add(txtFld2, c);

        c.gridx = 1;
        c.gridy = 4;
        pane.add(btnSubmit, c);

        frame.add(pane);
        frame.setVisible(true);
    }
}
