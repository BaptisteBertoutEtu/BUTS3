package main.java.fr.univlille.parkingEtages;

import javafx.stage.Stage;
import main.java.fr.univlille.parking.model.Parking;
import main.java.fr.univlille.parkingEtages.view.ParkingEtagesView;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;

public class Main extends Application{
    public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Parking p0 = new Parking(20);
		Parking p1 = new Parking(19);
		Parking p2 = new Parking(18);
		Parking p3 = new Parking(17);
		Parking p4 = new Parking(16);
		List<Parking> list = new ArrayList<Parking>();
		list.add(p0);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		new ParkingEtagesView(list);
	}
}
