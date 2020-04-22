package com.asdmorning3.test;
import com.asdmorning3.basic.GUI;
import com.asdmorning3.basic.VocableDictionary;
import com.asdmorning3.basic.showAllVocabs;
import com.asdmorning3.basic.Vocable;

import javax.swing.*;

public class main {

	public static void main (String[] args)
	{
		VocableDictionary d = new VocableDictionary();
		d.addVocable(new Vocable("hallo", com.asdmorning3.basic.Vocable.Language.GER),
				new Vocable("hello", com.asdmorning3.basic.Vocable.Language.ENG));
		d.addVocable(new Vocable("haus", com.asdmorning3.basic.Vocable.Language.GER),
				new Vocable("house", com.asdmorning3.basic.Vocable.Language.ENG));

		showAllVocabs Interface = new showAllVocabs(d);

	}

}
