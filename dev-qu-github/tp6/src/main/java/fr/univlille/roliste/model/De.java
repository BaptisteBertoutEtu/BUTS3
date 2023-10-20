package main.java.fr.univlille.roliste.model;

import java.util.Random;
import main.java.fr.univlille.utils.Subject;

public class De extends Subject{

    private Random rand;
    private int faces;

    public De(int faces){
        this.faces = faces;
        rand = new Random();
    }

    public void lancer(){
        notifyObservers(rand.nextInt(1, faces+1));
    }

    public int getFaces(){
        return faces;
    }
}
