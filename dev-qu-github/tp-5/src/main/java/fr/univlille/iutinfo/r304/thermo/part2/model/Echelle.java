package main.java.fr.univlille.iutinfo.r304.thermo.part2.model;

public enum Echelle {
	CELSIUS("Celsius","C"), 
	FAHRENHEIT("Fahrenheit","F"), 
	KELVIN("Kelvin","K"),
	RANKINE("Rankine","Ra"), 
	NEWTON("Newton","N");

	private final String NAME;
	private final String ABREV;

	private Echelle(String name, String abrev){
		this.NAME = name;
		this.ABREV = abrev;
	}

	public String getName() {
		return this.NAME;
	}

	public String getAbbrev() {
		return this.ABREV;
	}

	public double fromKelvin(double d) {
		switch (this.getName()) {
			case "Celsius":
				return kToC(d);
			case "Fahrenheit":
				return kToF(d);
			case "Rankine":
				return kToR(d);
			case "Newton":
				return kToN(d);
			default:
				return d;
		}
	}

	public double toKelvin(double d) {
		switch (this.getName()) {
			case "Celsius":
				return cToK(d);
			case "Fahrenheit":
				return fToK(d);
			case "Rankine":
				return rToK(d);
			case "Newton":
				return nToK(d);
			default:
				return d;
		}
	}

	private double nToK(double d) {
		return (d/0.33) + 273.15;
	}

	private double rToK(double d) {
		return (5.0/9.0)*d;
	}

	private double fToK(double d) {
		return (5.0/9.0)*(d + 459.67);
	}

	private double cToK(double d) {
		return (d + 273.15);
	}

	private double kToC(double d){
		return (d - 273.15);
	}

	private double kToF(double d){
		return ((9.0/5.0)*d) - 459.67;
	}

	private double kToN(double d){
		return (d - 273.15)* 0.33;
	}

	private double kToR(double d){
		return (9.0/5.0)*d;
	}


}
