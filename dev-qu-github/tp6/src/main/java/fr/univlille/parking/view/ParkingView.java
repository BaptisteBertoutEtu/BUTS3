package main.java.fr.univlille.parking.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.fr.univlille.parking.model.Parking;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;

public class ParkingView extends Stage implements Observer{
    private Parking model;
    private TextField tf;
    private TextField t;

    public ParkingView(Parking model){
        this.model = model;
		this.tf = new TextField("");
        this.t = new TextField();
		this.model.attach(this);
        tf.setDisable(true);
        t.setDisable(true);
		this.start();
    }

    @Override
    public void update(Subject subj) {
        if(this.model.getNombrePlaceLibre() == 0) this.t.setText("Le parking est plein");
        else this.t.setText("");
    }

    @Override
    public void update(Subject subj, Object data) {
        tf.setText(""+this.model.getNombrePlaceLibre());
    }

    public void start(){
		Button sortie1 = new Button("Sortie 1");
        Button sortie2 = new Button("Sortie 2");
        Button entree = new Button("Entree");
        
        BorderPane bp = new BorderPane();
        tf.setText(""+this.model.getNombrePlaceLibre());
        
        bp.setRight(sortie2);
        bp.setLeft(sortie1);
        bp.setBottom(entree);
        bp.setCenter(tf);
        bp.setTop(t);
        
        sortie1.setOnAction(e -> {
            this.model.ajouterPlace();
        });
        
        sortie2.setOnAction(e -> {
            this.model.ajouterPlace();
        });
        
        entree.setOnAction(e -> {
            this.model.enleverPlace();
        });
        
        Scene s = new Scene(bp, 400, 100);
        
        this.setScene(s);
        this.setTitle("Parking");
        this.show();
	}
}
