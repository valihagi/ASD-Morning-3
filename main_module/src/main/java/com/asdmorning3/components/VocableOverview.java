package com.asdmorning3.components;

import com.asdmorning3.basic.Vocable;
import com.asdmorning3.basic.VocableDictionary;
import com.asdmorning3.test.InterfaceLanguages;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VocableOverview {

	private JFrame frame_;
	private JScrollPane pane_;
	private JTable table_;
	private InterfaceLanguages.Languages interfaceLanguage_;
	private ArrayList<List<Vocable>> vocable_list_;
	private static int  WIDTH = 200;
	private Object[][] data_;
	private String[] columns_;
	private InterfaceLanguages languages;


	public void changeLanguage(InterfaceLanguages.Languages interfaceLanguage) {
		if (interfaceLanguage == interfaceLanguage_)
			return;
		this.interfaceLanguage_ = interfaceLanguage;
		frame_.setName(languages.getString(interfaceLanguage_, "overview"));
	}

	public VocableOverview(VocableDictionary dict, InterfaceLanguages.Languages interfaceLanguage)
	{
		// TODO implement changing header of JFrame according to InterfaceLanguage
		languages = new InterfaceLanguages();
		interfaceLanguage_ = interfaceLanguage;
		frame_ = new JFrame(languages.getString(interfaceLanguage, "overview"));
		columns_ = new String[Vocable.Language.class.getEnumConstants().length];
		int i = 0;
		for (Vocable.Language language: Vocable.Language.class.getEnumConstants())
		{
			columns_[i] = Vocable.getLanguageWord(language);
			i++;
		}
		String data_[][] = dict.getTable();
		table_ = new JTable(data_, columns_);


		pane_ = new JScrollPane(table_);
		frame_.setSize(Vocable.Language.class.getEnumConstants().length * WIDTH, 400);
		frame_.add(pane_);
	}

	public void changeVisibility(boolean visibility)
	{
		frame_.setVisible(visibility);
	}

	public JScrollPane getContent()
	{
		return pane_;
	}
}
