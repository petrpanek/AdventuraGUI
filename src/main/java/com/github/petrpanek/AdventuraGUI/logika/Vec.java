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
	
	/**
	 * Konstruktor tridy Vec
	 * 
	 * @param nazev - nazev veci
	 * @param prenositelnost - True/False zdali je dana vec prenositelna
	 */
	public Vec(String nazev, boolean prenositelnost) {
		this.nazev = nazev;
		this.prenositelnost = prenositelnost;
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
	 * Getter prenositelnosti
	 * 
	 * @return True/False podle prenositelnosti
	 */
	public boolean getPrenositelnost() {
		return prenositelnost;
	}
	
	@Override
	public String toString() {
		return getNazev();
	}
}
