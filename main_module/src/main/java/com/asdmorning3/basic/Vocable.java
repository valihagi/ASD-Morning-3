package com.asdmorning3.basic;

import java.util.HashMap;
import java.util.Map;

public class Vocable {

	public Vocable(String word_, Language language_) {
		this.word_ = word_;
		this.language_ = language_;
		this.translation_ = new HashMap<Language, Vocable>();
	}

	public enum Language {
		ENG,
		GER
	}

	private String word_;

	private Language language_ ;

	private HashMap<Language, Vocable> translation_;

	public Vocable getTranslation(Language language) throws IndexOutOfBoundsException, NullPointerException
	{
		return translation_.get(language);
	}

	public void addTranslation_(Language language, Vocable vocable) throws IllegalArgumentException
	{
		if (language == language_)
		{
			throw new IllegalArgumentException("Can't have a Translation from " + language_ + " to " + language);
		}
		translation_.put(language, vocable);
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
