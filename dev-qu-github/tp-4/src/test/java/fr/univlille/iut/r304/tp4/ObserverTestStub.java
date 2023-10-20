package test.java.fr.univlille.iut.r304.tp4;

import main.java.fr.univlille.iut.r304.tp4.q1.Observer;
import main.java.fr.univlille.iut.r304.tp4.q1.Subject;

/** ObserverTestStub
 * Can tell whether it was notified or not
 * Have a value that can be set by notification
 */
public class ObserverTestStub implements Observer {

    protected boolean wasNotified;

    protected int someValue;

    public ObserverTestStub() {
        wasNotified = false;
        someValue = 0;
    }

    public int getValue() {
        return someValue;
    }

    public void setValue(int someValue) {
        this.someValue = someValue;
    }

    public void update(Subject subj) {
        wasNotified = true;
    }

    public void update(Subject subj, Object data) {
        wasNotified = true;
        someValue = (int) data;
    }

    public boolean wasNotified() {
        return wasNotified;
    }

}
