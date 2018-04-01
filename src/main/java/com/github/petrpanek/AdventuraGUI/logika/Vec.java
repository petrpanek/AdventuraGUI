package com.github.petrpanek.AdventuraGUI.logika;

/**
 * Trida pro jednotlive veci ve hre
 * 
 * @author Petr Panek
 * @version pro skolni rok 2017/2018
 */
public class Vec {
	
	private String nazev;
	private boolean prenositelnost;
	private String nazevObrazku;
	
	/**
	 * Konstruktor tridy Vec
	 * 
	 * @param nazev - nazev veci
	 * @param prenositelnost - True/False zdali je dana vec prenositelna
	 * @param nazevObrazku - nazev obrazku predmetu
	 */
	public Vec(String nazev, boolean prenositelnost, String nazevObrazku) {
		this.nazev = nazev;
		this.prenositelnost = prenositelnost;
		this.nazevObrazku = nazevObrazku;
	}
	
	/**
	 * Getter nazvu veci
	 * 
	 * @return nazev veci
	 */
	public String getNazev() {
		return this.nazev;
	}
	
	/**
	 * Getter nazvu obrazku
	 * 
	 * @return nazev obrazku
	 */
	public String getNazevObrazku() {
		return this.nazevObrazku;
	}
	
	/**
	 * Getter prenositelnosti
	 * 
	 * @return True/False podle prenositelnosti
	 */
	public boolean getPrenositelnost() {
		return prenositelnost;
	}
	
	/**
	 * Metoda vracejici nazev predmetu
	 * 
	 * @return nazev predmetu
	 */
	@Override
	public String toString() {
		return getNazev();
	}
}
