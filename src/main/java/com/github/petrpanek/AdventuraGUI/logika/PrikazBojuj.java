package com.github.petrpanek.AdventuraGUI.logika;

/**
 * Trida PrikazBojuj implementujici IPrikaz pro herni prikaz bojuj
 * 
 *  @author Petr Pánek
 *  @version pro skolni rok 2017/2018
 */

public class PrikazBojuj implements IPrikaz {
	
	private static final String NAZEV = "bojuj";
	private HerniPlan plan;
	private Hra hra;
	
	/**
	 * Konstruktor tridy PrikazBojuj
	 * 
	 * @param plan - herni plan, na ktery chceme prikaz umistit
	 * @param hra - hra pro prikaz
	 */
	public PrikazBojuj(HerniPlan plan, Hra hra) {
		this.plan = plan;
		this.hra = hra;
	}
	
	/**
     *  Metoda pro provedení příkazu ve hře.
     *  Metoda porovnava vstup s jednotlivymi moznostmi.
     *  Kdyz je vstupem lupic, zkontroluje ze je vyslechnuta rad, nasledne odhodi luk do mistnosti a vymaze postavu z mistnosti.
     *  Kdyz jde o barbara, odhodi do mistnosti prsten a vymaze postavu z mistnosti.
     *  Kdyz jde o velitel, zkontroluje obsah batohu jestli se v nem nachazi mec, dale odhodi zlaty mec a vymaze postavu z mistnosti.
     *  
     *  @param parametrem metody je jmeno protivnika
     *  @return zprava o prubehu bitvy
     */
	@Override
    public String provedPrikaz(String... parametry) {
    		if (parametry.length == 0 || parametry.length > 1) {
    			return "\nJe potrebny prave jeden parametr a to osoba, se kterou ches bojovat!\n";
    		}
    		
    		Prostor aktualni = plan.getAktualniProstor();
    		String jmeno = parametry[0];
    		Postava osoba = aktualni.vyberPostavu(jmeno);
    		Vec vec;
    		
    		if (osoba.getJmeno().equals("lupic")) {
    			if (plan.getRadu()) {
    				vec = osoba.odeberVec("luk");
    				aktualni.vlozVec(vec);
    				aktualni.odeberPostavu(jmeno);
				
    				return "\nLupic byl porazen!\n";
    			} else {
    				hra.setKonecHry(true);
    				return "\nProhral jsi! Lupic te porazil ¯\\_(ツ)_/¯\n";
    			}
    		} else if (osoba.getJmeno().equals("barbar")) {
    			vec = osoba.odeberVec("prsten");
			aktualni.vlozVec(vec);
			aktualni.odeberPostavu(jmeno);
    			
			return "\nBarbar byl porazen!\n";
    		} else if (osoba.getJmeno().equals("velitel")) {
    			if (plan.getBatoh().obsahujeVec("medenyMec")) {
    			vec = osoba.odeberVec("zlatyMec");
			aktualni.vlozVec(vec);
			aktualni.odeberPostavu(jmeno);
				
			return "\nVelitel byl porazen!\n";
    			} else {
    				hra.setKonecHry(true);
    				return "\nProhral jsi! Velitel te porazil ¯\\_(ツ)_/¯\n";
    			}
    		} else {
    			return "\nS touto osobou nelze bojovat!\n";
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
