package main.java.fr.univlille.roliste.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.fr.univlille.roliste.model.De;

public class AllDeView extends Stage{
    private List<DeView> list;
    
    public AllDeView(Collection<De> allDe){
        this.list = new ArrayList<DeView>();
        for (De de : allDe) {
            this.list.add(new DeView(de));
        }
        this.start();
    }

    public void lancerAll(){
        for (DeView deView : list) {
            deView.lancer();
        }
    }

    public void start(){
		Button lancerAll = new Button("Lancer tous les dés ");		

        VBox v = new VBox();
        HBox h1 = new HBox();
        HBox h2 = new HBox();


        h1.getChildren().addAll(list);
        h2.getChildren().add(lancerAll);
        v.getChildren().addAll(h1,h2);
        Pane p = new Pane();
        p.getChildren().add(v);
        Scene s = new Scene(p,400,100);

        lancerAll.setOnAction(e -> {
            lancerAll();
        });

        s.setOnKeyPressed(e -> {
            if(e.getCode().equals(KeyCode.D)) lancerAll();
        });

        this.setTitle("All");
        this.setScene(s);
        this.show();
	}

    
}
