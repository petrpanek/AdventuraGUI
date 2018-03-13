package com.github.petrpanek.AdventuraGUI.logika;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida implementuje rozhrani pro postavu ve hre
 * 
 * @author Petr Panek
 * @version pro skolni rok 2017/2018
 */

public class Postava {

	private String jmeno;
	private String rada;
	private List<Vec> inventar;
	
	/**
	 * Konstruktor tridy Postava
	 * 
	 * @param jmeno - jmeno postavy
	 */
	public Postava(String jmeno) {
		this.jmeno = jmeno;
		this.inventar = new ArrayList<>();
	}
	
	/**
	 * Konstruktor tridy por postavu s rozpravkou
	 * 
	 * @param jmeno - jmeno osoby
	 * @param rada - text pro rozpravku
	 */
	public Postava(String jmeno, String rada) {
		this.jmeno = jmeno;
		this.rada = rada;
	}
	
	/**
	 * Getter pro jmeno postavy
	 * 
	 * @return jmeno postavy
	 */
	public String getJmeno() {
		return this.jmeno;
	}
	
	/**
	 * Getter pro proslov postavy
	 * 
	 * @return proslov postavy
	 */
	public String getProslov() {
		return this.rada;
	}
	
	/**
	 * Metoda slouzi k pridani veci do inventarre postavy
	 * 
	 * @param item - vec, kterou chceme pridat do inventare
	 */
	public void pridejVec(Vec item) {
		this.inventar.add(item);
	}
	
	/**
	 * Metoda slouzi k odebrani veci z inventare postavy
	 * 
	 * @param nazev - nazve veci, kterou chceme odebrat
	 * @return vraci referenci na danou vec, kterou jsme postave odebrali
	 */
	public Vec odeberVec(String nazev) {
		Vec kVyhozeni = null;
		
		for (Vec item : inventar) {
			if (item.getNazev().equals(nazev)) {
				kVyhozeni = item;
				inventar.remove(item);
				break;
			}
		}
		
		return kVyhozeni;
	}
	
}
