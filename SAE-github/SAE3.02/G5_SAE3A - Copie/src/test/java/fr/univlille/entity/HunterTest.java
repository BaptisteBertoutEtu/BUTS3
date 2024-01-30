package test.java.fr.univlille.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.model.entity.Hunter;
import main.java.fr.univlille.model.entity.Monster;

public class HunterTest {
    private Hunter hunter;
    private int width = 5;
    private int height = 5;
    private Monster monstre;
    private boolean[][] maze;

    @BeforeEach
    public void init() {
        hunter = new Hunter();
        monstre = new Monster();
        maze = new boolean[5][5];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = true;
            }
        }
        monstre.initialize(maze);
        hunter.initialize(width, height);
        hunter.attach(monstre);
        monstre.attach(hunter);
    }

    @Test
    public void testInitialize() {
        assertEquals(width, monstre.getMaze().length);
        assertEquals(height, monstre.getMaze()[0].length);
    }

    @Test
    public void testShoot() {
        Coordinate coord = new Coordinate(1, 1);
        hunter.shoot(coord);
        assertEquals(monstre.getLastShoot().getCol(), coord.getCol());
        assertEquals(monstre.getLastShoot().getRow(), coord.getRow());
    }
}
