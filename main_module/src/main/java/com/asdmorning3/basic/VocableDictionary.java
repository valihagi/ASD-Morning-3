package com.asdmorning3.basic;

import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

	public List<Vocable> getAllFromLanguage(Vocable.Language lang)
	{
		List<Vocable> vocables = new ArrayList<>();
		for(Vocable v : vocableList)
		{
			if(v.getLanguage() == lang)
				vocables.add(v);
		}
		return vocables;
	}

	public boolean exists(Vocable v)
	{
		for(Vocable d : getAllFromLanguage(v.getLanguage()))
			if(Objects.equals(d.getWord(), v.getWord()))
				return true;
		return false;
	}

	public void addVocable(Vocable ... vocables)
	{
		for (Vocable vocable: vocables)
		{
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
				else if(!exists(vocable))
				{
					vocableList.addAll(Arrays.asList(vocables));
				}
			}
		}

	}
}
