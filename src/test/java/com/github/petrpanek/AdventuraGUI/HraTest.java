package com.github.petrpanek.AdventuraGUI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.github.petrpanek.AdventuraGUI.logika.Hra;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra;

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
        hra = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    	// neni vyuzita
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        
        assertEquals("vchod", hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi věznice");
        assertEquals("věznice", hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("mluv trpaslik");
        assertEquals(true, hra.getHerniPlan().getRadu());
        hra.zpracujPrikaz("jdi jeskyně");
        assertEquals("jeskyně", hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("bojuj lupic");
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().obsahujePostavu("lupic"));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().obsahujeVec("luk"));
        hra.zpracujPrikaz("seber luk");
        assertEquals(true, hra.getHerniPlan().getBatoh().obsahujeVec("luk"));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().obsahujeVec("luk"));
        hra.zpracujPrikaz("jdi chodba");
        assertEquals("chodba", hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi stoka");
        assertEquals("stoka", hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().obsahujeVec("medenyMec"));
        hra.zpracujPrikaz("seber medenyMec");
        assertEquals(true, hra.getHerniPlan().getBatoh().obsahujeVec("medenyMec"));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().obsahujeVec("medenyMec"));
        hra.zpracujPrikaz("jdi chodba");
        assertEquals("chodba", hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi prvniSin");
        assertEquals("prvniSin", hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().obsahujePostavu("barbar"));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().obsahujeVec("prsten"));
        hra.zpracujPrikaz("bojuj barbar");
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().obsahujePostavu("barbar"));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().obsahujeVec("prsten"));
        hra.zpracujPrikaz("seber prsten");
        assertEquals(true, hra.getHerniPlan().getBatoh().obsahujeVec("prsten"));
        assertEquals(false, hra.konecHry());
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().obsahujeVec("prsten"));
        hra.zpracujPrikaz("jdi rozcestí");
        assertEquals("rozcestí", hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi druhaSin");
        assertEquals("druhaSin", hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().obsahujePostavu("velitel"));
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().obsahujeVec("zlatyMec"));
        assertEquals(true, hra.getHerniPlan().getBatoh().obsahujeVec("medenyMec"));
        hra.zpracujPrikaz("bojuj velitel");
        assertEquals(false, hra.getHerniPlan().getAktualniProstor().obsahujePostavu("velitel"));
        assertEquals(true, hra.getHerniPlan().getAktualniProstor().obsahujeVec("zlatyMec"));
        hra.zpracujPrikaz("seber zlatyMec");
        assertEquals(true, hra.konecHry());
        
    }
}
