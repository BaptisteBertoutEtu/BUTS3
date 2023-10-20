package fr.univlille.bestiary;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.Mathf;
import fr.univlille.donjon.Theme;
import fr.univlille.effect.Spell;

public enum Bestiary{
    CRS(Theme.INTERIEUR, "CRS", "crs.txt", 30, 40, Spell.COUP_DE_MATRAQUE),
    ARS(Theme.INTERIEUR, "ARS", "ARS.txt", 20, 5, Spell.FLASH_BALL),
    BRAVE_M(Theme.INTERIEUR, "BRAVE M", "braveM_ascii.txt", 40, 20, Spell.BAVURE),
    CHIEN_DE_GARDE(Theme.INTERIEUR, "Chien de gardes", "ChienDeGarde.txt", 30, 0, Spell.MORSURE),
    GERALD_GARDEMIN(Theme.INTERIEUR, "Gérald Gardemin", "GeraldGardefr.univlille.txt", 70, 20, Spell.NON_LIEU, Spell.DECENTE_DE_GARDE),
    PROFESSEUR_DE_MAGIE(Theme.EDUCATION, "Professeur de magie", "professeur_magie.txt", 40, 10, Spell.COUP_DE_REGLE_MAGIC),
    LIVRE_SCOLAIRE_MAGIC(Theme.EDUCATION, "Livre scolaire magic", "magic_book.txt", 20, 10, Spell.DEVOIR_MAISON),
    PROFESSEUR_DEPS(Theme.EDUCATION, "Professeur d'EPS", "prof_eps.txt", 100, 5, Spell.ESSOUFFLEMENT),
    ARAIGNEE_DE_BIBLIOTHEQUE(Theme.EDUCATION, "Araignée de bibliothèque", "araignee_biblio.txt", 30, 30, Spell.TOILE_ETHERIQUE),
    JEAN_MERLIN_BLANQUER(Theme.EDUCATION, "Jean Merlin Blanquer", "JeanMerlinBlancker.txt", 70, 10, Spell.DEGRADATION_MENTAL, Spell.BURNOUT),
    ROBE_JAUNE(Theme.OPPOSITION, "Robe Jaune", "robe_jaune.txt", 20, 50, Spell.EXPLOSION_MARCANIQUE),
    CASSEUR(Theme.OPPOSITION, "Casseur", "casseur.txt", 25, 30, Spell.PILLAGE),
    CHAOSSOS(Theme.OPPOSITION, "Chaossos", "chaossos.txt", 10, 99, Spell.HARCELEMENT_CIBLER),
    REVOLUTIONNAIRE(Theme.OPPOSITION, "Révolutionnaire", "revolutionary.txt", 20, 60, Spell.GUILLOTINE),
    JL_ANARCHON(Theme.OPPOSITION, "JL l'Anarchon", "jl_larnarchon.txt", 70, 80, Spell.FRAPPE_INSOUMISE, Spell.BARRAGE),
    SUCCUBUS(Theme.CULTURE, "Succubus <3", "succubus.txt", 69, 15, Spell.ENLACEMENT_EROTIQUE),
    PLAYGIRL(Theme.CULTURE, "PlayGirl", "playgirl.txt", 50, 0, Spell.DEUX_BON_ARGUMENT),
    VAMPIRETTE(Theme.CULTURE, "Vampirette", "vampirette.txt", 66, 0, Spell.DRAINAGE_SANGUIN),
    ESCLAVE_GOLEM(Theme.CULTURE, "Esclave Golem", "golem.txt", 166, 0, Spell.CHARGE_INERTE),
    MARLENNE_LILITHIA(Theme.CULTURE, "Marlène Lilithia", "marlenne_lilithia.txt", 100, 0, Spell.ETREINTE_VAMPIRIQUE, Spell.ASMODEUS),
    ARDIEJM(Theme.FINALE, "Archi roi démon immortel Emanuelle Jupiter Macrongue", "archi_roi_demon_immortel_emmanuel_jupiter_macrongue.txt", 493, 49, Spell.MORNING_STAR, Spell.CONFINEMENT);

    public static final Bestiary FINAL_BOSS = Bestiary.ARDIEJM;

    private Theme theme;
    private String name;
    private String fileName;
    private int health;
    private int defense;
    private Spell firstSpell;
    private Spell secondSpell;

    private Bestiary(Theme theme, String name, String fileName, int health, int defense, Spell firstSpell, Spell secondSpell){
        this.theme = theme;
        this.name = name;
        this.fileName = fileName;
        this.health = health;
        this.defense = defense;
        this.firstSpell = firstSpell;
        this.secondSpell = secondSpell;
    }

    private Bestiary(Theme theme, String name, String fileName, int health, int defense, Spell firstSpell){
        this(theme, name, fileName, health, defense, firstSpell, null);
    }

    public Theme getTheme(){
        return theme;
    }

    public String getName(){
        return name;
    }

    public String getFileName(){
        return fileName;
    }
    
    public int getHealth(){
        return health;
    }

    public int getDefense(){
        return defense;
    }

    public Spell getFirstSpell(){
        return firstSpell;
    }

    public Spell getSecondSpell(){
        return secondSpell;
    }

    public boolean isBoss(){
        return secondSpell!=null;
    }

    public static Bestiary getFinalBoss(){
        return FINAL_BOSS;
    }

    public static Bestiary random(){
        return Mathf.random(values());
    }

    public static Bestiary random(List<Bestiary> list){
        return Mathf.random(list);
    }

    public static Bestiary random(List<Bestiary> list, boolean isBoss){
        List<Bestiary> mobs = new ArrayList<>();
        List<Bestiary> boss = new ArrayList<>();
        for(Bestiary m : list){
            if(m.isBoss()) boss.add(m);
            else mobs.add(m);
        }
        if(isBoss && !boss.isEmpty()) return Mathf.random(boss);
        else if(!mobs.isEmpty()) return Mathf.random(mobs);
        return null;
    }

    public static Bestiary randomBossNotFinal(){
        List<Bestiary> boss = new ArrayList<>();
        for(Bestiary m : values()) if(m.isBoss() && m != FINAL_BOSS) boss.add(m);
        return random(boss);
    }

    public static List<Bestiary> getMobs(Theme theme){
        List<Bestiary> mobs = new ArrayList<>();
        for(Bestiary m : values()) if(m.getTheme() == theme) mobs.add(m);
        return mobs;
    }
    
    @Override
    public String toString(){
        return getClass().getSimpleName() + "[theme:" + theme + ", name:" + name + ", fileName:" + fileName + ", health:" + health + ", defense:" + defense + ", firstSpell:" + firstSpell + ", secondSpell:" + secondSpell + "]";
    }
}