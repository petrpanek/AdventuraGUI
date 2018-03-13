package com.github.petrpanek.AdventuraGUI.logika;

/**
 * Trida vykonavajici prikaz mluv ve hre.
 * 
 * @author Petr Panek
 * @version pro skolni rok 2017/2018
 */

public class PrikazMluv implements IPrikaz {
	
	private static final String NAZEV = "mluv";
	private HerniPlan plan;
	
	/**
	 * Konstruktor prikazu mluv
	 * 
	 * @param plan - plan kam prikaz umistujeme
	 */
	public PrikazMluv(HerniPlan plan) {
		this.plan = plan;
	}
	
	/**
     *  Metoda pro provedení příkazu ve hře.
     *  Metoda zkouma delku parametru a jestli je rovna nule nebo vetsi nez jedna vyhodi chybovou hlasku.
     *  Dale zkouma jestli je osoba rovna null a jestli ano, tak vyhodi chybovou hlasku.
     *  Jestli je danou postavou trpaslik, probehne rozhovor a hra pokracuje s vyslechnutou radou.
     *  
     *  @param parametrem je jmeno osoby
     *  
     */
	@Override
    public String provedPrikaz(String... parametry) {
    		if (parametry.length == 0 || parametry.length > 1) {
			return "\nJe potrebny prave jeden parametr a to osoba, se kterou ches mluvit!\n";
		}
    		
    		Prostor aktualni = plan.getAktualniProstor();
    		String jmeno = parametry[0];
    		Postava osoba = aktualni.vyberPostavu(jmeno);
    		
    		if (osoba == null) {
    			return "\nTato postava se v danem prostoru nenachazi!\n";
    		} else if (osoba.getJmeno().equals("trpaslik")){
    			plan.setRadu(true);
    			return osoba.getProslov();
    		}  else {
    			return "\nS touto osobou nelze rozmlouvat\n";
    		}
    		
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
	@Override
	public String getNazev() {
		return NAZEV;
	}
}
