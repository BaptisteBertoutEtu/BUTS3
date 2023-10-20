package fr.univlille.entity;

import fr.univlille.bestiary.Bestiary;
import fr.univlille.effect.Spell;

public class Boss {
    private Spell secondSpell;

    public Boss(Bestiary mob) {
        this.secondSpell = mob.getSecondSpell();
    }

    public Spell getSecondSpell() {
        return secondSpell;
    }

    public void setSecondSpell(Spell secondSpell) {
        this.secondSpell = secondSpell;
    }

    @Override
    public String toString() {
        return super.toString() + "[secondSpell:" + secondSpell + "]";
    }
}
