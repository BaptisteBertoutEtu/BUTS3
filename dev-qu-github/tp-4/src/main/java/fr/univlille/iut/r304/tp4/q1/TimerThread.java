package main.java.fr.univlille.iut.r304.tp4.q1;

public class TimerThread extends Thread {

    private Timer t ;

    public TimerThread(Timer t){
        this.t=t;
    }

    @Override
    public void run(){
        while (true) {
        try {
            sleep(1000);
            t.notifyObservers();
        } catch (InterruptedException e) {
            // on ignore et on espère que ce n’est pas grave
        }
        }
    }
}
