package com.asdmorning3.junit;

import com.asdmorning3.basic.Vocable;
import com.asdmorning3.basic.VocableDictionary;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
		vocable1.addTranslation_(vocable2);
		assert (vocable1.getTranslation(Vocable.Language.GER) == vocable2);
	}



	@ParameterizedTest
	@DisplayName("Dictionary Test")
	@MethodSource ("stringStream")
	void dictionaryTest(String english, String german)
	{
		try{
			initVocables(english, german);
		}
		catch (IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
			return;
		}
		dictionary = new VocableDictionary();
		dictionary.addVocable(vocable1, vocable2);
		assert(vocable1.getTranslation(vocable2.getLanguage_()) == vocable2);
		assert(vocable2.getTranslation(vocable1.getLanguage_()) == vocable1);
		dictionary.addVocable(vocable2, vocable2);
		dictionary.addVocable(vocable1, vocable2);
		assert(dictionary.getVocableList().size() == 2);
		assert(dictionary.getVocableList().contains(vocable1));
		assert(dictionary.getVocableList().contains(vocable2));
		try{
			vocable2.getTranslation(vocable2.getLanguage_());
		}
		catch (IllegalArgumentException e)
		{
			return;
		}
		assert(false);
	}
}
