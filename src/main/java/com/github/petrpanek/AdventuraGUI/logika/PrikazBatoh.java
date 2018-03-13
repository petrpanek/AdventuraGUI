package com.github.petrpanek.AdventuraGUI.logika;

/**
 *  Třída PrikazBatoh implementuje pro hru příkaz batoh.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author     Petr Panek
 * @version    pro školní rok 2017/2018
 */

public class PrikazBatoh implements IPrikaz {
	
	private static final String NAZEV = "batoh";
	private HerniPlan plan;
	
	/**
	 * Konstruktor tridy PrikazBatoh
	 * 
	 * @param plan - herni plan, na ktery prikaz umistujeme
	 */
	public PrikazBatoh(HerniPlan plan) {
		this.plan = plan;
	}
	
	/**
     * Provadi prikaz "batoh"
     * Vypise obsah batohu
     *  
     *  @param Neobsahuje parametry
     *  @return Obsah batohu
     */
	@Override
    public String provedPrikaz(String... prikazy) {
		if (plan.getBatoh().getObsahBatohu().equals("")) {
			return "\nBatoh nic neobsahuje!\n";
		} else {
			return "\nObsah batohu: " + plan.getBatoh().getObsahBatohu() + "\n";
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
