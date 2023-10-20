package main.java.fr.univlille.iutinfo.r304.thermo.part1.view;

import main.java.fr.univlille.iutinfo.r304.thermo.part1.model.Thermogeekostat;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import main.java.fr.univlille.iutinfo.r304.utils.Observer;
import main.java.fr.univlille.iutinfo.r304.utils.Subject;

public class TextView extends Stage implements ITemperatureView, Observer{
	
	private Thermogeekostat model;
	private TextField tf;


	public TextView(Thermogeekostat model) {
		this.model = model;
		this.tf = new TextField(""+this.getDisplayedValue());
		this.model.attach(this);
		this.start();
	}

	@Override
	public void update(Subject subj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Subject subj, Object data) {
		this.tf.setText(""+this.getDisplayedValue());
	}

	@Override
	public double getDisplayedValue() {
		return this.model.getTemperature();
	}

	@Override
	public void incrementAction() {
		this.model.incrementTemperature();
	}

	@Override
	public void decrementAction() {
		this.model.decrementTemperature();
	}

	public void start(){
		Button plus = new Button("+");
		Button moins = new Button("-");
		
		
		
		plus.setOnAction(e -> {
			this.incrementAction();
		});
		
		moins.setOnAction(e -> {
			this.decrementAction();
		});

		tf.setOnAction(e -> {
			String value = tf.getText();
			this.model.setTemperature( Double.parseDouble(value));
		});

		
		BorderPane p = new BorderPane();
		p.setLeft(moins);
		p.setRight(plus);
		p.setCenter(tf);
		Scene s = new Scene(p,300,100);

		this.setTitle("test exo1");
		this.setScene(s);
		this.show();
	}


}
