package main.java.fr.univlille.iutinfo.r304.thermo.part1.model;

import main.java.fr.univlille.iutinfo.r304.utils.Subject;

public class Thermogeekostat extends Subject implements ITemperature {

	private Double temperature;

	public Thermogeekostat(){
		this.temperature = 0.0;
	}

	@Override
	public void setTemperature(double d) {
		this.temperature = d;
		super.notifyObservers(this.temperature);
	}

	@Override
	public Double getTemperature() {
		return this.temperature;
	}

	@Override
	public void incrementTemperature() {
		this.setTemperature(temperature+1.0);
	}

	@Override
	public void decrementTemperature() {
		this.setTemperature(temperature-1.0);
	}

}
