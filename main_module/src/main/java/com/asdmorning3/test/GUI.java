package com.asdmorning3.test;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String args[])
    {
        String[] words1, words2;
        JFrame frame = new JFrame("test");
        JButton btnSubmit = new JButton("Add");
        JTextField txtFld1 = new JTextField();
        JTextField txtFld2 = new JTextField();
        JLabel lblLang1 = new JLabel("Language:");
        JLabel lblLang2 = new JLabel("Language:");
        JLabel lblWord1 = new JLabel("Word:");
        JLabel lblWord2 = new JLabel("Word:");
        JComboBox comboBoxLang1 = new JComboBox();
        JComboBox comboBoxLang2 = new JComboBox();

        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(0,2));
        frame.add(lblLang1);
        frame.add(lblLang2);
        frame.add(comboBoxLang1);
        frame.add(comboBoxLang2);
        frame.add(lblWord1);
        frame.add(lblWord2);
        frame.add(txtFld1);
        frame.add(txtFld2);
        frame.add(btnSubmit);

        //frame.add(txtFld);
        frame.setVisible(true);
    }
}
