package main.java.fr.univlille.iut.r304.tp4.q1;

import java.util.HashSet;
import java.util.Set;

public abstract class Subject {

	private Set<Observer> observers = new HashSet<Observer>();

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}

	public void notifyObservers(Object data) {
		for (Observer observer : this.observers) {
			observer.update(this, data);
		}
	}

	public void attach(Observer observer) {
		this.observers.add(observer);
	}

	public void detach(Observer observer) {
		this.observers.remove(observer);
	}
}
