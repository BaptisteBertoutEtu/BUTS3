package test.java.fr.univlille.parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.fr.univlille.model.parameters.AllParameters;
import main.java.fr.univlille.model.parameters.Parameters;

public class ParametersTest {
    private Parameters para;

    @Before
    public void initialisation(){
        this.para = new Parameters();
    }

    @Test
    public void getHeight_should_return_20(){
        this.para.setHeight(20);
        assertTrue(this.para.getHeight()==20);
    }

    @Test 
    public void decrementHeight_should_decrement_one(){
        this.para.setHeight(20);
        this.para.decrementHeight();
        assertTrue(this.para.getHeight()==19);
    }

    @Test 
    public void incrementHeight_should_increment_one(){
        this.para.setHeight(20);
        this.para.incrementHeight();
        assertTrue(this.para.getHeight()==21);
    }

    @Test
    public void getWidth_should_return_30(){
        this.para.setWidth(30);
        assertTrue(this.para.getWidth()==30);
    }

    @Test 
    public void decrementWidth_should_decrement_one(){
        this.para.setWidth(30);
        this.para.decrementWidth();
        assertTrue(this.para.getWidth()==29);
    }

    @Test 
    public void incrementWidth_should_increment_one(){
        this.para.setWidth(30);
        this.para.incrementWidth();
        assertTrue(this.para.getWidth()==31);
    }

    @Test
    public void movement_should_change_and_return_MOVEMENT_8_WITHOUT_PASSING_WALL(){
        this.para.movementEightWithoutPassingWall();
        assertEquals(AllParameters.MOVEMENT_8_WITHOUT_PASSING_WALL, Parameters.movement);
    }

    @Test
    public void movement_should_change_and_return_MOVEMENT_8_WITH_PASSING_WALL(){
        this.para.movementEightWithPassingWall();
        assertEquals(AllParameters.MOVEMENT_8_WITH_PASSING_WALL, Parameters.movement);
    }

    @Test
    public void movement_should_change_and_return_MOVEMENT_4(){
        this.para.movementFour();
        assertEquals(AllParameters.MOVEMENT_4, Parameters.movement);
    }

    @Test
    public void knowledge_should_change_and_return_COMPLETE_KNOWLEDGE(){
        this.para.knowledgeComplete();
        assertEquals(AllParameters.COMPLETE_KNOWLEDGE, Parameters.labyrinthKnowledge);
    }

    @Test
    public void knowledge_should_change_and_return_PARTIAL_KNOWLEDGE(){
        this.para.knowledgePartial();
        assertEquals(AllParameters.PARTIAL_KNOWLEDGE, Parameters.labyrinthKnowledge);
    }

    @Test
    public void getPartialKnowledgeValue_should_change_and_return_5(){
        this.para.setPartialKnowledgeValue(5);
        assertTrue(this.para.getPartialKnowledgeValue()==5);
    }

    @Test
    public void incrementValue_should_increment_one(){
        this.para.setPartialKnowledgeValue(4);
        this.para.incrementValue();
        assertTrue(this.para.getPartialKnowledgeValue()==5);
    }

    @Test
    public void decrementValue_should_decrement_one(){
        this.para.setPartialKnowledgeValue(4);
        this.para.decrementValue();
        assertTrue(this.para.getPartialKnowledgeValue()==3);
    }

    @Test
    public void choiseLaby_should_change_and_return_LABYRAND(){
        this.para.labyRandom();
        assertEquals(AllParameters.LABYRAND, Parameters.choiseLaby);
    }

    @Test
    public void choiseLaby_should_change_and_return_LABYPREDEF(){
        String path = "chemin_vers_labyrinthe";
        this.para.setLabyPredef(path);
        assertEquals(AllParameters.LABYPREDEF, Parameters.choiseLaby);
        assertEquals(Parameters.pathForLabyPredef, path);
    }

    @Test
    public void gameMode_should_be_BOTH_PLAYER(){
        this.para.setPlayModeMonster(0);
        this.para.setPlayModeHunter(0);
        assertEquals(Parameters.gameMode, AllParameters.BOTH_PLAYER);
    }

    @Test
    public void gameMode_should_be_BOTH_IA(){
        this.para.setPlayModeMonster(1);
        this.para.setPlayModeHunter(1);
        assertEquals(Parameters.gameMode, AllParameters.BOTH_AI);
    }

    @Test
    public void gameMode_should_be_MONSTER_AI_ONLY(){
        this.para.setPlayModeMonster(1);
        this.para.setPlayModeHunter(0);
        assertEquals(Parameters.gameMode, AllParameters.MONSTER_AI_ONLY);
    }

    @Test
    public void gameMode_should_be_HUNTER_AI_ONLY(){
        this.para.setPlayModeMonster(0);
        this.para.setPlayModeHunter(1);
        assertEquals(Parameters.gameMode, AllParameters.HUNTER_AI_ONLY);
    }

    @Test
    public void getProba_should_return_5(){
        this.para.setProba(5);
        assertEquals(this.para.getProba(), 5, 0.01);
    }

    @Test
    public void incrementProbaLittle_should_increment_by_0_01(){
        this.para.setProba(0);
        this.para.incrementProbaLittle();
        assertEquals(this.para.getProba(), 0.01,0.01);
    }

    @Test
    public void decrementProbaLittle_should_decrement_by_0_01(){
        this.para.setProba(1);
        this.para.decrementProbaLittle();
        assertEquals(this.para.getProba(), 0.99,0.01);
    }

    @Test
    public void incrementProbaMid_should_increment_by_0_05(){
        this.para.setProba(0);
        this.para.incrementProbaMid();
        assertEquals(this.para.getProba(), 0.05,0.01);
    }

    @Test
    public void decrementProbaMid_should_decrement_by_0_05(){
        this.para.setProba(1);
        this.para.decrementProbaMid();
        assertEquals(this.para.getProba(), 0.95,0.01);
    }

    @Test
    public void incrementProbaBig_should_increment_by_1(){
        this.para.setProba(0);
        this.para.incrementProbaBig();
        assertEquals(this.para.getProba(), 0.1,0.01);
    }

    @Test
    public void decrementProbaBig_should_decrement_by_1(){
        this.para.setProba(1);
        this.para.decrementProbaBig();
        assertEquals(this.para.getProba(), 0.9, 0.01);
    }

    @Test
    public void setZoneForSpanwn_should_set_a_zone_of_10(){
        Parameters.setZoneForSpanwn();
        assertEquals(Parameters.zoneForSpawnAroundExit, 10);
    }

    @Test
    public void updateParameters_should_update(){
        this.para.setWidth(15);
        this.para.setHeight(10);
        this.para.setPartialKnowledgeValue(6);
        this.para.setProba(4);
        this.para.updateParameters();
        assertEquals(Parameters.lengthLabyrinth[0], 15);
        assertEquals(Parameters.lengthLabyrinth[1], 10);
        assertEquals(Parameters.mouvementPartialKnowledgeValue, 6);
        assertEquals(Parameters.probabilityForWall, 4,0);
    }

}
