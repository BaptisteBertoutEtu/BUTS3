package main.java.fr.univlille.iutinfo.r304;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXSimpleExample extends Application {

    @Override
    public void start(Stage stage) {
        Label l = new Label(welcomeMessage());
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

	private String welcomeMessage() {
		String javaVersion = System.getProperty("java.version");
		String javafxVersion = System.getProperty("javafx.version");
		
		return
			"Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".\n" +
			"             (and powered by maven)\n";
	}

    public static void main(String[] args) {
        launch();
    }

}
