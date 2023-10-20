package fr.univlille.effect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.univlille.Mathf;
import fr.univlille.entity.Renaud;

public enum Bonus {

    /* Sorts à valeur fixe */
    LANCE_DE_BRIQUE("Lancé de brique", 0, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 1, 15),
    POING_BOUCHE("Poing-bouche", 0, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 3, 20),
    PTITE_BIERE("P'tite bière", 0, BonusType.SPELL, SpellType.HEALTH, UseType.HEAL, 2, 10),
    WRIGHT_KICK("Wright kick", 1, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 2, 20),
    COUP_DE_BOULE("Coup de boule", 1, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 4, 27),
    BARQUETTE_DE_FRITTES("Barquette de frittes", 1, BonusType.SPELL, SpellType.HEALTH, UseType.HEAL, 2, 20),
    COCKTAIL_MOLOTOV("Cocktail molotov", 2, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 3, 25),
    BALAYETTE("Balayette", 2, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 5, 30),
    COCKTAIL_FRUITE("Cocktail fruité", 2, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 2, 70),
    LANCE_DE_CHAISE("Lancé de chaise", 3, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 2, 25),
    CONCERT_ASSOURDISSANT("Concert assourdissant", 3, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 3, 40),
    MENU_GIANT("Menu giant", 3, BonusType.SPELL, SpellType.ATTACK, UseType.HEAL, 4, 160),
    COUP_DE_GUITARE("Coup de guitare", 4, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 1, 50),
    REQUIEM_MACABRE("Requiem macabre", 4, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 4, 100),
    GRAND_CRU("Grand crû", 4, BonusType.SPELL, SpellType.ATTACK, UseType.HEAL, 2, 300),

    /* Sorts à valeur modulable (value est un pourcentage en int) */
    LA_SERENADE_DU_COEUR("La sérénade du coeur", 4, BonusType.SPELL_SCALING, SpellType.HEALTH, UseType.DAMAGE, 5, 50),
    HARD_METAL("Hard metal", 4, BonusType.SPELL_SCALING, SpellType.DEFENSE, UseType.DAMAGE, 5, 75),
    ARIA_OF_THE_SOULS("Aria of the souls", 4, BonusType.SPELL_SCALING, SpellType.ATTACK, UseType.DAMAGE, 5, 100),
    LA_BERCEUSE_DERESHKIGAL_LULLABY("La berceuse d'Ereshkigal", 4, BonusType.SPELL_SCALING, SpellType.HEALTH, UseType.HEAL, 5, 50),
    MEGIDOLAON("Megidolaon", 2, BonusType.SPELL_SCALING, SpellType.HEALTH, UseType.DAMAGE, 2, 25),
    VENT_DE_FARORE("Vent de Farore", 2, BonusType.SPELL_SCALING, SpellType.DEFENSE, UseType.DAMAGE, 2,35),
    FEU_DE_DIN("Feu de Din", 2, BonusType.SPELL_SCALING, SpellType.ATTACK, UseType.DAMAGE, 2, 50),
    SAGESSE_DE_NAYRU("Sagesse de Nayrus", 2, BonusType.SPELL_SCALING, SpellType.HEALTH, UseType.HEAL, 2, 25),
    MEGIDO("Megido", 0, BonusType.SPELL_SCALING, SpellType.HEALTH, UseType.DAMAGE, 3, 10),
    FREEDOM("Freedom", 0, BonusType.SPELL_SCALING, SpellType.DEFENSE, UseType.DAMAGE, 3, 15),
    AGILAON("Agilaon", 0, BonusType.SPELL_SCALING, SpellType.ATTACK, UseType.DAMAGE, 3, 10),
    DIA("Dia", 0, BonusType.SPELL_SCALING, SpellType.HEALTH, UseType.DAMAGE, 3, 10),

    /* Buffs constants (value est un pourcentage en int) */
    VIOLENCE("Violence", 0, BonusType.BUFF, SpellType.ATTACK, 10),
    VIOLENCE_FAROUCHE("Violence", 0, BonusType.BUFF, SpellType.ATTACK, 30),
    VIOLENCE_DE_SES_MORTS("Violence de ses morts", 0, BonusType.BUFF, SpellType.ATTACK, 50),
    SANTE("Santé", 0, BonusType.BUFF, SpellType.HEALTH, 10),
    SANTE_PAS_CREVE("Santé, pas crevé", 0, BonusType.BUFF, SpellType.HEALTH, 30),
    SANTE_TOUJOURS_DEBOUT("Santé, toujours debout", 0, BonusType.BUFF, SpellType.HEALTH, 50),
    ROBUSTESSE("Robustesse", 0, BonusType.BUFF, SpellType.DEFENSE, 10),
    ROBUSTESSE_IVROGNE("Robustesse ivrogne", 1, BonusType.BUFF, SpellType.DEFENSE, 30),
    ROBUSTESSE_COCKASS("Robustesse cockass", 2, BonusType.BUFF, SpellType.DEFENSE, 50);

    private String name;
    private int rarity;
    private BonusType type;
    private SpellType spellType;
    private UseType useType;
    private int cooldown;
    private int value;

    private Bonus(String name, int rarity, BonusType type, SpellType spellType, UseType useType, int cooldown, int value) {
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.spellType = spellType;
        this.useType = useType;
        this.cooldown = cooldown;
        this.value = value;
    }

    private Bonus(String name, int rarity, BonusType type, SpellType spellType, int value) {
        this(name, rarity, type, spellType, UseType.DAMAGE, 0, value);
    }

    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }

    public BonusType getBonusType() {
        return type;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public UseType getUseType() {
        return useType;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getValue() {
        return value;
    }

    public static Bonus random(){
        return Mathf.random(values());
    }

    public static Bonus random(List<Bonus> list){
        ArrayList<Bonus> res = new ArrayList<Bonus>();
        for (Bonus b : list) {
            for (int i = 0; i < (5-b.getRarity()); i++) {
                res.add(b);
            }
        }
        return Mathf.random(res);
    }

    public static List<Bonus> getBonusList() {
        return Arrays.asList(values());
    }

    public int calcBuffOrValue(Renaud renaud) {
        int v = 0;
        if (spellType.equals(SpellType.ATTACK)) {
            v = (int) (value*(renaud.getAtk()*0.01));
            if (type.equals(BonusType.BUFF)) {
                renaud.setAtk(renaud.getAtk()+v);
            }
            return v;
        }
        if (spellType.equals(SpellType.DEFENSE)) {
            v = (int) (value*(renaud.getDef()*0.01));
            if (type.equals(BonusType.BUFF)) {
                renaud.setDef(renaud.getDef()+v);
            }
            return v;
        }
        v = (int) (value*(renaud.getHp()*0.01));
            if (type.equals(BonusType.BUFF)) {
                renaud.setHp(renaud.getHp()+v);
            }
        return v;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[codeName:" + name() + ", name:" + name + ", rarity:" + rarity +
            ", bonusType:" + type + ", scaling:" + spellType + ", useType:" + useType +
            ", cooldown:" + cooldown + ", value:" + value + "]";
    }
}
