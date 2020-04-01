package com.asdmorning3.test;
import com.asdmorning3.basic.Vocable;
import com.asdmorning3.basic.VocableDictionary;
import com.asdmorning3.components.VocableOverview;

import javax.swing.*;

public class main {

	public static void main (String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				VocableDictionary dict = new VocableDictionary();
				dict.addVocable(new Vocable("hello", Vocable.Language.ENG), new Vocable("hallo", Vocable.Language.GER));
				new VocableOverview(dict, InterfaceLanguages.Languages.DE);
			}
		});
	}

}
