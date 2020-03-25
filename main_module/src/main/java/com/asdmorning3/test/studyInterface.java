package com.asdmorning3.test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class studyInterface {
    static JFrame frame;
    private static JDialog d;
    static JList b;
    public studyInterface() {
        frame = new JFrame("Study Interface");
        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        frame.setSize(600, 600);
        String[] languages = {"English", "German"};
        final String[] english = {"house", "dog", "cat", "mouse"};
        final String[] german = {"Haus", "Hund", "Katze", "Maus"};
        final JComboBox setLanguages = new JComboBox(languages);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = .05;
        c.insets = new Insets(10, 55, 0, 100);
        pane.add(setLanguages, c);

        JButton button = new JButton("Show vocabulary");
        c.gridx = 1;
        c.gridy = 4;
        pane.add(button, c);
        frame.add(pane);
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Object obj = setLanguages.getSelectedItem();
                String currentLanguage = String.valueOf(obj);
                d = new JDialog(frame, currentLanguage);
                d.setSize(400, 400);
                if(currentLanguage == "English")
                    b = new JList(english);
                else
                    b = new JList(german);
                d.add(b);
                d.setVisible(true);
            }
        });
        frame.setVisible(true);

    }
}