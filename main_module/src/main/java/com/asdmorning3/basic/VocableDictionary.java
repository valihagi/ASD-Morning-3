package com.asdmorning3.basic;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import com.asdmorning3.basic.Vocable;

public class VocableDictionary implements Serializable {

	public VocableDictionary()
	{
		vocableList = new HashSet<>();
	}

	private HashSet<Vocable> vocableList;

	public HashSet<Vocable> getVocableList()
	{
		return vocableList;
	}

	public List<Vocable> findVocable(String word, Vocable.Language language)
	{
		try{
			return vocableList.stream().filter(
					(vocable) -> (vocable.getWord().equals(word) && vocable.getLanguage().equals(language)))
					.collect(Collectors.toList());
		}
		catch (NullPointerException e)
		{
			return new ArrayList<>();
		}

	}

	public List<Vocable> findVocable(Vocable.Language language)
	{
		try{
			return vocableList.stream().filter(
					(vocable) -> (vocable.getLanguage().equals(language))
			).collect(Collectors.toList());
		}
		catch (NullPointerException e)
		{
			return new ArrayList<>();
		}
	}

	public List<String> findWord(Vocable.Language language)
	{
		try{
			return vocableList.stream().filter(
					(vocable) -> (vocable.getLanguage().equals(language))
			).map(Vocable::getWord).collect(Collectors.toList());
		}
		catch (NullPointerException e)
		{
			return new ArrayList<>();
		}
	}

	public String[][] getTable()
	{
		String[][] table = new String[findVocable(Vocable.Language.GER).size()][Vocable.Language.class.getEnumConstants().length];
		int row = 0;
		int col = 0;
		for (Vocable vocab : findVocable(Vocable.Language.GER)) {
			col = 0;
			for (Vocable.Language language : Vocable.Language.class.getEnumConstants()) {
				table[row][col] = vocab.getWord(language);
				col++;
			}
			row++;
		}
		return table;
	}

	public void save(String path) throws IOException
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
		oos.writeObject(vocableList);
		oos.close();
	}

	public void load(String path) throws IOException, ClassNotFoundException
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		vocableList = (HashSet<Vocable>) ois.readObject();
	}

	public void addVocable(Vocable ... vocables)
	{
		boolean german = false;
		for (Vocable vocable: vocables)
		{
			if (vocable.getLanguage() == Vocable.Language.GER)
			{
				german = true;
			}
			for (Vocable translation: vocables)
			{
				if (vocable.getLanguage() != translation.getLanguage())
				{
					try{
						if (!vocable.getTranslation(translation.getLanguage()).getWord().equals(translation.getWord()))
						{
							throw new NullPointerException();
						}
					}
					catch (NullPointerException e)
					{
						try{
							vocable.addTranslation(translation);
						}
						catch (IllegalArgumentException ex)
						{
							System.out.println("This should never happen.");
						}
					}
				}
			}
		}
		ArrayList<Vocable> list = new ArrayList<Vocable>(Arrays.asList(vocables));
		if (!german)
		{
			Vocable voc1 = new Vocable("", Vocable.Language.GER);
			for (Vocable vcb : list)
			{
				vcb.addTranslation(voc1);
			}
			list.add(voc1);
		}
		vocableList.addAll(Arrays.asList(vocables));
	}


}
