/**
 * Testovaci trida BatohTest testuje jednotlive moznosti batohu
 * 
 * @author Petr Panek
 * @version pro skolni rok 2017/2018
 */

package com.github.petrpanek.AdventuraGUI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.github.petrpanek.AdventuraGUI.logika.Batoh;
import com.github.petrpanek.AdventuraGUI.logika.Vec;

/**
 * Testova trida BatohTest slouzi k otestovani tridy Batoh
 * 
 * @author  Petr Pánek
 * @version pro skolni rok 2017/2018
 */
public class BatohTest {
	
	/***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }
    
    /**
     * Testuje vlozeni veci
     */
    @Test
    public void testVlozeniVeci() {
    		Batoh batoh = new Batoh();
    		Vec vec = new Vec("mec", true);
    		batoh.vlozDoBatohu(vec);
    		assertEquals(true, batoh.obsahujeVec("mec"));
    }
    
    /**
     * Testuje vyhozeni veci
     */
    @Test
    public void testVyhozeniVeci() {
    		Batoh batoh = new Batoh();
    		Vec vec = new Vec("mec", true);
    		batoh.vlozDoBatohu(vec);
    		assertEquals(vec, batoh.vyhodZBatohu("mec"));
    }
    
    /**
     * Testuje vlozeni neprenositelne veci
     */
    @Test
    public void testVlozNeprenositelnouVec() {
    		Batoh batoh = new Batoh();
    		Vec vec = new Vec("truhla", false);
    		batoh.vlozDoBatohu(vec);
    		assertEquals(false, batoh.obsahujeVec("truhla"));
    }
    
    /**
     * Test kapacity batohu
     */
    @Test
    public void testKapacityBatohu() {
    		Batoh batoh = new Batoh();
    		Vec vec1 = new Vec("vec1", true);
    		Vec vec2 = new Vec("vec2", true);
    		Vec vec3 = new Vec("vec3", true);
    		Vec vec4 = new Vec("vec4", true);
    		Vec vec5 = new Vec("vec5", true);
    		Vec vec6 = new Vec("vec6", true);
    		Vec vec7 = new Vec("vec7", true);
    		Vec vec8 = new Vec("vec8", true);
    		
    		assertEquals(true, batoh.vejdeSe());
    		batoh.vlozDoBatohu(vec1);
    		assertEquals(true, batoh.vejdeSe());
    		batoh.vlozDoBatohu(vec2);
    		assertEquals(true, batoh.vejdeSe());
    		batoh.vlozDoBatohu(vec3);
    		assertEquals(true, batoh.vejdeSe());
    		batoh.vlozDoBatohu(vec4);
    		assertEquals(true, batoh.vejdeSe());
    		batoh.vlozDoBatohu(vec5);
    		assertEquals(true, batoh.vejdeSe());
    		batoh.vlozDoBatohu(vec6);
    		assertEquals(true, batoh.vejdeSe());
    		batoh.vlozDoBatohu(vec7);
    		assertEquals(false, batoh.vejdeSe());
    		batoh.vlozDoBatohu(vec8);
    		assertEquals(false, batoh.obsahujeVec("vec8"));
    }
}
