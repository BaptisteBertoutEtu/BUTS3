package fr.univlille.felixthecat;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
    	stage.setTitle("Felix The Cat");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        new Felix().drawOn(gc);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();    }

    public static void main(String[] args) {
        launch();
    }

}