package main.java.fr.univlille;

public class Temps {
    private int tempsCoureur;
    private int heures;
    private int minutes;
    private int secondes;

    public Temps(int secondes){
        this.tempsCoureur=secondes;
        this.setTime();
    }

    private void setTime(){
        if(this.tempsCoureur>0){
            this.heures = this.tempsCoureur/3600;
            this.minutes = (this.tempsCoureur%3600)/60;
            this.secondes = (this.tempsCoureur%60);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.heures+":");
        sb.append(this.minutes+":");
        sb.append(this.secondes);
        return sb.toString();
    }

    public int getTempsCoureur(){
        return this.tempsCoureur;
    }

    public static Temps add(Temps t1, Temps t2){
        Temps temps = new Temps(t1.getTempsCoureur() + t2.getTempsCoureur());
        return temps;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + tempsCoureur;
        result = prime * result + heures;
        result = prime * result + minutes;
        result = prime * result + secondes;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Temps other = (Temps) obj;
        if (tempsCoureur != other.tempsCoureur)
            return false;
        if (heures != other.heures)
            return false;
        if (minutes != other.minutes)
            return false;
        if (secondes != other.secondes)
            return false;
        return true;
    }

    

}