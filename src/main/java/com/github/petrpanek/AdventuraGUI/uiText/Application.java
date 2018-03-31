package com.github.petrpanek.AdventuraGUI.uiText;

import com.github.petrpanek.AdventuraGUI.logika.Hra;
import com.github.petrpanek.AdventuraGUI.logika.IHra;
import com.github.petrpanek.AdventuraGUI.uiText.HomeController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
	
	/**
	 * Spouštěcí metoda pro aplikaci
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Neplatný parametr");
            }
        }
	}

	
	/**
	 * Metoda, ve které se konstruuje okno, kontroler a hra,
	 * která se předává kontroleru
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
	       
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("Home.fxml"));
	        Parent root = loader.load();
	        
	        HomeController controller = loader.getController();
	        controller.inicializuj(new Hra());
	        
	        primaryStage.setTitle("Adventura");
	        primaryStage.setScene(new Scene(root));
	        primaryStage.show();
	}

}