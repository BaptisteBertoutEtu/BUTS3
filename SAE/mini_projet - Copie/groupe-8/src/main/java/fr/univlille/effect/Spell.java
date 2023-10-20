package fr.univlille.effect;

import java.util.List;

import fr.univlille.Mathf;

public enum Spell {
    NON_LIEU("Non lieu", SpellType.ATTACK, 20),
    DECENTE_DE_GARDE("Décente de garde", SpellType.ATTACK, 30),
    COUP_DE_MATRAQUE("Coup de matraque", SpellType.ATTACK, 10),
    FLASH_BALL("Flash Ball", SpellType.ATTACK, 20),
    BAVURE("Bavure", SpellType.ATTACK, 25),
    MORSURE("Morsure", SpellType.ATTACK, 20),
    DEGRADATION_MENTAL("Dégradation mentale", SpellType.ATTACK, 20),
    BURNOUT("Burnout", SpellType.ATTACK, 25),
    COUP_DE_REGLE_MAGIC("Coup de règle magique", SpellType.ATTACK, 20),
    DEVOIR_MAISON("Devoir maison", SpellType.ATTACK, 20),
    ESSOUFFLEMENT("Éssoufflement", SpellType.ATTACK, 3),
    TOILE_ETHERIQUE("Toile étherique", SpellType.ATTACK, 15),
    BARRAGE("Barrage", SpellType.ATTACK, 20),
    FRAPPE_INSOUMISE("Frappe Insoumise", SpellType.ATTACK, 10),
    EXPLOSION_MARCANIQUE("Explosion Marcanique", SpellType.ATTACK, 10),
    PILLAGE("Pillage", SpellType.ATTACK, 20),
    HARCELEMENT_CIBLER("Harcelement cibler", SpellType.ATTACK, 7),
    GUILLOTINE("Guillotine", SpellType.ATTACK, 20),
    ASMODEUS("Asmodeus, Démone de la luxure", SpellType.ATTACK, 20),
    ETREINTE_VAMPIRIQUE("Étreinte vampirique", SpellType.ATTACK, 10),
    ENLACEMENT_EROTIQUE("Enlacement érotique", SpellType.ATTACK, 10),
    DEUX_BON_ARGUMENT("Deux 'BON' arguments", SpellType.ATTACK, 10),
    DRAINAGE_SANGUIN("Drainage sanguin", SpellType.ATTACK, 10),
    CHARGE_INERTE("Charge inerte", SpellType.ATTACK, 1),
    MORNING_STAR("Morning Star", SpellType.ATTACK, 33),
    CONFINEMENT("Confinement", SpellType.ATTACK, 48);

    private String name;
    private SpellType type;
    private int amount;

    private Spell(String name, SpellType type, int amount){
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public String getName(){
        return name;
    }

    public SpellType getType(){
        return type;
    }

    public int getAmount(){
        return amount;
    }

    public static Spell random(){
        return Mathf.random(values());
    }

    public static Spell random(List<Spell> list){
        return Mathf.random(list);
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + "[name:" + name + ", type:" + type + ", amount:" + amount + "]";
    }
}
