package com.asdmorning3.basic;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vocable implements Serializable {

	public Vocable(@NotNull String word, @NotNull Language language) {
		if (word.length() == 0)
		{
			throw new IllegalArgumentException("Word has to be at least of lentght one."); //TODO constant for interface language
		}
		this.word_ = word;
		this.language_ = language;
		this.translation_ = new HashMap<Language, Vocable>();
		this.tags_ = new ArrayList<Tags>();
	}

	public enum Language {
		ENG,
		GER,
		FRA
	}

	private String word_;

	private Language language_ ;

	private HashMap<Language, Vocable> translation_;

	private ArrayList<Tags> tags_;

	public Vocable getTranslation(@NotNull Language language) throws IndexOutOfBoundsException, NullPointerException, IllegalArgumentException
	{
		if (language == language_)
		{
			throw new IllegalArgumentException("Translation to same language not supported."); //TODO constant for interface language
		}
		return translation_.get(language);
	}

	public void addTranslation(@NotNull Vocable vocable) throws IllegalArgumentException
	{
		if (vocable.getLanguage() == language_)
		{
			throw new IllegalArgumentException("Can't have a Translation from " + language_ + " to " + vocable.getLanguage()); //TODO constant for interface language
		}
		translation_.put(vocable.getLanguage(), vocable);
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
	public void editTranslation(Language language, String vocable)
	{
		Vocable tmp_vcb = translation_.get(language), new_vcb;
		translation_.remove(language);
		translation_.put(language, (new_vcb = new Vocable(vocable, language)));
		for(Vocable.Language l : Vocable.Language.values())
		{
			if(l != language)
			{
				try {
					new_vcb.addTranslation(tmp_vcb.getTranslation(l));
				}
				catch(NullPointerException ex) {}
			}
		}
	}

	public void removeTranslation(Language language)
	{
		translation_.remove(language);
	}

	public String getWord() {
		return word_;
	}

	public String getWord(Language language) {
		try {
			return getTranslation(language).getWord();
		}
		catch(IllegalArgumentException e) {
			return word_;
		}
		catch(Exception e)
		{
			return "";
		}
	}

	public void setWord_(String word_) {
		this.word_ = word_;
	}

	public Language getLanguage() {
		return language_;
	}

	public void setLanguage_(Language language_) {
		this.language_ = language_;
	}

	public boolean equals(Vocable vocable)
	{
		return vocable.getWord().equals(word_) && vocable.getLanguage().equals(language_);
	}

	public static String getLanguageWord(Language language)
	{
		switch(language)
		{
			case GER:
				return "Deutsch";
			case ENG:
				return "English";
			default:
				return "Language not Implemented";
		}
  }

	public boolean addTag(Tags addTag)
	{
		for(Tags tag : tags_)
		{
			if(tag.getDescription().equals(addTag.getDescription()))
			{
				return false;
			}
		}
		tags_.add(addTag);
		return true;
	}

	public boolean removeTag(Tags removeTag)
	{
		for(Tags tag : tags_)
		{
			if(tag.getDescription().equals(removeTag.getDescription()))
			{
				tags_.remove(tag);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Tags> getTags() { return tags_; }

	public boolean hasTag(Tags hasTag)
	{
		for(Tags tag : tags_)
		{
			if(tag.getDescription().equals(hasTag.getDescription()))
			{
				return true;
			}
		}
		return false;
  }
}
