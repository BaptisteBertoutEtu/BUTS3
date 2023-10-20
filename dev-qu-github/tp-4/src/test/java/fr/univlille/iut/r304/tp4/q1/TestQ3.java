package test.java.fr.univlille.iut.r304.tp4.q1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.fr.univlille.iut.r304.tp4.q1.Timer;
import test.java.fr.univlille.iut.r304.tp4.ObserverTestStub;

import static org.junit.jupiter.api.Assertions.*;


public class TestQ3 {

    private static final int MORE_THAN_1_SECOND = 1100;  // in milliseconds
    
	/** Two objects used in the tests 
     */
    protected Timer timer;
    protected ObserverTestStub observer;

    @BeforeEach
    public void setup() {
        timer = new Timer();
        timer.start();
        observer = new ObserverTestStub();
    }

    @AfterEach
    public void teardown() {
        timer.stopRunning();
    }

    @Test
    public void test_timer_notifies_attached_observer() {
        assertFalse( observer.wasNotified());
        timer.attach(observer);
        try {
            Thread.sleep(MORE_THAN_1_SECOND);
        } catch (InterruptedException e) {
            fail("Exception while waiting");
        }
        assertTrue( observer.wasNotified());
    }

    @Test
    public void test_timer_does_not_notify_unattached_observer() {
        assertFalse( observer.wasNotified());
        try {
            Thread.sleep(MORE_THAN_1_SECOND);
        } catch (InterruptedException e) {
            fail("Exception while waiting");
        }
        assertFalse( observer.wasNotified());
    }

    @Test
    public void test_timer_does_not_notify_detached_observer() {
        assertFalse( observer.wasNotified());
        timer.attach(observer);
        timer.detach(observer);
        try {
            Thread.sleep(MORE_THAN_1_SECOND);
        } catch (InterruptedException e) {
            fail("Exception while waiting");
        }
        assertFalse( observer.wasNotified());
    }

}
