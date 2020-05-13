package com.asdmorning3.junit;

import com.asdmorning3.basic.GUI;
import com.asdmorning3.basic.Vocable;
import com.asdmorning3.basic.VocableDictionary;
import com.asdmorning3.test.TestVocabulary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class test007 {
    @Test
    @DisplayName("test vocabulary")
    void test1(){
        VocableDictionary dictionary = new VocableDictionary();
        GUI g = new GUI(dictionary);
    }


}