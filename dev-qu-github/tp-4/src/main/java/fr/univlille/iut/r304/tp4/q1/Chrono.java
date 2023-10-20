package main.java.fr.univlille.iut.r304.tp4.q1;

public class Chrono implements Observer{
    private String name ="Chrono";
    private int time = 0;
    private int total;
    private static int nb = 1;

    public Chrono(int total){
        this.total=total;
        this.name=name+Chrono.nb;
        Chrono.nb++;
    }

    public Chrono(){
        this(0);
    }

    @Override
    public void update(Subject subj) {
        this.time++;
        this.total++;
        afficherChrono();
    }

    @Override
    public void update(Subject subj, Object data) {
        this.time++;
        this.total++;
    }

    public void afficherChrono(){
        System.out.println(this.name+" : \n"+"Etape : "+this.time+"\nTotal : "+this.total);
    }

    public static void main(String[] args) throws InterruptedException {
        Chrono c = new Chrono();
        Chrono c2 = new Chrono(10);
        Timer t = new Timer();
        t.attach(c);
        t.attach(c2);
        t.start();
        c.afficherChrono();
        c2.afficherChrono();
        Thread.sleep(10000);
        t.stopRunning();
    }

}
