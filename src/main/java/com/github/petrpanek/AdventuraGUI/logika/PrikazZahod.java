package com.github.petrpanek.AdventuraGUI.logika;

/**
 * Trida pro prikaz zahod
 * 
 * @author Petr Panek
 * @version pro skolni rok 2017/2018
 */
public class PrikazZahod implements IPrikaz {
	
	private static final String NAZEV = "zahod";
	private HerniPlan plan;
	
	public PrikazZahod(HerniPlan plan) {
		this.plan = plan;
	}
	
	/**
     *  Metoda pro provedení příkazu ve hře.
     *  Metoda zkontroluje zdali danou vec batoh obsahuje, jestli ano, vec vlozi do mistnosti a z batohu odstrani.
     *  
     *  @param vec pro zahozeni
     *  
     */
	@Override
    public String provedPrikaz(String... parametry) {
		if (parametry.length == 0) {
    			return "\nNevybral jsi predmet k odhozeni\n";
    		}
    		
		String kZahozeni = parametry[0];
		
    		if (plan.getBatoh().obsahujeVec(kZahozeni)) {
    			Prostor aktualniProstor = plan.getAktualniProstor();
    			Vec vec = plan.getBatoh().vyhodZBatohu(kZahozeni);
    			aktualniProstor.vlozVec(vec);
    			
    			return "\nVec " + kZahozeni + " byla zahozena.\n";
    			
    		} else {
    			return "\nTakova vec se v batohu nenachazi!\n";
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
