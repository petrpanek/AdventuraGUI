package com.github.petrpanek.AdventuraGUI.uiText;

import com.github.petrpanek.AdventuraGUI.logika.IHra;
import com.github.petrpanek.AdventuraGUI.logika.Hra;
import com.github.petrpanek.AdventuraGUI.logika.Vec;
import com.github.petrpanek.AdventuraGUI.logika.Prostor;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ListView<Vec> seznamVeciMistnost;
	@FXML private ListView<Vec> seznamVeciBatoh;
	@FXML private ListView<Prostor> seznamVychodu;
	@FXML private ImageView postava;
	
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
		
		vycistiSeznamy();
		
		seznamVeciMistnost.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		
		postava.setX(hra.getHerniPlan().getAktualniProstor().getX());
		postava.setY(hra.getHerniPlan().getAktualniProstor().getY());
		
		hra.getHerniPlan().addObserver(this);
		hra.getHerniPlan().getBatoh().addObserver(this);
		hra.getHerniPlan().getAktualniProstor().addObserver(this);
	}

	/**
	 * Metoda aktualizaci rozhrani po provedenych zmenach ve hre
	 * 
	 * @param o, arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		vycistiSeznamy();
		
		seznamVeciBatoh.getItems().addAll(hra.getHerniPlan().getBatoh().getVeci());
		seznamVeciMistnost.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		
		postava.setX(hra.getHerniPlan().getAktualniProstor().getX());
		postava.setY(hra.getHerniPlan().getAktualniProstor().getY());
	}
	
	/**
	 * Metoda pro vycisteni seznamu v rozhrani
	 */
	private void vycistiSeznamy() {
		seznamVeciMistnost.getItems().clear();
		seznamVychodu.getItems().clear();
		seznamVeciBatoh.getItems().clear();
	}
	
	/**
	 * Metoda pro vytvoreni nove herni instance
	 */
	@FXML public void newGame() {
		inicializuj(new Hra());
	}
	
	/**
	 * Metoda pro zobrazeni napovedy
	 */
	@FXML public void help() {
		Stage help = new Stage();
		help.setTitle("Nápověda");
		
		WebView webView = new WebView();
		webView.getEngine().load(com.github.petrpanek.AdventuraGUI.uiText.Application.class.getResource("/sources/napoveda.html").toExternalForm());
        
        help.setScene(new Scene(webView, 1200, 850));
        help.show();
	}
 
}
