package test.java.fr.univlille.iutinfo.r304.part2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.fr.univlille.iutinfo.r304.thermo.part2.model.Echelle;
import main.java.fr.univlille.iutinfo.r304.thermo.part2.model.Temperature;

public class TestP2Q3 {
	public static final double DELTA = 0.005;  // rounding to 1/100

	@Test
	public void test_temperature_Celius() {
		Temperature temp = new Temperature(Echelle.CELSIUS);
		assertEquals(Echelle.CELSIUS, temp.getEchelle());
		
		temp.setTemperature(0.0);
		assertEquals(0.0, temp.getTemperature(), DELTA);
		assertEquals(273.15, temp.getEchelle().toKelvin( temp.getTemperature()), DELTA);
		
		temp.incrementTemperature();
		assertEquals(1.0, temp.getTemperature(), DELTA);
		
		temp.decrementTemperature();
		assertEquals(0.0, temp.getTemperature(), DELTA);
	}

	@Test
	public void test_temperature_Fahrenheit() {
		Temperature temp = new Temperature(Echelle.FAHRENHEIT);
		assertEquals(Echelle.FAHRENHEIT, temp.getEchelle());
		
		temp.setTemperature(0.0);
		assertEquals(0.0, temp.getTemperature(), DELTA);
		assertEquals(255.37, temp.getEchelle().toKelvin( temp.getTemperature()), DELTA);
		
		temp.incrementTemperature();
		assertEquals(1.0, temp.getTemperature(), DELTA);
		
		temp.decrementTemperature();
		assertEquals(0.0, temp.getTemperature(), DELTA);
	}

	@Test
	public void test_temperature_kelvin() {
		Temperature temp = new Temperature(Echelle.KELVIN);
		assertEquals(Echelle.KELVIN, temp.getEchelle());
		
		temp.setTemperature(0.0);
		assertEquals(0.0, temp.getTemperature(), DELTA);
		assertEquals(0.0, temp.getEchelle().toKelvin( temp.getTemperature()), DELTA);
		
		temp.incrementTemperature();
		assertEquals(1.0, temp.getTemperature(), DELTA);
		
		temp.decrementTemperature();
		assertEquals(0.0, temp.getTemperature(), DELTA);
	}

	@Test
	public void test_temperature_Rankine() {
		Temperature temp = new Temperature(Echelle.RANKINE);
		assertEquals(Echelle.RANKINE, temp.getEchelle());
		
		temp.setTemperature(0.0);
		assertEquals(0.0, temp.getTemperature(), DELTA);
		assertEquals(0.0, temp.getEchelle().toKelvin( temp.getTemperature()), DELTA);
		
		temp.incrementTemperature();
		assertEquals(1.0, temp.getTemperature(), DELTA);
		
		temp.decrementTemperature();
		assertEquals(0.0, temp.getTemperature(), DELTA);
	}

	@Test
	public void test_temperature_Newton() {
		Temperature temp = new Temperature(Echelle.NEWTON);
		assertEquals(Echelle.NEWTON, temp.getEchelle());
		
		temp.setTemperature(0.0);
		assertEquals(0.0, temp.getTemperature(), DELTA);
		assertEquals(273.15, temp.getEchelle().toKelvin( temp.getTemperature()), DELTA);
		
		temp.incrementTemperature();
		assertEquals(1.0, temp.getTemperature(), DELTA);
		
		temp.decrementTemperature();
		assertEquals(0.0, temp.getTemperature(), DELTA);
	}

}
