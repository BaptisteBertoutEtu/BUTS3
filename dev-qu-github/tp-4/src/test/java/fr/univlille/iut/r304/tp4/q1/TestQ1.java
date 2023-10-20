package test.java.fr.univlille.iut.r304.tp4.q1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.fr.univlille.iut.r304.tp4.q1.Subject;
import test.java.fr.univlille.iut.r304.tp4.ObserverTestStub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestQ1 {

    /** A mock concrete subject
     * Used to register Observers and notify them
     */
    protected class MockSubject extends Subject {
        public void notifyValue(int i) {
            this.notifyObservers(i);
        }
    }

    /** Two objects used in the tests 
     */
    protected MockSubject subject;
    protected ObserverTestStub observer;

    @BeforeEach
    public void setup() {
        subject = new MockSubject();
        observer = new ObserverTestStub();
    }

    @Test
    public void test_does_not_notify_unattached_observer() {
        subject.notifyObservers();
        assertFalse( observer.wasNotified());
    }

    @Test
    public void test_notifies_attached_observer() {
        subject.attach(observer);
        subject.notifyObservers();
        assertTrue( observer.wasNotified());
    }

    @Test
    public void test_notifies_several_attached_observers() {
    	ObserverTestStub observer2 = new ObserverTestStub();
    	ObserverTestStub observer3 = new ObserverTestStub();

    	subject.attach( observer);
    	subject.attach( observer2);
    	subject.attach( observer3);
        subject.notifyObservers();

        assertTrue( observer.wasNotified());
        assertTrue( observer2.wasNotified());
        assertTrue( observer3.wasNotified());
    }

    @Test
    public void test_does_not_notify_observer_attached_then_detached() {
        subject.attach( observer);
        subject.detach( observer);
        subject.notifyObservers();
        assertFalse( observer.wasNotified());
    }

    @Test
    public void test_does_not_notify_observer_after_detach_without_attach() {
        subject.detach( observer);
        subject.notifyObservers();
        assertFalse( observer.wasNotified());
    }

    @Test
    public void test_push_notify_transmits_value() {
        subject.attach( observer);
        assertEquals(0, observer.getValue());
        subject.notifyValue(5);
        assertTrue( observer.wasNotified());
        assertEquals(5, observer.getValue());
    }

}
