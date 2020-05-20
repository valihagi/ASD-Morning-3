package com.asdmorning3.components;

import com.asdmorning3.basic.Vocable;
import com.asdmorning3.basic.VocableDictionary;
import com.asdmorning3.test.InterfaceLanguages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		languages = new InterfaceLanguages();
		interfaceLanguage_ = interfaceLanguage;
		frame_ = new JFrame(languages.getString(interfaceLanguage, "overview"));
		columns_ = new String[Vocable.Language.class.getEnumConstants().length + 1];
		int i = 0;
		for (Vocable.Language language: Vocable.Language.class.getEnumConstants())
		{
			columns_[i] = Vocable.getLanguageWord(language);
			i++;
		}
		columns_[i] = "Difficulty";


		String data_[][] = dict.getTable();
		CustomTableModel customTableModel = new CustomTableModel(data_, columns_);
		table_ = new JTable(customTableModel);

		table_.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				int rating_col = Vocable.Language.class.getEnumConstants().length;
				if(table_.getSelectedColumn() == rating_col)
				{
					int row = table_.getSelectedRow();
					Vocable.Difficulty difficulty;
					ArrayList<String> row_strings = new ArrayList<>(rating_col);
					for (int i = 0 ; i < rating_col; i++)
					{
						row_strings.add(i,(String)table_.getValueAt(row, i));
					}
					if (SwingUtilities.isRightMouseButton(mouseEvent))
					{
						difficulty = dict.increaseDifficulty(row_strings);
					}
					else if(SwingUtilities.isLeftMouseButton(mouseEvent))
					{
						difficulty = dict.increaseDifficulty(row_strings);

						//dict.decreaseDifficulty()
					}
					else
					{
						return;
					}
					updateDifficulty(row, difficulty);
				}
			}

			@Override
			public void mousePressed(MouseEvent mouseEvent) {

			}

			@Override
			public void mouseReleased(MouseEvent mouseEvent) {

			}

			@Override
			public void mouseEntered(MouseEvent mouseEvent) {

			}

			@Override
			public void mouseExited(MouseEvent mouseEvent) {

			}
		});

		pane_ = new JScrollPane(table_);
		frame_.setSize(Vocable.Language.class.getEnumConstants().length * WIDTH, 400);
		frame_.add(pane_);

	}

	public void updateDifficulty(int row, Vocable.Difficulty difficulty)
	{
		table_.setValueAt(difficulty.toString(),row, Vocable.Language.class.getEnumConstants().length);
	}

	public void changeVisibility(boolean visibility)
	{
		frame_.setVisible(visibility);
	}

	public JScrollPane getContent()
	{
		return pane_;
	}
	class CustomTableModel extends DefaultTableModel
	{
		public CustomTableModel(String[][] data, String[] columns) {
			super(data, columns);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

}
