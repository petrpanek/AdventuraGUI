package com.github.petrpanek.AdventuraGUI.uiText;

import com.github.petrpanek.AdventuraGUI.logika.IHra;
import com.github.petrpanek.AdventuraGUI.logika.Vec;
import com.github.petrpanek.AdventuraGUI.logika.Prostor;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ListView<Vec> seznamVeciMistnost;
	@FXML private ListView<Vec> seznamVeciBatoh;
	@FXML private ListView<Prostor> seznamVychodu;
	
	private IHra hra;
	
	/**
	 * Metoda cte prikaz ze vstupniho textoveho pole a zpracuje ho
	 * 
	 */
	@FXML public void odesliPrikaz() {
		String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
		vystup.appendText("\n----------\n" + vstupniText.getText() + "\n----------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
		
		hra.getHerniPlan().getAktualniProstor().addObserver(this);
		
		if (hra.konecHry()) {
			vystup.appendText("\n----------\nKonec hry!\n----------\n");
			vstupniText.setEditable(false);
		}
	}
	
	/**
	 * Metoda bude slouzit pro predani objektu se spustenou hrou
	 * kotroleru a zobrazi stav hry v grafice.
	 * 
	 * @param objekt spustene hry
	 */
	public void inicializuj(IHra hra) {
		vystup.setText(hra.vratUvitani());
		vystup.setEditable(false);
		this.hra = hra;
		
		seznamVeciMistnost.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		
		hra.getHerniPlan().addObserver(this);
		hra.getHerniPlan().getBatoh().addObserver(this);
		hra.getHerniPlan().getAktualniProstor().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		seznamVeciMistnost.getItems().clear();
		seznamVychodu.getItems().clear();
		seznamVeciBatoh.getItems().clear();
		
		seznamVeciBatoh.getItems().addAll(hra.getHerniPlan().getBatoh().getVeci());
		seznamVeciMistnost.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		
		
	}
 
}
