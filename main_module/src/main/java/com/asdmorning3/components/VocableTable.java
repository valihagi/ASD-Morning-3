package com.asdmorning3.components;

import com.asdmorning3.basic.Vocable;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import java.util.List;

public class VocableTable extends JTable {

	private List<List<Vocable>> vocableList_;

	private List<TableModelListener> listeners_;

	public VocableTable(List<List<Vocable>> vocab)
	{
		vocableList_ = vocab;
	}

	public int getRowCount()
	{
		int max = 0;
		int buffer = 0;
		for (int i = 0; i < vocableList_.size(); i++)
		{
			buffer =  Math.max(vocableList_.get(i).size(), max);
			if (max < buffer)
			{
				max = buffer;
			}
		}
		return max;
	}

	public int getColumnCount()
	{
		return vocableList_.size();
	}

	public String getColumnName(int col)
	{
		if (vocableList_.get(col).size() == 0)
		{
			return "";
		}
		return vocableList_.get(col).get(0).getLanguage().toString();
	}

	public Class<?> getColumnClass(int col)
	{
		return vocableList_.get(0).get(0).getClass();
	}

	public boolean isCellEditable(int var1, int var2)
	{
		return false;
	}

	public Object getValueAt(int row, int col)
	{
		return vocableList_.get(row).get(col).getWord();
	}

	public void setValueAt(Object vocab, int row, int col)
	{
		vocableList_.get(row).set(col, (Vocable)vocab);
	}

	void addTableModelListener(TableModelListener listener)
	{
		listeners_.add(listener);
	}

	void removeTableModelListener(TableModelListener listener)
	{
		listeners_.remove(listener);
	}



}
