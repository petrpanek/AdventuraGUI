package com.github.petrpanek.AdventuraGUI.logika;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Batoh batoh;
    private boolean vyslechRadu;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
    		batoh = new Batoh();
        zalozProstoryHry();
        this.vyslechRadu = false;
    }
    
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví vchod do jeskyně.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor vchod = new Prostor("vchod","vchod do jeskyně");
        Prostor veznice = new Prostor("věznice", "věznice, ve které se nachází trpaslík");
        Prostor jeskyne = new Prostor("jeskyně","jeskyně, kde se nachází nepřítel, který střeží luk");
        Prostor rozcesti = new Prostor("rozcestí","rozcestí, které umožňuje jít buď do první nebo druhé síně a zpět do jeskyně");
        Prostor chodba = new Prostor("chodba","chodba, ze které lze jít buď do stoky nebo první sině a zpět do jeskyně");
        Prostor stoka = new Prostor("stoka","stoka, ve které se nachází meč");
        Prostor prvniSin = new Prostor("prvniSin","první síň, kde se nachází nepřítel");
        Prostor druhaSin = new Prostor("druhaSin","druhá síň, kde se nachází nepřítel");
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        vchod.setVychod(veznice);
        veznice.setVychod(jeskyne);
        veznice.setVychod(vchod);
        jeskyne.setVychod(chodba);
        jeskyne.setVychod(rozcesti);
        jeskyne.setVychod(veznice);
        chodba.setVychod(prvniSin);
        chodba.setVychod(stoka);
        chodba.setVychod(jeskyne);
        rozcesti.setVychod(druhaSin);
        rozcesti.setVychod(prvniSin);
        rozcesti.setVychod(jeskyne);
        druhaSin.setVychod(rozcesti);
        prvniSin.setVychod(rozcesti);
        prvniSin.setVychod(chodba);
        stoka.setVychod(chodba);
                
        aktualniProstor = vchod;  // hra začíná u vchodu do jeskyně
        
       // vytvareji se jednotlive predmety
        Vec jablko = new Vec("jablko", true);
        Vec hruska = new Vec("hruska", true);
        Vec luk = new Vec("luk", true);
        Vec truhla = new Vec("truhla", false);
        Vec prsten = new Vec("prsten", true);
        Vec mecStoka = new Vec("medenyMec", true);
        Vec mecNepritel = new Vec("zlatyMec", true);
        
        // vlozeni predmetu do jednotlivych mistnosti
        veznice.vlozVec(jablko);
        veznice.vlozVec(hruska);
        jeskyne.vlozVec(truhla);
        stoka.vlozVec(mecStoka);
        
        // vytvoreni postav
        Postava trpaslik = new Postava("trpaslik", 
        		"\nVez, ze kazdy nepritel ma svou Achilovu patu! Kazdeho protivnika prozrazuji jeho oci, do kterych kdyz se radne zadivas, muzes z nich cist kazdy jeho pohyb jako z otevrene knihy.\n");
        
        Postava lupic = new Postava("lupic");
        lupic.pridejVec(luk);
        Postava barbar = new Postava("barbar");
        barbar.pridejVec(prsten);
        Postava velitel = new Postava("velitel");
        velitel.pridejVec(mecNepritel);
        
        // vlozeni postav do jednotlivych mistnosti
        veznice.vlozPostavu(trpaslik);
        jeskyne.vlozPostavu(lupic);
        prvniSin.vlozPostavu(barbar);
        druhaSin.vlozPostavu(velitel);
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    
    /**
     * Getter pro odkaz na batoh
     * 
     * @return batoh
     */
    public Batoh getBatoh() {
    		return batoh;
    }
    
    /**
     * Metoda slouzi jako setter pro promennou vyslechlRadu
     * 
     * @param hodnota - True/False podle toho, zdali si radu vyslechl ci nikoliv
     */
    public void setRadu(boolean hodnota) {
    		this.vyslechRadu = hodnota;
    } 
    
    /**
     * Metoda slouzi jako getter pro promennou vyslechlRadu
     * 
     * @return hodnotu promenne vyslechlRadu
     */
    public boolean getRadu() {
    		return this.vyslechRadu;
    }
    
    /**
     * Metoda zkouma, zdali se v obsahu batohu nachazi tri predmety, diky
     * kterym lze hru vyhrat
     * 
     * @return True/False podle obsahu veci v batohu
     */
    public boolean jeKonec() {
    		if ((batoh.obsahujeVec("luk") && batoh.obsahujeVec("prsten")) && batoh.obsahujeVec("zlatyMec")) {
    			return true;
    		}
    		return false;
    }

}
