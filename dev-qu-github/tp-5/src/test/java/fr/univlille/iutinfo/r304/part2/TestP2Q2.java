package test.java.fr.univlille.iutinfo.r304.part2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.fr.univlille.iutinfo.r304.thermo.part2.model.Echelle;

public class TestP2Q2 {
	public static final double DELTA = 0.005;  // rounding to 1/100

	@Test
	public void test_Celius_from_Kelvin() {
		assertEquals(-273.15, Echelle.CELSIUS.fromKelvin(0.0), DELTA);
		assertEquals(26.85, Echelle.CELSIUS.fromKelvin(300.0), DELTA);
	}

	@Test
	public void test_Fahrenheit_from_Kelvin() {
		assertEquals(-459.67, Echelle.FAHRENHEIT.fromKelvin(0.0), DELTA);
		assertEquals(80.33, Echelle.FAHRENHEIT.fromKelvin(300.0), DELTA);
	}

	@Test
	public void test_Kelvin_from_kelvin() {
		assertEquals(0.0, Echelle.KELVIN.fromKelvin(0.0), DELTA);
		assertEquals(300.0, Echelle.KELVIN.fromKelvin(300.0), DELTA);
	}

	@Test
	public void test_Rankine_from_kelvin() {
		assertEquals(0.0, Echelle.RANKINE.fromKelvin(0.0), DELTA);
		assertEquals(-48.33, Echelle.RANKINE.fromKelvin(-300.0), DELTA);
	}

	@Test
	public void test_Newton_from_kelvin() {
		assertEquals(-90.14, Echelle.NEWTON.fromKelvin(0.0), DELTA);
		assertEquals(8.86, Echelle.NEWTON.fromKelvin(300.0), DELTA);
	}

	@Test
	public void test_Celius_to_Kelvin() {
		assertEquals(273.15, Echelle.CELSIUS.toKelvin(0.0), DELTA);
		assertEquals(373.15, Echelle.CELSIUS.toKelvin(100.0), DELTA);
	}

	@Test
	public void test_Fahrenheit_to_Kelvin() {
		assertEquals(255.37, Echelle.FAHRENHEIT.toKelvin(0.0), DELTA);
		assertEquals(310.93, Echelle.FAHRENHEIT.toKelvin(100.0), DELTA);
	}

	@Test
	public void test_Kelvin_to_kelvin() {
		assertEquals(0.0, Echelle.KELVIN.toKelvin(0.0), DELTA);
		assertEquals(300.0, Echelle.KELVIN.toKelvin(300.0), DELTA);
	}

	@Test
	public void test_Rankine_to_kelvin() {
		assertEquals(0.0, Echelle.RANKINE.toKelvin(0.0), DELTA);
		assertEquals(55.56, Echelle.RANKINE.toKelvin(100.0), DELTA);
	}

	@Test
	public void test_Newton_to_kelvin() {
		assertEquals(273.15, Echelle.NEWTON.toKelvin(0.0), DELTA);
		assertEquals(576.18, Echelle.NEWTON.toKelvin(100.0), DELTA);
	}

}
