package test.java.fr.univlille.iutinfo.r304.part1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.fr.univlille.iutinfo.r304.thermo.part1.model.ITemperature;
import main.java.fr.univlille.iutinfo.r304.thermo.part1.model.Thermogeekostat;
import main.java.fr.univlille.iutinfo.r304.thermo.part1.view.ITemperatureView;
import main.java.fr.univlille.iutinfo.r304.thermo.part1.view.TextView;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class TestP1Q3 {
	public static final double DELTA = 0.005;  // rounding to 1/100

	/* JFXPanel is a component that initializes JavaFX without needing a javafx.application.Application
	 * We need it to be able to create a view but we don't need the attribute after that
	 */
	@SuppressWarnings("unused")
	private JFXPanel fxPanel = new JFXPanel();

	protected ITemperature model;
	protected ITemperatureView view;

	@BeforeEach
	public void setUp() {
		view = null;
		/* runLater is needed to let JavaFX initialize the view in its own thread
		 */
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				model = new Thermogeekostat();
				view = new TextView((Thermogeekostat) model);
			}
		});

		/* Because of runLater, we must wait until the window is actually created
		 */
		while (view == null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				fail("Exception while setting up test");
			}
		}
	}

	@Test
	public void test_setting_model_value_should_set_displayed_value() {
		model.setTemperature(18.0);
		assertEquals(18.0, view.getDisplayedValue(), DELTA);
	}

	@Test
	public void test_increment_model_value_should_increment_displayed_value() {
		model.setTemperature(18.0);
		model.incrementTemperature();
		assertEquals(19.0, model.getTemperature(), DELTA);
	}

	@Test
	public void test_decrement_modele_value_should_decrement_displayed_value() {
		model.setTemperature(18.0);
		model.decrementTemperature();
		assertEquals(17.0, model.getTemperature(), DELTA);
	}

	@Test
	public void test_view_increment_value_should_increment_model_value() {
		model.setTemperature(18.0);
		view.incrementAction();
		assertEquals(19.0, model.getTemperature(), DELTA);
	}

	@Test
	public void test_view_decrement_value_should_decrement_model_value() {
		model.setTemperature(18.0);
		view.decrementAction();
		assertEquals(17.0, model.getTemperature(), DELTA);
	}

}
