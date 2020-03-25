package com.asdmorning3.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class VocableDictionary {

	public VocableDictionary()
	{
		vocableList = new HashSet<>();
	}

	private HashSet<Vocable> vocableList;

	public HashSet<Vocable> getVocableList()
	{
		return vocableList;
	}

	public void addVocable(Vocable ... vocables)
	{
		for (Vocable vocable: vocables)
		{
			for (Vocable translation: vocables)
			{
				if (vocable.getLanguage_() != translation.getLanguage_())
				{
					try{
						if (!vocable.getTranslation(translation.getLanguage_()).getWord_().equals(translation.getWord_()))
						{
							throw new NullPointerException();
						}
					}
					catch (NullPointerException e)
					{
						try{
							vocable.addTranslation_(translation);
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


}
