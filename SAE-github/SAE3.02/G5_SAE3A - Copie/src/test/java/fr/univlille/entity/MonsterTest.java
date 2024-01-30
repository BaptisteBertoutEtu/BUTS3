package test.java.fr.univlille.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.model.entity.Hunter;
import main.java.fr.univlille.model.entity.Monster;
import main.java.fr.univlille.model.exception.UnsupportedMove;

public class MonsterTest {
    private Monster monster = new Monster();
    private boolean[][] maze;
    private Hunter hunter = new Hunter();
    private ICoordinate c;
    private ICoordinate shoot;

    @Before
    public void setup() {
        maze = new boolean[10][10];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = true;
            }
        }
        this.monster.initialize(maze);
        this.hunter.initialize(10, 10);
        monster.attach(hunter);
        hunter.attach(monster);
        Monster.setExit(new Coordinate(5, 5));
        shoot = new Coordinate(4, 4);
        c = new Coordinate(1, 1);
        Monster.setCurrentCoord(c);
    }

    @Test
    public void testMaze() {
        maze = monster.getMaze();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                assertTrue(maze[i][j]);
            }
        }
    }

    @Test
    public void testMove() throws UnsupportedMove{
        monster.initialize(maze);
        ICoordinate destination = new Coordinate(monster.getCurrentCoord().getCol(), monster.getCurrentCoord().getRow()+1);
        monster.move(destination);
        assertEquals(monster.getCurrentCoord().getCol(),destination.getCol());
        assertEquals(monster.getCurrentCoord().getRow(),destination.getRow());
        assertTrue(maze[monster.getLastCoord().getRow()][monster.getLastCoord().getCol()]);
        assertTrue(maze[destination.getRow()][destination.getCol()]);
    }


    @Test
    public void testLastShoot() {
        hunter.shoot(shoot);
        assertEquals(monster.getLastShoot(),shoot);
        assertEquals(monster.getLastShoot().getCol(), shoot.getCol());
        assertEquals(monster.getLastShoot().getRow(), shoot.getRow());
        assertNotEquals(monster.getExit(), shoot);
    }

    @Test
    public void testgetLastShoot() {
        ICoordinate shootCoord = new Coordinate(2, 2);
        hunter.shoot(shootCoord);

        ICoordinate lastShoot = monster.getLastShoot();

        assertEquals(shootCoord, lastShoot);
    }
}
