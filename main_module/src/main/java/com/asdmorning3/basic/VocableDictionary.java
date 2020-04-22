package com.asdmorning3.basic;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class VocableDictionary implements Serializable {

	public VocableDictionary()
	{
		vocableList = new HashSet<>();
		tagsList = new ArrayList<Tags>();
	}

	private HashSet<Vocable> vocableList;

	private ArrayList<Tags> tagsList;

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
		oos.writeObject(tagsList);
		oos.close();
	}

	public void load(String path) throws IOException, ClassNotFoundException
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		vocableList = (HashSet<Vocable>) ois.readObject();
		tagsList = (ArrayList<Tags>) ois.readObject();
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
			}
		}
		vocableList.addAll(Arrays.asList(vocables));
	}

	public Tags createTag(String description, Color color)
	{
		Tags tag = getTagByDescription(description);
		if(tag != null)
			return tag;

		tag = new Tags(description, color);
		tagsList.add(tag);
		return tag;
	}

	public Tags getTagByDescription(String description)
	{
		for(Tags tag : tagsList)
		{
			if(tag.getDescription().equals(description))
				return tag;
		}

		return null;
	}

	public ArrayList<Tags> getTagsList()
	{
		return tagsList;
	}

	public void addTagToVocable(Tags tag, Vocable vocable)
	{
		boolean addSuccess = vocable.addTag(tag);
	}

	public void removeTagToVocable(Tags tag, Vocable vocable)
	{
		boolean removeSuccess = vocable.removeTag(tag);
	}

}
