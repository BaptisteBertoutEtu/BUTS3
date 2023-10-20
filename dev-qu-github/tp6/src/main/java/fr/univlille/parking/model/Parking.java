package main.java.fr.univlille.parking.model;

import main.java.fr.univlille.utils.Subject;

public class Parking extends Subject{
    private final int nombrePlace;
    private int nombrePlaceLibre;

    public Parking(int nombrePlace){
        this.nombrePlace = nombrePlace;
        this.nombrePlaceLibre = this.nombrePlace;
    }

    public void enleverPlace(){
        if(this.nombrePlaceLibre-1 >= 0){
            setNombrePlaceLibre(this.nombrePlaceLibre-1);
            notifyObservers(this.nombrePlaceLibre);
        }
        else if(this.nombrePlaceLibre-1 < 0){
            notifyObservers();
        }
    }

    
    public void ajouterPlace(){
        if(this.nombrePlaceLibre+1 <= this.nombrePlace){
            setNombrePlaceLibre(this.nombrePlaceLibre+1);
            notifyObservers(this.nombrePlaceLibre);
        }
        if(this.nombrePlaceLibre > 0) {
            notifyObservers();
        }
    }

    public int getNombrePlaceLibre(){
        return this.nombrePlaceLibre;
    }

    public void setNombrePlaceLibre(int nombrePlaceLibre){
        this.nombrePlaceLibre = nombrePlaceLibre;
    }
}
