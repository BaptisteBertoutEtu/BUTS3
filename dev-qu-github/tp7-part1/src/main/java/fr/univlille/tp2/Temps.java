package fr.univlille.tp2;

import java.util.Objects;

public class Temps {
    private final int s;

    public Temps(int s) {
    	if (s <0) s = 0;
    	this.s = s;
    }

    public static Temps add(Temps a, Temps b) {
        return new Temps(a.s + b.s);
    }

    public String toString() {
            return this.s / (60 * 60) + ":" + ((this.s % (60*60)) /  60) + ":" + (this.s % 60);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Temps temps = (Temps) o;
    return s == temps.s;
    }

    @Override
    public int hashCode() {
        return Objects.hash(s);
    }

}
