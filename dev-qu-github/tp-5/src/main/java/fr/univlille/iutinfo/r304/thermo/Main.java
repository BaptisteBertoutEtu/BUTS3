package main.java.fr.univlille.iutinfo.r304.thermo;

import main.java.fr.univlille.iutinfo.r304.thermo.part1.model.Thermogeekostat;
import main.java.fr.univlille.iutinfo.r304.thermo.part1.view.SliderView;
import main.java.fr.univlille.iutinfo.r304.thermo.part1.view.TextView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Thermogeekostat thermo = new Thermogeekostat();
		new TextView(thermo);
		new TextView(thermo);
		new SliderView(thermo);
	}

}
