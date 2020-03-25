package com.asdmorning3.test;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GUITest {

    GUI g;

    @Before
    public void setUp() throws Exception {
        g = new GUI();
    }

    @Test
    public void testConstructor()
    {
        g.comboBoxLang1.addItem("FRA");
        g.comboBoxLang2.addItem("GER");
        g.txtFld1.setText("fille");
        g.txtFld2.setText("Mädchen");
        g.btnSubmit.doClick();
        assert(g.getTranslations().size() != 0);
    }
}
