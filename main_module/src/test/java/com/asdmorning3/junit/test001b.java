package com.asdmorning3.junit;

import com.asdmorning3.basic.Vocable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class test001b {

	@Test
	@DisplayName("Vocable Test Basic")
	void vocableTest()
	{
		Vocable vocable1 = new Vocable("what", Vocable.Language.ENG);
		Vocable vocable2 = new Vocable("was", Vocable.Language.GER);
		Vocable vocable3 = new Vocable("wie", Vocable.Language.GER);
		vocable1.addTranslation_(Vocable.Language.GER, vocable2);
		assert (vocable1.getTranslation(Vocable.Language.GER) == vocable2);
	}
}
