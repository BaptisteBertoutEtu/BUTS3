package test.java.fr.univlille.iutinfo.r304.part2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.fr.univlille.iutinfo.r304.thermo.part2.model.Echelle;

public class TestP2Q1 {

	@Test
	public void test_Celsius_name_and_abbreviation() {
		assertEquals("Celsius", Echelle.CELSIUS.getName());
		assertEquals("C", Echelle.CELSIUS.getAbbrev());
	}

	@Test
	public void test_Fahrenheit_name_and_abbreviation() {
		assertEquals("Fahrenheit", Echelle.FAHRENHEIT.getName());
		assertEquals("F", Echelle.FAHRENHEIT.getAbbrev());
	}

	@Test
	public void test_Kelvin_name_and_abbreviation() {
		assertEquals("Kelvin", Echelle.KELVIN.getName());
		assertEquals("K", Echelle.KELVIN.getAbbrev());
	}

	@Test
	public void test_Rankine_name_and_abbreviation() {
		assertEquals("Rankine", Echelle.RANKINE.getName());
		assertEquals("Ra", Echelle.RANKINE.getAbbrev());
	}

	@Test
	public void test_Newton_name_and_abbreviation() {
		assertEquals("Newton", Echelle.NEWTON.getName());
		assertEquals("N", Echelle.NEWTON.getAbbrev());
	}

}
