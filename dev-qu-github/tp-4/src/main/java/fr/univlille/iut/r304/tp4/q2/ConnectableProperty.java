package main.java.fr.univlille.iut.r304.tp4.q2;

import main.java.fr.univlille.iut.r304.tp4.q1.Observer;
import main.java.fr.univlille.iut.r304.tp4.q1.Subject;

public class ConnectableProperty extends ObservableProperty implements Observer{

	public void connectTo(ConnectableProperty other) {
		other.attach(this);
	}

	public void biconnectTo(ConnectableProperty other) {
		this.connectTo(other);
		this.update(other, other.getValue());
		other.connectTo(this);
	}

	public void unconnectFrom(ConnectableProperty other) {
		other.detach(other);
	}

	@Override
	public void update(Subject subj) {

	}

	@Override
	public void update(Subject subj, Object data) {
		if((this.getValue()==null) || (!this.getValue().equals(data))) this.setValue(data);
	}

}
