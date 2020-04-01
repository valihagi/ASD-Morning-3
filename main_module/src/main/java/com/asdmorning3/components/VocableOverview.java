package com.asdmorning3.components;

import com.asdmorning3.basic.Vocable;
import com.asdmorning3.basic.VocableDictionary;
import com.asdmorning3.test.InterfaceLanguages;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VocableOverview {

	private JFrame frame_;
	private JPanel panel_;
	private JTable table_;
	private InterfaceLanguages.Languages interfaceLanguage_;
	private ArrayList<List<Vocable>> vocable_list_;
	private VocableTable tableModel_;
	private static int  WIDTH = 200;
	private Object[][] data_;
	private String[] columns_;


	public void changeLanguage(InterfaceLanguages.Languages interfaceLanguage) {
		if (interfaceLanguage == interfaceLanguage_)
			return;
		this.interfaceLanguage_ = interfaceLanguage;
		frame_.setName(InterfaceLanguages.getString(interfaceLanguage_, "overview"));
	}

	public VocableOverview(VocableDictionary dict, InterfaceLanguages.Languages interfaceLanguage)
	{
		interfaceLanguage_ = interfaceLanguage;
		frame_ = new JFrame("test"/*InterfaceLanguages.getString(interfaceLanguage_, "overview"))*/);
		vocable_list_ = new ArrayList<List<Vocable>>();
		columns_ = new String[Vocable.Language.class.getEnumConstants().length];
		data_ = new Object[Vocable.Language.class.getEnumConstants().length][];
		int i = 0;
		for (Vocable.Language language: Vocable.Language.class.getEnumConstants())
		{
			System.out.println(i);
			columns_[i] = language.toString();
			try{
				data_[i] = dict.findVocable(language).toArray();
			}
			catch (NullPointerException | IndexOutOfBoundsException ignored) {}
			System.out.println(i);

			i++;
		}
		table_ = new JTable(data_, columns_);
		frame_.setSize(800, 800);
		frame_.add(table_);
		frame_.setVisible(true);
	}

}
