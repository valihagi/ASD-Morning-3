package com.asdmorning3.junit;

import com.asdmorning3.basic.Vocable;
import com.asdmorning3.basic.VocableDictionary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class test001b {

	static Stream<Arguments> stringStream () {
		return Stream.of(   Arguments.of("what", "was"),
							Arguments.of("how", null),
							Arguments.of(null, "wie"),
							Arguments.of(null, null),
							Arguments.of("", "hallo"));
	}

	Vocable vocable1 = null;
	Vocable vocable2 = null;
	Vocable vocable3 = null;
	VocableDictionary dictionary = null;


	void initVocables(String english, String german) throws IllegalArgumentException
	{
		vocable1 = null;
		vocable2 = null;
		vocable3 = null;
		dictionary = new VocableDictionary();
		String errorMessage = null;
		try{
			vocable1 = new Vocable(english, Vocable.Language.ENG);
		}
		catch (IllegalArgumentException e)
		{
			errorMessage = "vocable 1 triggered: " + e.getMessage();
			throw new IllegalArgumentException(errorMessage);
		}
		try{
			vocable2 = new Vocable(german, Vocable.Language.GER);

		}
		catch (IllegalArgumentException e)
		{
			errorMessage = "vocable 2 triggered: " + e.getMessage();
			throw new IllegalArgumentException(errorMessage);
		}
		try{
			vocable3 = new Vocable(german, null);
		}
		catch (IllegalArgumentException ignored) { }
	}


	@ParameterizedTest
	@DisplayName("Vocable Test Basic")
	@MethodSource("stringStream")
	void vocableTest(String english, String german)
	{
		try{
			initVocables(english, german);
		}
		catch (IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
			return;
		}
		vocable1.addTranslation(vocable2);
		assert (vocable1.getTranslation(Vocable.Language.GER) == vocable2);
	}



	@ParameterizedTest
	@DisplayName("Dictionary Test")
	@MethodSource ("stringStream")
	void dictionaryTest(String english, String german) throws IOException {
		try{
			initVocables(english, german);
		}
		catch (IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
			return;
		}
		dictionary.addVocable(vocable1, vocable2);
		assert(vocable1.getTranslation(vocable2.getLanguage()).equals(vocable2));
		assert(vocable2.getTranslation(vocable1.getLanguage()).equals(vocable1));
		dictionary.addVocable(vocable2, vocable2);
		dictionary.addVocable(vocable1, vocable2);
		assert(dictionary.getVocableList().size() == 2);
		assert(dictionary.getVocableList().contains(vocable1));
		assert(dictionary.getVocableList().contains(vocable2));
		try{
			vocable2.getTranslation(vocable2.getLanguage());
		}
		catch (IllegalArgumentException e)
		{
			try{
				dictionary.save("dictionary.save");
			}
			catch (IOException ex)
			{
				System.out.println("save did not work: " + ex.getMessage());
			}
			try
			{
				dictionary.load("dictionary.save");
			} catch (ClassNotFoundException|IOException ex)
			{
				System.out.println("open did not work: " + ex.getMessage());
				assert(false);
			}
			assert(dictionary.getVocableList().size() == 2);
			for(Vocable vocable: dictionary.getVocableList())
			{
				assert (vocable1.equals(vocable) || vocable2.equals(vocable));
			}
			assert(vocable1.getTranslation(vocable2.getLanguage()).equals(vocable2));
			assert(vocable2.getTranslation(vocable1.getLanguage()).equals(vocable1));
			try
			{
				dictionary.load("this_file_does_not_exist.exe");
			}
			catch (ClassNotFoundException | IOException ex)
			{
				try
				{
					dictionary.load("pom.xml");
				} catch (ClassNotFoundException | IOException exception)
				{
					exception.printStackTrace();
					return;
				}
			}
		}
		assert(false);
	}

	@ParameterizedTest
	@DisplayName("Find Vocable")
	@MethodSource("stringStream")
	void findVocable(String english, String german)
	{
		try{
			initVocables(english, german);
		}
		catch (IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
			return;
		}
		dictionary.addVocable(vocable1, vocable2);
		String word_ = vocable1.getWord();
		Vocable.Language language_ = vocable1.getLanguage();
		assert(word_.length() >= 1);
		assert (language_ != null);
		List<Vocable> results = dictionary.findVocable(word_, language_);
		System.out.println("found " + results.size() + " results");
		for (Vocable vocable: results)
		{
			assert (vocable.equals(vocable1));
		}
	}

	void setUpDict()
	{
		dictionary.addVocable(new Vocable("hello", Vocable.Language.ENG), new Vocable("hallo", Vocable.Language.GER));
		dictionary.addVocable(new Vocable("test", Vocable.Language.ENG), new Vocable("Test", Vocable.Language.GER));
		dictionary.addVocable(new Vocable("test1", Vocable.Language.ENG), new Vocable("Test1", Vocable.Language.GER));
	}

	@Test
	@DisplayName("create Table")
	void createTable()
	{
		dictionary = new VocableDictionary();
		setUpDict();

		System.out.println(Arrays.deepToString(dictionary.getTable()));
	}
}
