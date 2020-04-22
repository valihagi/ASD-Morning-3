package com.asdmorning3.basic;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

import com.asdmorning3.basic.Vocable;
import com.asdmorning3.basic.VocableDictionary;



public class showAllVocabs {
    static JFrame frame;
    static JLabel l;
    public showAllVocabs(VocableDictionary vcbd) {
        frame = new JFrame("All Vocabulary");
        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setSize(600, 600);
        l = new JLabel();
        l.setText("test");
        //this gives us all the languages in our program
        Vocable.Language[] languages1 = Vocable.Language.values();

        String[][] input = new String[languages1.length][];
        final String[] german = {"aus", "und", "Hund", "Hahn", "Berg", "aus", "und", "Hund", "Hahn", "Berg", "aus", "und", "Hund", "Hahn", "Berg"};
        final String[] english = {"dog", "house", "chicken", "dog", "house", "chicken", "dog", "house", "chicken", "dog", "house", "chicken"};
        input[0] = english;
        input[1] = german;



        for (String[] words : input)
        {
            JList<String> list = new JList<>(words);
            JScrollPane jps = new JScrollPane(list);

            pane.add(jps);
        }

        frame.add(pane);

        frame.setVisible(true);

    }
}
