package test.java.fr.univlille.cell;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import main.java.fr.univlille.model.cell.CellEvent;
import main.java.fr.univlille.model.cell.Coordinate;

public class CellEventTest {
    private Coordinate tempCoord;

    @Before
    public void init(){
        this.tempCoord = new Coordinate(5, 5);
    }
    

    @Test
    public void getState_should_equals_to_WALL(){
        CellEvent event = new CellEvent(CellInfo.WALL, tempCoord , 0);
        assertEquals(event.getState(), CellInfo.WALL);
    }

    @Test
    public void getState_should_equals_to_EMPTY(){
        CellEvent event = new CellEvent(CellInfo.EMPTY, tempCoord , 0);
        assertEquals(event.getState(), CellInfo.EMPTY);
    }

    @Test
    public void getState_should_equals_to_MONSTER(){
        CellEvent event = new CellEvent(CellInfo.MONSTER, tempCoord , 0);
        assertEquals(event.getState(), CellInfo.MONSTER);
    }

    @Test
    public void getState_should_equals_to_EXIT(){
        CellEvent event = new CellEvent(CellInfo.EXIT, tempCoord , 0);
        assertEquals(event.getState(), CellInfo.EXIT);
    }

    @Test
    public void getState_should_equals_to_HUNTER(){
        CellEvent event = new CellEvent(CellInfo.HUNTER, tempCoord , 0);
        assertEquals(event.getState(), CellInfo.HUNTER);
    }

    @Test 
    public void getCoord_should_equals_to_tempCoord_coordinate(){
        Coordinate temp = new Coordinate(5,5);
        CellEvent event = new CellEvent(null, temp , 0);
        assertEquals(event.getCoord(), temp);
        assertEquals(event.getCoord().getCol(), 5);
        assertEquals(event.getCoord().getRow(), 5);
    }

    @Test
    public void getTurn_should_return_3(){
        CellEvent event = new CellEvent(null, tempCoord , 3);
        assertEquals(event.getTurn(), 3);
    }

    @Test
    public void getTurn_should_return_18(){
        CellEvent event = new CellEvent(null, tempCoord , 18);
        assertEquals(event.getTurn(), 18);
    }

    @Test
    public void isMonsterPassed_should_be_false(){
        CellEvent event = new CellEvent(CellInfo.MONSTER, tempCoord , -1);
        assertFalse(event.isMonsterPassed());
    }

    @Test
    public void isMonsterPassed_should_be_true(){
        CellEvent event = new CellEvent(CellInfo.MONSTER, tempCoord , 1);
        assertTrue(event.isMonsterPassed());
    }

    @Test
    public void isMonster_should_be_false(){
        CellEvent event = new CellEvent(CellInfo.EMPTY, tempCoord, 0);
        assertFalse(event.isMonster());
    }

    @Test
    public void isMonster_should_be_true(){
        CellEvent event = new CellEvent(CellInfo.MONSTER, tempCoord, 0);
        assertTrue(event.isMonster());
    }

    @Test
    public void isEmpty_should_be_false(){
        CellEvent event = new CellEvent(CellInfo.EXIT, tempCoord, 0);
        assertFalse(event.isEmpty());
    }

    @Test
    public void isEmpty_should_be_true(){
        CellEvent event = new CellEvent(CellInfo.EMPTY, tempCoord, 0);
        assertTrue(event.isEmpty());
    }

    @Test
    public void isWall_should_be_false(){
        CellEvent event = new CellEvent(CellInfo.EMPTY, tempCoord, 0);
        assertFalse(event.isWall());
    }

    @Test
    public void isWall_should_be_true(){
        CellEvent event = new CellEvent(CellInfo.WALL, tempCoord, 0);
        assertTrue(event.isWall());
    }

    @Test
    public void isHunter_should_be_false(){
        CellEvent event = new CellEvent(CellInfo.EMPTY, tempCoord, 0);
        assertFalse(event.isHunter());
    }

    @Test
    public void isHunter_should_be_true(){
        CellEvent event = new CellEvent(CellInfo.HUNTER, tempCoord, 0);
        assertTrue(event.isHunter());
    }

}
