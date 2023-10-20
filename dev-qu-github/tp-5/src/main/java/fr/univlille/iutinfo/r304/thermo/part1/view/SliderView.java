package main.java.fr.univlille.iutinfo.r304.thermo.part1.view;

import main.java.fr.univlille.iutinfo.r304.thermo.part1.model.Thermogeekostat;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import main.java.fr.univlille.iutinfo.r304.utils.Observer;
import main.java.fr.univlille.iutinfo.r304.utils.Subject;

public class SliderView extends Stage implements ITemperatureView, Observer{
	
	private Thermogeekostat model;
	private Slider sl;


	public SliderView(Thermogeekostat model) {
		this.model = model;
		this.sl = new Slider(-10, 40, this.getDisplayedValue());
		this.model.attach(this);
		this.start();
	}

	@Override
	public void update(Subject subj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Subject subj, Object data) {
		this.sl.setValue((Double)data);
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

		plus.setOnAction(e->{
			this.incrementAction();
		});

		moins.setOnAction(e->{
			this.decrementAction();
		});

		sl.setOnDragDetected(e -> {
			Double value = sl.getValue();
			this.model.setTemperature(value);
		});

		this.sl.setMinorTickCount(5);
		this.sl.setShowTickMarks(true);
		this.sl.setShowTickLabels(true);

		
		BorderPane p = new BorderPane();
		p.setTop(plus);
		p.setCenter(sl);
		p.setBottom(moins);
		Scene s = new Scene(p,300,100);

		this.setTitle("test exo1");
		this.setScene(s);
		this.show();
	}


}
