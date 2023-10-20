package main.java.fr.univlille.parkingEtages.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import main.java.fr.univlille.parking.model.Parking;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;

public class ParkingView extends BorderPane implements Observer{
    private static int nb = 0;
    private Parking model;
    private TextField tf;
    private TextField t;
    private TextField nameEtage;

    public ParkingView(Parking model){
        this.model = model;
		this.tf = new TextField("");
        this.t = new TextField();
		this.model.attach(this);
        tf.setDisable(true);
        t.setDisable(true);
        nameEtage = new TextField("Etage "+ParkingView.nb);
        nameEtage.setDisable(true);
        ParkingView.nb++;
		this.start();
    }

    public TextField getNameEtage(){
        return this.nameEtage;
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
        
        tf.setText(""+this.model.getNombrePlaceLibre());
        
        this.setRight(sortie2);
        this.setLeft(sortie1);
        this.setBottom(entree);
        this.setCenter(tf);
        this.setTop(t);
        
        sortie1.setOnAction(e -> {
            this.model.ajouterPlace();
        });
        
        sortie2.setOnAction(e -> {
            this.model.ajouterPlace();
        });
        
        entree.setOnAction(e -> {
            this.model.enleverPlace();
        });
        
	}
}
