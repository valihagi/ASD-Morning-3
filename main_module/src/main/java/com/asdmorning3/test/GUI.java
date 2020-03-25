package com.asdmorning3.test;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI {

    JFrame frame;
    JPanel pane;
    JButton btnSubmit;
    JTextField txtFld1;
    JTextField txtFld2;
    JLabel lblLang1;
    JLabel lblLang2;
    JLabel lblWord1;
    JLabel lblWord2;
    JComboBox comboBoxLang1;
    JComboBox comboBoxLang2;
    List<Translation> translations;

    static public class Translation {
        Pair<String, String> transl1;
        Pair<String, String> transl2;

        public Translation(String l1, String t1, String l2, String t2)
        {
            transl1 = new Pair<String, String>(l1, t1);
            transl2 = new Pair<String, String>(l2, t2);
        }

        public Pair<String, String> getTranslation1()
        {
            return transl1;
        }
        public Pair<String, String> getTranslation2()
        {
            return transl2;
        }
    }

    public GUI()
    {
        frame = new JFrame("GUI");

        pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        btnSubmit = new JButton("Add");
        txtFld1 = new JTextField();
        txtFld2 = new JTextField();
        lblLang1 = new JLabel("Language:");
        lblLang2 = new JLabel("Language:");
        lblWord1 = new JLabel("Word:");
        lblWord2 = new JLabel("Word:");
        comboBoxLang1 = new JComboBox();
        comboBoxLang2 = new JComboBox();

        frame.setSize(600, 600);

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = .05;
        c.insets = new Insets(10, 55, 0, 100);
        pane.add(lblLang1, c);
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(10, 0, 0, 55);
        pane.add(lblLang2, c);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 55, 10, 100);
        pane.add(comboBoxLang1, c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 10, 55);
        pane.add(comboBoxLang2, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 55, 0, 100);
        pane.add(lblWord1, c);
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(10, 0, 0, 55);
        pane.add(lblWord2, c);

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 55, 0, 100);
        pane.add(txtFld1, c);
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(10, 0, 15, 55);
        pane.add(txtFld2, c);

        c.gridx = 1;
        c.gridy = 4;
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                translations.add(new Translation(comboBoxLang1.getSelectedItem().toString(), txtFld1.getText(),
                        comboBoxLang2.getSelectedItem().toString(), txtFld2.getText()));
            }
        });
        pane.add(btnSubmit, c);

        frame.add(pane);
        frame.setVisible(true);
    }

    public static void main(String args[])
    {
        GUI g = new GUI();
    }

    public List<Translation> getTranslations() {
        return translations;
    }
}
