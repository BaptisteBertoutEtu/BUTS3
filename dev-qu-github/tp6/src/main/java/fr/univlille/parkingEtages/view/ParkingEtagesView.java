package main.java.fr.univlille.parkingEtages.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.fr.univlille.parking.model.Parking;


public class ParkingEtagesView extends Stage{
    private Map<Integer,ParkingView> map;
    private int currentStage;
    private ParkingView currentParking;
    private HBox top = new HBox();
    private HBox mid = new HBox();
    private HBox bottom = new HBox();
    private Button plus = new Button("->");
    private Button moins = new Button("<-");

    public ParkingEtagesView(Collection<Parking> etages){
        map = new HashMap<Integer,ParkingView>();
        int i = 0;
        for (Parking p : etages) {
            this.map.put(i, new ParkingView(p));
            i++;
        }
        this.currentStage = 0;
        this.currentParking = this.map.get(0);
        this.start();
    }

    private void changeEtage(){
        top.getChildren().clear();
        mid.getChildren().clear();
        bottom.getChildren().clear();
        top.getChildren().add(currentParking.getNameEtage());
        mid.getChildren().add(currentParking);
        bottom.getChildren().addAll(moins,plus);
    }

    private void changeEtagePlus(){
        this.changeCurrentStage(this.currentStage+1);
        changeCurrentParking();
        this.changeEtage();
    }

    private void changeEtageMoins(){
        this.changeCurrentStage(this.currentStage-1);
        changeCurrentParking();
        this.changeEtage();
    }

    private void changeCurrentStage(int i){
        if(i >= 0 && i < map.size()-1) this.currentStage=i;
    }

    private void changeCurrentParking(){
        this.currentParking = this.map.get(this.currentStage);
    }


    public void start(){
        VBox v = new VBox();

        Pane p =new Pane();
        this.changeEtage();
        v.getChildren().addAll(top,mid,bottom);

        p.getChildren().add(v);

        Scene s = new Scene(p,500,200);
        this.setTitle("Parking "+currentParking.getNameEtage());
        this.setScene(s);
        this.show();

        plus.setOnAction(e -> {
            this.changeEtagePlus();
        });

        moins.setOnAction(e -> {
            this.changeEtageMoins();
        });

    }

}
