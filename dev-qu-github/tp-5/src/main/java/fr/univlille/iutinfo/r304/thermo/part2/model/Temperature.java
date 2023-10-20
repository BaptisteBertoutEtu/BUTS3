package main.java.fr.univlille.iutinfo.r304.thermo.part2.model;

import main.java.fr.univlille.iutinfo.r304.thermo.part1.model.ITemperature;
import main.java.fr.univlille.iutinfo.r304.utils.ConnectableProperty;
import main.java.fr.univlille.iutinfo.r304.utils.Subject;

public class Temperature extends ConnectableProperty implements ITemperature{

	private double temperature;
	private Echelle echelle;

	public Temperature(Echelle celsius) {
		this.echelle=celsius;
		this.temperature = 0.0;
		this.setValue(0.0);
	}

	public Echelle getEchelle() {
		return this.echelle;
	}

	@Override
	public void setTemperature(double d) {
		this.temperature = d;
		super.setValue(this.echelle.toKelvin(d));
	}

	@Override
	public Double getTemperature() {
		return this.temperature;
	}

	@Override
	public void incrementTemperature() {
		this.setTemperature((double)this.getValue()+1);
	}

	@Override
	public void decrementTemperature() {
		this.setTemperature((double)this.getValue()-1);
	}

	@Override
	public void update(Subject other, Object data) {
		this.setValue(this.echelle.fromKelvin((double)data));
		this.temperature=(double)this.getValue();
	}

}
