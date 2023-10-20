package main.java.fr.univlille.roliste;

import javafx.stage.Stage;
import main.java.fr.univlille.roliste.model.De;
import main.java.fr.univlille.roliste.view.AllDeView;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;

public class Main extends Application{
    public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		De d1 = new De(6);
		De d2 = new De(10);
		De d3 = new De(3);
		List<De> lsit = new ArrayList<De>();
		lsit.add(d1);
		lsit.add(d2);
		lsit.add(d3);
		new AllDeView(lsit);
	}
}
