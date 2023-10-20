package fr.univlille.entity;

import java.util.ArrayList;
import java.util.List;

import fr.univlille.Game;
import fr.univlille.donjon.Donjon;
import fr.univlille.donjon.Theme;
import fr.univlille.effect.Bonus;
import fr.univlille.effect.BonusType;
import fr.univlille.effect.LevelChoice;
import fr.univlille.view.DialogueView;

public class Renaud extends Entity{
    public static final int BASE_HP = 100;
    public static final int BASE_ATK = 15;
    public static final int BASE_DEF = 20;
    public static final int BASE_LEVEL = 1;
    public static final int BASE_EXP = 0;
    public static final int BASE_NEEDED_EXP = 100;
    public static final int BASE_STAGE = 0;
    public static final int BASE_ROOM = 0;

    private Donjon currentDonjon;

    private List<Bonus> bonusList;
    private List<Bonus> bonusDrawList;
    private List<Bonus> learnedSpells;

    private int atk;
    private int level;
    private int expNeeded;
    private int expCurrent;
    private int stage;
    private int room;

    public Renaud(Donjon currentDonjon) {
        super("Renaud", BASE_HP, BASE_DEF);
        this.currentDonjon = currentDonjon;
        this.bonusList = new ArrayList<Bonus>();
        this.bonusDrawList = Bonus.getBonusList();
        this.learnedSpells = new ArrayList<Bonus>();
        this.atk = BASE_ATK;
        this.level = BASE_LEVEL;
        this.expNeeded = BASE_NEEDED_EXP;
        this.expCurrent = BASE_EXP;
        this.stage = BASE_STAGE;
        this.room = BASE_ROOM;
        addBonusToRenaud(Bonus.LANCE_DE_BRIQUE);
    }

    public List<Bonus> getBonusList() {
        return bonusList;
    }

    public void addBonusToRenaud(Bonus bonus) {
        bonusList.add(bonus);
        if (!bonus.getBonusType().equals(BonusType.BUFF)) {
            bonusDrawList.remove(bonus);
            learnedSpells.add(bonus);
        } 
        bonus.calcBuffOrValue(this);
    }

    public List<Bonus> getBonusDrawList() {
        return bonusDrawList;
    }

    public List<Bonus> getLearnedSpells() {
        return learnedSpells;
    }

    public boolean addLearnedSpells(Bonus spell) {
        return learnedSpells.add(spell);
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }


    public int getExpNeeded() {
        return expNeeded;
    }

    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }

    public int getExpCurrent() {
        return expCurrent;
    }

    public void setExpCurrent(int expCurrent) {
        this.expCurrent = expCurrent;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void nextRoom(){
        int roomMax = currentDonjon.getRoomsCount(stage);
        room++;
        while(room >= roomMax){
            room -= roomMax;
            nextStage();
        }
    }

    public Theme getCurrentTheme(){
        return currentDonjon.getTheme(stage);
    }

    /**
     * Méthode surchargée pour la facilité des tests
     */
    public void nextRoomTest(){
        int roomMax = 5; // on considère le max des room a 5 pour les test
        room++;
        while(room >= roomMax){
            room -= roomMax;
            nextStageTest();
        }
    }

    public void nextStage(){
        stage++;
        DialogueView.startStage(currentDonjon.getTheme(stage));
    }

    /**
     * Méthode surchargée pour la facilité des tests
     */
    public void nextStageTest(){
        stage++;
    }

    public int getLevel() {
        return level;
    }

    public void giveExp(int exp) {
        expCurrent += exp;
        while(expCurrent >= expNeeded){
            expCurrent -= expNeeded;
            nextLevel();
        }
    }

    /**
     * Méthode surchargée pour la facilité des tests
     */
    public void giveExpTest(int exp) {
        expCurrent += exp;
        while(expCurrent >= expNeeded){
            expCurrent -= expNeeded;
            nextLevelTest();
        }
    }

    public boolean applyBonus(Bonus bonus) {
        if(bonus.getBonusType().equals(BonusType.BUFF)) {
            bonus.calcBuffOrValue(this);
            return true;
        }
        return this.addLearnedSpells(bonus) && bonusDrawList.remove(bonus);
    }
    
    public void nextLevel() {
        Game.clearScreen();
        level++;
        expNeeded *= 1.2;
        System.out.println("Vous êtes passé niveau " + getLevel() + " !");
        applyBonus(LevelChoice.pickBonus(this));
        this.setCurrentHp(this.getHp());
    }

    public boolean isDead(){
        return this.getCurrentHp() <= 0;
    }

    /**
     * Méthode surchargée pour la facilité des tests
     */
    private void nextLevelTest() {
        level++;
        expNeeded *= 1.2;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + super.toString() + "[atk:" + atk + ", level:" + level + ", expNeeded:" + expNeeded
        + ", expCurrent:" + expCurrent + ", stage:" + stage + ", room:" + room + ", bonusList:" + bonusList.toString() 
        + ", bonusDrawList:" + bonusDrawList.toString() + ", learnedSpells:" + learnedSpells + "]";
    }
}
