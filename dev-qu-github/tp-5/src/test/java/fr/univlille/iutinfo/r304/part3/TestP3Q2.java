package test.java.fr.univlille.iutinfo.r304.part3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.fr.univlille.iutinfo.r304.thermo.part1.view.ITemperatureView;
import main.java.fr.univlille.iutinfo.r304.thermo.part2.model.Echelle;
import main.java.fr.univlille.iutinfo.r304.thermo.part2.model.Temperature;
import main.java.fr.univlille.iutinfo.r304.thermo.part3.view.TextView;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class TestP3Q2 {
	public static final double DELTA = 0.005;  // rounding to 1/100

	/* JFXPanel is a component that initializes JavaFX without needing a javafx.application.Application
	 * We need it to be able to create a IVueThermogeekostat but we don't need the attribute after that
	 */
	@SuppressWarnings("unused")
	private JFXPanel fxPanel = new JFXPanel();

	protected Temperature modelC;
	protected ITemperatureView viewC;

	protected Temperature modelF;
	protected ITemperatureView viewF;

	@BeforeEach
	public void setUp() {
		modelC = new Temperature(Echelle.CELSIUS);
		modelC.setTemperature(18.0);
		modelF = new Temperature(Echelle.FAHRENHEIT);
		modelF.biconnectTo(modelC);

		viewC = null;
		viewF = null;
		/* runLater is needed to let JavaFX initialize the views in its own thread
		 */
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				viewC = new TextView(modelC);
				viewF = new TextView(modelF);
			}
		});

		/* Because of runLater, we must wait until the windows are actually created
		 */
		while ( (viewC == null) || (viewF == null) ) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				fail("Exception while setting up test");
			}
		}
	}

	@Test
	public void test_setting_celsius_model_value_should_set_both_displayed_values() {
		modelC.setTemperature(18.0);
		assertEquals(18.0, viewC.getDisplayedValue(), DELTA);
		assertEquals(64.4, viewF.getDisplayedValue(), DELTA);
	}

	@Test
	public void test_view_fahrenheit_increment_should_increment_both_model_values() {
		modelC.setTemperature(18.0);
		viewF.incrementAction();
		assertEquals(65.4, modelF.getTemperature(), DELTA);
		assertEquals(18.56, modelC.getTemperature(), DELTA);
	}

	@Test
	public void test_view_celsius_decrement_should_decrement_both_model_values() {
		modelC.setTemperature(18.0);
		viewC.decrementAction();
		assertEquals(17.0, modelC.getTemperature(), DELTA);
		assertEquals(62.6, modelF.getTemperature(), DELTA);
	}

}
