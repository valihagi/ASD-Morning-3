package com.asdmorning3.basic;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Vocable {

	public Vocable(@NotNull String word, @NotNull Language language) {
		if (word.length() == 0)
		{
			throw new IllegalArgumentException("Word has to be at least of lentght one."); //TODO constant for interface language
		}
		this.word_ = word;
		this.language_ = language;
		this.translation_ = new HashMap<Language, Vocable>();
	}

	public enum Language {
		ENG,
		GER
	}

	private String word_;

	private Language language_ ;

	private HashMap<Language, Vocable> translation_;

	public Vocable getTranslation(@NotNull Language language) throws IndexOutOfBoundsException, NullPointerException, IllegalArgumentException
	{
		if (language == language_)
		{
			throw new IllegalArgumentException("Translation to same language not supported."); //TODO constant for interface language
		}
		return translation_.get(language);
	}

	public void addTranslation_(@NotNull Vocable vocable) throws IllegalArgumentException
	{
		if (vocable.getLanguage_() == language_)
		{
			throw new IllegalArgumentException("Can't have a Translation from " + language_ + " to " + vocable.getLanguage_()); //TODO constant for interface language
		}
		translation_.put(vocable.getLanguage_(), vocable);
		vocable.viceVersaTranslation(language_, this);
	}

	public void viceVersaTranslation(Language language, Vocable vocable)
	{
		translation_.put(language, vocable);
	}

	public void editTranslation(Language language, Vocable vocable)
	{
		translation_.remove(language);
		translation_.put(language, vocable);
	}

	public void removeTranslation(Language language)
	{
		translation_.remove(language);
	}

	public String getWord_() {
		return word_;
	}

	public void setWord_(String word_) {
		this.word_ = word_;
	}

	public Language getLanguage_() {
		return language_;
	}

	public void setLanguage_(Language language_) {
		this.language_ = language_;
	}
}
