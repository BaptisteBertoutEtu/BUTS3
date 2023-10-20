package main.java.fr.univlille.roliste.view;

import main.java.fr.univlille.roliste.model.De;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;

public class DeView extends Pane implements Observer{
	
	private De model;
	private TextField tf;


	public DeView(De model) {
		this.model = model;
		this.tf = new TextField("");
		this.model.attach(this);
        tf.setDisable(true);
		this.start();
	}
    @Override
	public void update(Subject subj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Subject subj, Object data) {
		this.tf.setText(""+data);
	}

    public void lancer(){
        this.model.lancer();
    }


	public void start(){
		Button lancer = new Button("Lancer dé "+this.model.getFaces());		

        VBox vBox = new VBox();
        HBox hboxButton = new HBox();
        HBox hboxText = new HBox();
        hboxButton.getChildren().add(lancer);
        hboxText.getChildren().add(tf);

		vBox.getChildren().add(hboxButton);
        vBox.getChildren().add(hboxText);
        this.getChildren().add(vBox);

        lancer.setOnAction(e -> {
            this.model.lancer();
        });
	}


}
