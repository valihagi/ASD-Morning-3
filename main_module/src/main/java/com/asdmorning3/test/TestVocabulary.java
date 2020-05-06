package com.asdmorning3.test;

import com.asdmorning3.basic.Vocable;
import com.asdmorning3.basic.VocableDictionary;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TestVocabulary {
    static JFrame frame;
    private static JDialog d;
    static JList list_vocabs;
    static JList list_test;
    public JLabel lblLang1 = new JLabel();
    public JLabel lblLang2 = new JLabel();
    public JButton button_start = new JButton();
    VocableDictionary dictionary;
    InterfaceLanguages.Languages interface_languages;
    InterfaceLanguages languages;
    //JTextField t1;
    HashMap<String, Vocable.Language> language_list = new HashMap();

    private java.util.List<Vocable> vocableList;

    public TestVocabulary(VocableDictionary dictionary_, InterfaceLanguages.Languages lang) {
        languages = new InterfaceLanguages();
        dictionary = dictionary_;
        interface_languages = lang;
        frame = new JFrame("Test Interface");
        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        frame.setSize(600, 600);

        ArrayList<String> languages = new ArrayList<String>();
        for (Vocable.Language language : Vocable.Language.class.getEnumConstants()) {
            languages.add(language.name());
            language_list.put(language.name(), language);
        }

        final JComboBox fromLanguage = new JComboBox(new DefaultComboBoxModel<String>(languages.toArray(new String[0])));
        final JComboBox toLanguage = new JComboBox(new DefaultComboBoxModel<String>(languages.toArray(new String[0])));
        final JComboBox setAnotherLanguage;

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = .05;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 55, 0, 100);
        pane.add(lblLang1, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = .05;
        c.insets = new Insets(10, 0, 0, 55);
        pane.add(lblLang2, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 55, 10, 100);
        pane.add(fromLanguage, c);

        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 10, 55);
        pane.add(toLanguage, c);

        c.gridx = 0;
        c.gridy = 2;
        list_test = new JList();
        c.insets = new Insets(10, 55, 10, 100);
        JScrollPane scrollPaneL = new JScrollPane(list_vocabs);
        pane.add(scrollPaneL, c);

        c.gridx = 1;
        c.gridy = 2;
        list_vocabs = new JList();
        c.insets = new Insets(10, 0, 10, 55);
        JScrollPane scrollPaneR = new JScrollPane(list_test);
        pane.add(scrollPaneR, c);

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 55, 10, 100);
        pane.add(button_start, c);

        frame.add(pane);
        button_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object obj = toLanguage.getSelectedItem();
                String currentLanguage = String.valueOf(obj);

                Object obj2 = fromLanguage.getSelectedItem();
                String secondCurrentLanguage = String.valueOf(obj2);

                if (currentLanguage != secondCurrentLanguage) {
                    vocableList = dictionary.getAllFromLanguage(language_list.get(currentLanguage));
                    int size = vocableList.size();
                    String[] words = new String[size];
                    for (int counter = 0; counter < vocableList.size(); counter++) {
                        words[counter] = vocableList.get(counter).getWord();
                    }
                    final String[] final_list = words;
                    list_vocabs.setListData(final_list);
                } else
                    JOptionPane.showMessageDialog(frame,
                            "Select two different languages!",
                            "WARNING",
                            JOptionPane.WARNING_MESSAGE);

            }

        });


        list_vocabs.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent event) {
                Object obj = fromLanguage.getSelectedItem();
                String currentLanguage = String.valueOf(obj);
                if (!event.getValueIsAdjusting()) {
                    JList source = (JList) event.getSource();
                    String selected = source.getSelectedValue().toString();
                    HashSet<Vocable> test = dictionary.getVocableList();
                    for (Vocable i : test)
                        if (i.getWord().equals(selected)) {
                            //t1.setText(i.getTranslation(language_list.get(currentLanguage)).getWord());
                        }
                }
            }

        });

        list_test.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent event) {
                Object obj = fromLanguage.getSelectedItem();
                String currentLanguage = String.valueOf(obj);
                if (!event.getValueIsAdjusting()) {
                    JList source = (JList) event.getSource();
                    String selected = source.getSelectedValue().toString();
                    HashSet<Vocable> test = dictionary.getVocableList();
                    for (Vocable i : test)
                        if (i.getWord().equals(selected)) {
                            //t1.setText(i.getTranslation(language_list.get(currentLanguage)).getWord());
                        }
                }
            }

        });
        setIntLang(lang);
        frame.setVisible(true);

    }

    public void setIntLang(InterfaceLanguages.Languages lang) {
        lblLang1.setText(languages.getString(lang, "from"));
        lblLang2.setText(languages.getString(lang, "to"));
        frame.setTitle(languages.getString(lang, "study"));
        button_start.setText(languages.getString(lang, "start"));
    }
}
