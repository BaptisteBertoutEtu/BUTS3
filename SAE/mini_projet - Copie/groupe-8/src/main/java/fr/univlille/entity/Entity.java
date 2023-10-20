package fr.univlille.entity;

import fr.univlille.bestiary.Bestiary;

public class Entity {
    private final String NAME;
    private int hp;
    private int currentHp;
    private int def;

    
    public Entity(String name, int hp, int def) {
        this.NAME = name;
        this.hp = hp;
        this.currentHp = hp;
        this.def = def;
    }

    public Entity(Bestiary mob){
        this(mob.getName(), mob.getHealth(), mob.getDefense());
    }

    public String getName(){
        return NAME;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getCurrentHp() {
        return currentHp;
    }
    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getDef() {
        return def;
    }
    public void setDef(int def) {
        this.def = def;
    }

    public String toString() {
        return getClass().getSimpleName() + "[name:" + NAME + ", hp:" + hp + ", currentHp:" + currentHp + ", def:" + def + "]";
    }
}