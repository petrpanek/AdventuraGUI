package com.github.petrpanek.AdventuraGUI.logika;

/**
 * Trida pro herni prikaz prohledej.
 * 
 * @author Petr Panek
 * @version pro skolni rok 2017/2018
 */
public class PrikazProhledej implements IPrikaz {
	
	private static final String NAZEV = "prohledej";
	private HerniPlan plan;
	
	/**
	 * Konstruktor prikazu prohledej
	 * 
	 * @param plan - plan, se kterym prikaz pracuje
	 */
	public PrikazProhledej(HerniPlan plan) {
		this.plan = plan;
	}
	
	/**
     *  Metoda pro provedení příkazu ve hře.
     *  Metoda pouze aktualizuje mistnost do konzole.
     *  
     *  @param bez parametru
     *  
     */
	@Override
    public String provedPrikaz(String... parametry) {
		return plan.getAktualniProstor().dlouhyPopis();
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
