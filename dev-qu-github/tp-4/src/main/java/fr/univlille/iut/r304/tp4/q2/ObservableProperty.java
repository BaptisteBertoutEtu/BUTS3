package main.java.fr.univlille.iut.r304.tp4.q2;

import main.java.fr.univlille.iut.r304.tp4.q1.Observer;
import main.java.fr.univlille.iut.r304.tp4.q1.Subject;

public class ObservableProperty extends Subject{

	private Object o;

	public void setValue(Object i) {
		this.o=i;
		super.notifyObservers(i);
	}

	public Object getValue() {
		return this.o;
	}

	public void attach(Observer observer) {
		super.attach(observer);
	}

	public void detach(Observer observer) {
		super.detach(observer);
	}

}
