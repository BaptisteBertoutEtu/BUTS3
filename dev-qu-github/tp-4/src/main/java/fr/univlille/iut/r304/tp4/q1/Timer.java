package main.java.fr.univlille.iut.r304.tp4.q1;


public class Timer extends Subject{

	private TimerThread tt = new TimerThread(this);

	public void start() {
		tt.start();
	}

	public void stopRunning() {
		tt.stop();;
	}

	public void attach(Observer observer) {
		super.attach(observer);
		// methode cree pour que les tests compilent sans erreur
		// n'est pas censée rester une fois que vous avez fini Q1.3
	}

	public void detach(Observer observer) {
		super.detach(observer);
		// methode cree pour que les tests compilent sans erreur
		// n'est pas censée rester une fois que vous avez fini Q1.3
	}

}
