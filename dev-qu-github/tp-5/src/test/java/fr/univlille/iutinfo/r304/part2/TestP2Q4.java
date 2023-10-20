package test.java.fr.univlille.iutinfo.r304.part2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.fr.univlille.iutinfo.r304.thermo.part2.model.Echelle;
import main.java.fr.univlille.iutinfo.r304.thermo.part2.model.Temperature;

public class TestP2Q4 {
	public static final double DELTA = 0.005;  // rounding to 1/100

	protected Temperature tempC;
	protected Temperature tempF;
	protected Temperature tempRa;
	protected Temperature tempN;

	@BeforeEach
	public void setup() {
		tempC = new Temperature(Echelle.CELSIUS);
		tempF = new Temperature(Echelle.FAHRENHEIT);
		tempRa= new Temperature(Echelle.RANKINE);
		tempN = new Temperature(Echelle.NEWTON);
	}

	@Test
	public void test_temperature_Celius_to_Fahrenheit() {
		tempC.setTemperature(0.0);
		tempF.biconnectTo(tempC);
		assertEquals(32.0, tempF.getTemperature(), DELTA);
	}

	@Test
	public void test_temperature_Newton_to_Rankine_and_Celsius() {
		tempN.setTemperature(0.0);
		tempRa.biconnectTo(tempN);
		tempC.biconnectTo(tempN);
		assertEquals(491.67, tempRa.getTemperature(), DELTA);
		assertEquals(0.0, tempC.getTemperature(), DELTA);
	}

	@Test
	public void test_temperature_Fahrenheit_to_Celsius_to_Rankine_in_sequence() {
		tempF.setTemperature(0.0);
		tempC.biconnectTo(tempF);
		tempRa.biconnectTo(tempC);
		assertEquals(-17.78, tempC.getTemperature(), DELTA);
		assertEquals(459.67, tempRa.getTemperature(), DELTA);
	}

}
