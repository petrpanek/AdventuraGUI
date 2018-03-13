package com.github.petrpanek.AdventuraGUI.logika;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida Batoh implementuje rozhrani pro batoh ve hre
 * 
 * @author Petr Pánek
 * @version pro skolni rok 2017/2018
 */

public class Batoh {
	
	private List<Vec> batoh;
	private static final int KAPACITA = 7;
	
	/**
	 * Konstruktor tridy Batoh
	 */
	public Batoh() {
		batoh = new ArrayList<Vec>();
	}
	
	/**
	 * Metoda pro zkontrolovani, zdali se dana vec nachazi v batohu
	 * 
	 * @param vec - vec, kterou chceme najit
	 * @return - True/False podle toho, zdali vec obsahuje
	 */
	public boolean obsahujeVec(String jmeno) {
		for (Vec item : batoh) {
			if (item.getNazev().equals(jmeno)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metoda zjistujici obsah batohu
	 * 
	 * @return retezec obsahujici jednotlive nazvy veci z batohu
	 */
	public String getObsahBatohu() {
		String obsah = "";
		
		for (Vec item : batoh) {
			obsah += item.getNazev() + " ";
		}
		
		return obsah;
	}
	
	/**
	 * Metoda zjistuje, zdali neni naplnena kapacita batohu
	 * 
	 * @return true, zdali je mozne vlozit dalsi vec
	 */
	public boolean vejdeSe() {
		return batoh.size() < KAPACITA;
	}
	
	/**
	 * Metoda pro ziskani poctu veci v batohu
	 * 
	 * @return pocet veci batohu
	 */
	public int pocetVeciBatohu() {
		return batoh.size();
	}
	
	/**
	 * Metoda prida vec do batohu pokud se vejde a je prenositelna
	 * 
	 * @param item - vec pro vlozeni
	 */
	public boolean vlozDoBatohu(Vec item) {
		if (vejdeSe() && item.getPrenositelnost()) {
			batoh.add(item);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Metoda odstrani zadanou vec z batohu
	 * 
	 * @param nazev - nazev veci pro odstraneni
	 */
	public Vec vyhodZBatohu(String nazev) {
		Vec kVyhozeni = null;
		
		for (Vec item : batoh) {
			if (item.getNazev().equals(nazev)) {
				kVyhozeni = item;
				batoh.remove(item);
				break;
			}
		}
		
		return kVyhozeni;
	}

}
