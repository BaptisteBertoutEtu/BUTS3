package test.java.fr.univlille.iut.r304.tp4.q2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.fr.univlille.iut.r304.tp4.q2.ConnectableProperty;

public class TestQ2 {
    protected ConnectableProperty p1;
    protected ConnectableProperty p2;

    @BeforeEach
    public void setup() {
        p1 = new ConnectableProperty();
        p2 = new ConnectableProperty();
    }

    @Test
    public void test_connecting_changes_value_of_observer() {
        p1.setValue(42);
        p2.connectTo(p1);
        assertEquals(42, p2.getValue());
    }

    @Test
    public void test_connectTo_transmits_value_to_connected() {
        p1.connectTo(p2);
        p2.setValue(5);

        assertEquals(5, p1.getValue());
        assertEquals(5, p2.getValue());
    }

    @Test
    public void test_connectTo_does_not_transmit_value_from_connected() {
    	p2.setValue(42);
        p1.connectTo(p2);
        assertEquals(42, p2.getValue());
        
        p1.setValue(5);
        assertEquals(42, p2.getValue());
    }

    @Test
    public void test_biconnectTo_transmits_value_both_way() {
        p1.biconnectTo(p2);
        p1.setValue(42);
        
        assertEquals(42, p1.getValue());
        assertEquals(42, p2.getValue());

        p2.setValue(5);
        assertEquals(5, p1.getValue());
        assertEquals(5, p2.getValue());

        p1.setValue(42);
        assertEquals(42, p1.getValue());
        assertEquals(42, p2.getValue());
    }

    @Test
    public void test_circular_connects_do_not_loop() {
    	ConnectableProperty p3 = new ConnectableProperty();

        p1.biconnectTo(p2);
        p2.biconnectTo(p3);
        p3.biconnectTo(p1);

        p2.setValue(5);

        assertEquals(5, p1.getValue());
        assertEquals(5, p2.getValue());
        assertEquals(5, p3.getValue());

        p1.setValue(42);

        assertEquals(42, p1.getValue());
        assertEquals(42, p2.getValue());
        assertEquals(42, p3.getValue());

        p3.setValue(17);

        assertEquals(17, p1.getValue());
        assertEquals(17, p2.getValue());
        assertEquals(17, p3.getValue());
    }

    @Test
    public void test_unconnected_property_does_not_transmit_value() {
    	p2.setValue(5);
        p1.connectTo(p2);
        assertEquals(5, p1.getValue());
        
        p1.unconnectFrom(p2);
    	p2.setValue(42);

        assertEquals(5, p1.getValue());
    }

}
