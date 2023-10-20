package main.java.fr.univlille.parking;

import javafx.stage.Stage;
import main.java.fr.univlille.parking.model.Parking;
import main.java.fr.univlille.parking.view.ParkingView;

import javafx.application.Application;

public class Main extends Application{
    public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Parking p = new Parking(10);
		new ParkingView(p);
	}
}
