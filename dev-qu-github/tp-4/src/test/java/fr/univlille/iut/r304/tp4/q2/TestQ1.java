package test.java.fr.univlille.iut.r304.tp4.q2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.fr.univlille.iut.r304.tp4.q2.ObservableProperty;
import test.java.fr.univlille.iut.r304.tp4.ObserverTestStub;

public class TestQ1 {
    protected ObservableProperty prop;
    protected ObserverTestStub observer;

    @BeforeEach
    public void setup() {
        prop = new ObservableProperty();
        observer = new ObserverTestStub();
    }

    @Test
    public void test_change_ObservableProperty_without_observer() {
        prop.setValue(5);
        assertEquals(5, prop.getValue());
    }

    @Test
    public void test_change_ObservableProperty_notifies_observer() {
        prop.attach(observer);

        prop.setValue(5);
        assertTrue(observer.wasNotified());
    }

    @Test
    public void test_change_ObservableProperty_does_not_notify_detached_observer() {
        prop.attach(observer);
        prop.detach(observer);
        prop.setValue(5);
        assertFalse(observer.wasNotified());
    }

}
