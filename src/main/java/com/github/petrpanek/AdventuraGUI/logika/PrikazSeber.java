package com.github.petrpanek.AdventuraGUI.logika;

/**
 * Trida pro prikaz seber
 * 
 * @author Petr Panek
 * @version pro skolni rok 2017/2018
 */
public class PrikazSeber implements IPrikaz {
	
	private static final String NAZEV = "seber";
	private HerniPlan plan;
	private Batoh batoh;
	
	/**
	 * Konstruktor tridy PrikazSeber
	 * 
	 * @param plan - plan, se kterym prikaz pracuje
	 */
	public PrikazSeber(HerniPlan plan) {
		this.plan = plan;
		this.batoh = plan.getBatoh();
	}
	
	/**
     *  Metoda pro provedení příkazu ve hře.
     *  Metoda porovnava vstup s jednotlivymi vetvemi.
     *  Jestli dany prostor vec obsahuje a v batohu je misto, vlozi se do batohu a nasledne se z mistnosti vymaze.
     *  
     *  @param vec pro vlozeni
     *  
     */
	@Override
    public String provedPrikaz(String... parametry) {
    		if (parametry.length == 0) {
    			return "\nNezadal jsi jmeno veci pro sebrani!\n";
    		} else {
    			String vecProVlozeni = parametry[0];
    			Prostor aktualniProstor = plan.getAktualniProstor();
    			
    			if (aktualniProstor.obsahujeVec(vecProVlozeni)) {
    				Vec vec = aktualniProstor.odebratVec(vecProVlozeni);
    				
    				if (batoh.vlozDoBatohu(vec)) {
    					return "\nVec " + vecProVlozeni + " byla vlozena do batohu.\n";
    				} else {
    					aktualniProstor.vlozVec(vec);
    					return "\nBatoh je naplnen nebo dana vec je neprenositelna.\n" + 
    							"\nBatoh obsahuje: " + batoh.pocetVeciBatohu() + " veci.\n";
    				}
    			} else {
    				return "\nZadna takova vec tu neni!\n";
    			}
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
