package com.github.petrpanek.AdventuraGUI.uiText;

import com.github.petrpanek.AdventuraGUI.logika.IHra;
import com.github.petrpanek.AdventuraGUI.logika.Hra;
import com.github.petrpanek.AdventuraGUI.logika.Vec;
import com.github.petrpanek.AdventuraGUI.logika.Prostor;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

/**
 * Class HomeController
 * 
 * Trida obsluhujici graficke zmeny aplikace  
 * 
 * @author Petr Panek
 */
public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ListView<Object> seznamVeciMistnost;
	@FXML private ListView<Object> seznamVeciBatoh;
	@FXML private ListView<Prostor> seznamVychodu;
	@FXML private ImageView postava;
	
	private ObservableList<Object> veciMistnost = FXCollections.observableArrayList();
	private ObservableList<Object> veciBatoh = FXCollections.observableArrayList();
	private ObservableList<Prostor> vychody = FXCollections.observableArrayList();
	
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
		vstupniText.setEditable(true);
		vystup.setEditable(false);
		this.hra = hra;
		
		vycistiSeznamy();
		
		vychody.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		
		seznamVeciMistnost.setItems(veciMistnost);
		seznamVeciBatoh.setItems(veciBatoh);
		seznamVychodu.setItems(vychody);
		
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
		
		postava.setX(hra.getHerniPlan().getAktualniProstor().getX());
		postava.setY(hra.getHerniPlan().getAktualniProstor().getY());
		
		vychody.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		
		Collection<Vec> obsahBatohu = hra.getHerniPlan().getBatoh().getVeci();
		for (Vec item : obsahBatohu) {
			ImageView picture = new ImageView(new Image(getClass().getResourceAsStream("/resources/" + item.getNazevObrazku()), 100, 100, true, false));
			veciBatoh.add(picture);
		}
		
		Collection<Vec> obsahMistnosti = hra.getHerniPlan().getAktualniProstor().getVeci();
		for (Vec item : obsahMistnosti) {
			ImageView picture = new ImageView(new Image(getClass().getResourceAsStream("/resources/" + item.getNazevObrazku()), 100, 100, true, false));
			veciMistnost.add(picture);
		}
	}
	
	/**
	 * Metoda pro vycisteni seznamu veci
	 */
	private void vycistiSeznamy() {
		veciMistnost.clear();
		veciBatoh.clear();
		vychody.clear();
	}
	
	/**
	 * Metoda pro vytvoreni nove herni instance
	 */
	@FXML public void newGame() {
		inicializuj(new Hra());
	}
	
	/**
	 * Metoda pro ukonceni stavajici hry
	 */
	@FXML public void endGame() {
		vstupniText.setText("konec");
		odesliPrikaz();
	}
	
	/**
	 * Metoda pro zobrazeni napovedy
	 */
	@FXML public void help() {
		Stage help = new Stage();
		help.setTitle("Nápověda");
		
		WebView webView = new WebView();
		webView.getEngine().load(getClass().getResource("/resources/napoveda.html").toExternalForm());
        
        help.setScene(new Scene(webView, 1200, 850));
        help.show();
	}
 
}
