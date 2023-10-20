package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.donjon.Donjon;
import main.effect.Bonus;
import main.entity.Renaud;

public class RenaudTest {
    Renaud r;
    Donjon dj;
    List<Bonus> bonusList;
    List<Bonus> bonusDrawList;
    List<Bonus> learnedSpells;
    
    @BeforeEach
    void initialisation(){
        this.dj = new Donjon();
        this.r = new Renaud(dj);
        this.bonusList = this.r.getBonusList();
        this.bonusDrawList = this.r.getBonusDrawList();
        this.learnedSpells = this.r.getLearnedSpells();
    }

    @Test
    void testGetBonusList(){
        this.bonusList.add(Bonus.LANCE_DE_BRIQUE);
        this.bonusList.add(Bonus.POING_BOUCHE);
        this.bonusList.add(Bonus.BARQUETTE_DE_FRITTES);
        this.bonusList.add(Bonus.COCKTAIL_MOLOTOV);

        this.r.addBonusToRenaud(Bonus.POING_BOUCHE);
        this.r.addBonusToRenaud(Bonus.BARQUETTE_DE_FRITTES);
        this.r.addBonusToRenaud(Bonus.COCKTAIL_MOLOTOV);
        
        for (Bonus bonus : this.r.getBonusList()) {
            assertTrue(this.bonusList.contains(bonus));
        }
    }
    
    @Test
    void testAddBonusToRenaud(){
        assertEquals(this.r.getBonusList().size(),1);
        assertEquals(this.r.getLearnedSpells().size(), 1);

        this.r.addBonusToRenaud(Bonus.BALAYETTE);
        assertEquals(this.r.getBonusList().size(), 2);
        assertTrue(this.r.getBonusList().contains(Bonus.BALAYETTE));
        assertFalse(this.r.getBonusDrawList().contains(Bonus.BALAYETTE));
        assertTrue(this.r.getLearnedSpells().contains(Bonus.BALAYETTE));

        this.r.addBonusToRenaud(Bonus.VIOLENCE);
        assertEquals(this.r.getBonusList().size(), 3);
        assertTrue(this.r.getBonusList().contains(Bonus.VIOLENCE));
    }

    @Test
    void testGetBonusDrawList(){
        this.bonusList.add(Bonus.LANCE_DE_BRIQUE);
        this.bonusList.add(Bonus.POING_BOUCHE);
        this.bonusList.add(Bonus.BARQUETTE_DE_FRITTES);
        this.bonusList.add(Bonus.COCKTAIL_MOLOTOV);

        this.bonusDrawList = Bonus.getBonusList();
        this.bonusDrawList.remove(Bonus.LANCE_DE_BRIQUE);
        this.bonusDrawList.remove(Bonus.POING_BOUCHE);
        this.bonusDrawList.remove(Bonus.BARQUETTE_DE_FRITTES);
        this.bonusDrawList.remove(Bonus.COCKTAIL_MOLOTOV);

        this.r.addBonusToRenaud(Bonus.POING_BOUCHE);
        this.r.addBonusToRenaud(Bonus.BARQUETTE_DE_FRITTES);
        this.r.addBonusToRenaud(Bonus.COCKTAIL_MOLOTOV);
        
        for (Bonus bonus : this.r.getBonusDrawList()) {
            assertTrue(this.bonusDrawList.contains(bonus));
        }
    }

    @Test
    void testGetLearnedSpells(){
        this.learnedSpells.add(Bonus.LANCE_DE_BRIQUE);
        this.learnedSpells.add(Bonus.POING_BOUCHE);
        this.learnedSpells.add(Bonus.BARQUETTE_DE_FRITTES);
        this.learnedSpells.add(Bonus.COCKTAIL_MOLOTOV);

        this.r.addBonusToRenaud(Bonus.POING_BOUCHE);
        this.r.addBonusToRenaud(Bonus.BARQUETTE_DE_FRITTES);
        this.r.addBonusToRenaud(Bonus.COCKTAIL_MOLOTOV);
        
        for (Bonus bonus : this.r.getLearnedSpells()) {
            assertTrue(this.learnedSpells.contains(bonus));
        }
    }

    @Test
    void testAddLearnedSpells(){
        this.learnedSpells.add(Bonus.LANCE_DE_BRIQUE);
        this.learnedSpells.add(Bonus.POING_BOUCHE);
        this.learnedSpells.add(Bonus.BARQUETTE_DE_FRITTES);
        this.learnedSpells.add(Bonus.COCKTAIL_MOLOTOV);

        this.r.addLearnedSpells(Bonus.POING_BOUCHE);
        this.r.addLearnedSpells(Bonus.BARQUETTE_DE_FRITTES);
        this.r.addLearnedSpells(Bonus.COCKTAIL_MOLOTOV);
        
        for (Bonus bonus : this.r.getLearnedSpells()) {
            assertTrue(this.learnedSpells.contains(bonus));
        }
    }

    @Test
    void testSetAtkAndGetAtk(){
        assertEquals(this.r.getAtk(), 15);
        this.r.setAtk(10);
        assertEquals(this.r.getAtk(), 10);
        this.r.setAtk(25);
        assertEquals(this.r.getAtk(), 25);
    }

    @Test
    void testgetExpNeededAndSetExpNeeded(){
        assertEquals(this.r.getExpNeeded(), 100);
        this.r.setExpNeeded(120);
        assertEquals(this.r.getExpNeeded(), 120);
        this.r.setExpNeeded(180);
        assertEquals(this.r.getExpNeeded(), 180);
    }

    @Test
    void testgetExpCurrentAndSetExpCurrent(){
        assertEquals(this.r.getExpCurrent(), 0);
        this.r.setExpCurrent(120);
        assertEquals(this.r.getExpCurrent(), 120);
        this.r.setExpCurrent(10);
        assertEquals(this.r.getExpCurrent(), 10);
    }

    @Test
    void testGetStageAndSetStage(){
        assertEquals(this.r.getStage(), 0);
        this.r.setStage(5);
        assertEquals(this.r.getStage(), 5);
        this.r.setStage(2);
        assertEquals(this.r.getStage(), 2);
    }

    @Test
    void testGetRoomAndSetRoom(){
        assertEquals(this.r.getRoom(), 0);
        this.r.setRoom(5);
        assertEquals(this.r.getRoom(), 5);
        this.r.setRoom(2);
        assertEquals(this.r.getRoom(), 2);
    }

    @Test
    void testGiveExpTest(){
        assertEquals(r.getExpCurrent(),0 );
        assertEquals(r.getExpNeeded(),100 );
        assertEquals(r.getLevel(),1 );
        r.giveExpTest(50);
        assertEquals(r.getExpCurrent(),50 );
        r.giveExpTest(50);
        assertEquals(r.getExpCurrent(),0 );
        assertEquals(r.getLevel(),2 );
        assertEquals(r.getExpNeeded(),120 );
    }

    @Test
    void testNextRoomTest(){
        assertEquals(this.r.getRoom(),0);
        this.r.nextRoomTest();
        assertEquals(this.r.getRoom(),1);
        this.r.nextRoomTest();
        assertEquals(this.r.getRoom(),2);
        this.r.nextRoomTest();
        assertEquals(this.r.getRoom(),3);
        this.r.nextRoomTest();
        assertEquals(this.r.getRoom(),4);
        this.r.nextRoomTest();
        assertEquals(this.r.getRoom(),0);
        assertEquals(this.r.getStage(),1);
    }

}
