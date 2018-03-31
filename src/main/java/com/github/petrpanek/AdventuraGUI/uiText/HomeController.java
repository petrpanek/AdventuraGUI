package com.github.petrpanek.AdventuraGUI.uiText;

import com.github.petrpanek.AdventuraGUI.logika.IHra;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class HomeController extends GridPane {
	
	@FXML private TextField vstupniText;
	
	/**
	 * Metoda cte prikaz ze vstupniho textoveho pole a zpracuje ho
	 * 
	 */
	@FXML public void odesliPrikaz() {
		System.out.println(vstupniText.getText());
		vstupniText.setText("");
	}
	
	/**
	 * Metoda bude slouzit pro predani objektu se spustenou hrou
	 * kotroleru a zobrazi stav hry v grafice.
	 * 
	 * @param objekt spustene hry
	 */
	public void inicializuj(IHra hra) {
		
	}
 
}
