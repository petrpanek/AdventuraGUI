package com.github.petrpanek.AdventuraGUI;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.github.petrpanek.AdventuraGUI.logika.Vec;
import com.github.petrpanek.AdventuraGUI.logika.Postava;
import com.github.petrpanek.AdventuraGUI.logika.Prostor;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková
 * @version   pro skolní rok 2016/2017
 */
public class ProstorTest
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

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

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Prostor prostor1 = new Prostor("jeskyne", "temna jeskyne");
        Prostor prostor2 = new Prostor("veznice", "temna veznice");
        
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        
        assertEquals(prostor2, prostor1.vratSousedniProstor("veznice"));
        assertEquals(prostor1, prostor2.vratSousedniProstor("jeskyne"));
        assertNull(prostor2.vratSousedniProstor("vchod"));
    }
    
    /**
     * Testuje vkladani veci do mistnosti.
     */
    @Test
    public void testVeci() {
    		Prostor prostor = new Prostor("jeskyne", "temna jeskyne");
    		Vec jablko = new Vec("jablko", true);
    		Vec truhla = new Vec("truhla", false);
    		
    		prostor.vlozVec(jablko);
    		prostor.vlozVec(truhla);
    		
    		assertEquals(true, prostor.obsahujeVec("jablko"));
    		assertEquals(true, prostor.obsahujeVec("truhla"));
    		assertEquals(false, prostor.obsahujeVec("bota"));
    }
    
    /**
     * Testuje vkladani postav do mistnosti a jejich nasledne odebirani.
     */
    @Test
    public void testPostav() {
    		Prostor prostor = new Prostor("jeskyne", "temna jeskyne");
    		Postava lupic = new Postava("lupic");
    		Postava trpaslik = new Postava("trpaslik", "ahoj");
    	
    		prostor.vlozPostavu(trpaslik);
    		prostor.vlozPostavu(lupic);
    		
    		assertEquals(true, prostor.obsahujePostavu("lupic"));
    		assertEquals(true, prostor.obsahujePostavu("trpaslik"));
    		
    		prostor.odeberPostavu("lupic");
    		
    		assertEquals(false, prostor.obsahujePostavu("lupic"));
    }

}
