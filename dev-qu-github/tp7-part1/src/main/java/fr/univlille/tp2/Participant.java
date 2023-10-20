package fr.univlille.tp2;

import java.util.Objects;

public class Participant implements Comparable {
    static int n;
    int dossard;
    String nom;
    String prenom;

    static void reset() {
    Participant.n = 1;
    }

    public Participant(String nom, String prenom) {
        this.dossard = Participant.n++;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public String toString() {
    return nom + " " + prenom + " " + dossard;
    }

    @Override
    public int compareTo(Object o) {
        Participant p2 = (Participant) o;
        if (this.dossard != p2.dossard)
            return Integer.compare(this.dossard, p2.dossard);
        if (!this.nom.equals(p2.nom))
            return this.nom.compareTo(p2.nom);
        return this.prenom.compareTo(p2.prenom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;
        Participant that = (Participant) o;
        return dossard == that.dossard && nom.equals(that.nom) && prenom.equals(that.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dossard, nom, prenom);
    }
}
