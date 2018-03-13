package com.github.petrpanek.AdventuraGUI.logika;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class Prostor {
	
	private List<Vec> seznamVeci;
	private List<Postava> seznamPostav;

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        seznamVeci = new ArrayList<>();
        seznamPostav = new ArrayList<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisPostav() + "\n";
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
    
    /**
     * Metoda vypisuje jednotlive veci v danem prostoru
     * 
     * @return Textovy retezec s vecmi v danem prostoru oddelene mezerou
     */
    private String popisVeci() {
    		String vypis = "veci:";
    		
    		for (Vec item : seznamVeci) {
    			vypis += " " + item.getNazev(); 
    		}
    		
    		return vypis;
    }
    
    /**
     * Metoda vypisuje postavy v danem prostoru
     * 
     * @return Textovy retezec s postavami v danem prostoru oddeleny mezerou
     */
    private String popisPostav() {
    		String vypis = "postavy:";
    		
    		for (Postava osoba : seznamPostav) {
    			vypis += " " + osoba.getJmeno();
    		}
    		
    		return vypis;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * Metoda zkontroluje zdali se vec opravdu vyskytuje v danem prostoru
     * 
     * @param vec kterou chceme overit
     * @return True/False podle toho, jestli je vec v danem prostoru
     */
    public boolean obsahujeVec(String nazev) {
    		for (Vec item : seznamVeci) {
    			if (item.getNazev().equals(nazev)) {
    				return true;
    			}
    		}
    		
    		return false;
    }
    
    /**
     * Metoda vlozi vec do seznamu veci v danem prostoru
     * 
     * @param vec - vec kterou chceme vlozit
     */
    public void vlozVec(Vec vec) {
    		seznamVeci.add(vec);
    }
    
    /**
     * Metoda odebere vec z prostoru
     * 
     * @param nazev - nezev dane veci
     */
    public Vec odebratVec(String nazev) {
    		Vec vybiranaVec = null;
    		
    		for (Vec item : seznamVeci) {
    			if (item.getNazev().equals(nazev)) {
    				vybiranaVec = item;
    				seznamVeci.remove(item);
    				break;
    			}
    		}
    		
    		return vybiranaVec;
    }
    
    /**
     * Metoda zkontroluje, zdali se dana postava nachazi v prostoru
     * 
     * @param jmeno - jmeno postavy, kterou chceme nechat zkontrolovat
     * @return True/False podle toha, zdali je dana postava v prostoru ci nikoliv
     */
    public boolean obsahujePostavu(String jmeno) {
    		for (Postava osoba : seznamPostav) {
    			if (osoba.getJmeno().equals(jmeno)) {
    				return true;
    			}
    		}
    		
    		return false;
    }
    
    /**
     * Metoda slouzi k predani reference dane postavy
     * 
     * @param jmeno - jmeno postavy, kterou chceme predat
     * @return reference na Postavu
     */
    public Postava vyberPostavu(String jmeno) {
    		for (Postava osoba : seznamPostav) {
    			if (osoba.getJmeno().equals(jmeno)) {
    				return osoba;
    			}
    		}
    		
    		return null;
    }
    
    /**
     * Metoda vlozi postavu do dane mistnosti
     * 
     * @param postava - postava, kterou chceme vlozit
     */
    public void vlozPostavu(Postava postava) {
    		seznamPostav.add(postava);
    }
    
    /**
     * Metoda pro odebrani dotycne postavy z mistnosti
     * 
     * @param jmeno - jmeno osoby, kterou chceme odebrat
     */
    public void odeberPostavu(String jmeno) {
    		for (Postava osoba : seznamPostav) {
    			if (osoba.getJmeno().equals(jmeno)) {
    				seznamPostav.remove(osoba);
    				break;
    			}
    		}
    }
    
    
}
