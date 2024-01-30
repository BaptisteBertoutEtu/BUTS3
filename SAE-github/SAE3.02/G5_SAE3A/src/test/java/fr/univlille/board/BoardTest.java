package test.java.fr.univlille.board;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import main.java.fr.univlille.model.board.Board;

public class BoardTest {
    // private boolean[][] maze;

    // @Before
    // public void setUp(){
    //     boolean[][] test = {
    //         {true, true, true, false, true},
    //         {true, false, false, true, true},
    //         {true, true, false, true, true},
    //         {true, false, true, false, true},
    //         {true, true, true, true, true}
    // };

    // maze = test;
        
    // }

    // @Test
    // public void test_resolution_labi(){
    //     assertEquals(true, );
    // }
    
    // @Test
    // public void testInitialize() {
    //     int height = 15;
    //     int width = 15;
    //     boolean[][] maze = Board.initialize(height, width);

    //     assertNotNull(maze);
    //     assertEquals(height, maze.length);
    //     assertEquals(width, maze[0].length);
    // }
    
    // @Test
    // void test_initialize_tableau_monster(){
    //     assertEquals(5, monsterBoard.length);
    //     assertEquals(5, monsterBoard[0].length);
    // }
    
    // @Test
    // void test_monsterBoard_contains_monster(){
    //     // On instancie Game pour avoir accès au gameTurn
    //     new Game();
    //     assertTrue(parcours(CellInfo.MONSTER));
    // }

    // @Test
    // void test_monsterBoard_contains_exit(){
    //     assertTrue(parcours(CellInfo.EXIT));
    // }

    // @Test
    // void test_monsterBoard_contains_empty(){
    //     assertTrue(parcours(CellInfo.EMPTY));
    // }

    // @Test
    // void test_monsterBoard_contains_wall(){
    //     assertTrue(parcours(CellInfo.WALL));
    // }
    
    // private boolean parcours(CellInfo info){
    //     for (Cell[] cells : monsterBoard) {
    //         for (Cell cell : cells) {
    //             if(info.equals(CellInfo.EMPTY) && cell.isEmpty()) return true;
    //             if(info.equals(CellInfo.WALL) && cell.isWall()) return true;
    //             if(info.equals(CellInfo.MONSTER) && cell.isMonster()) return true;
    //             if(info.equals(CellInfo.EXIT) && cell.isExit()) return true;
    //         }
    //     }
    //     return false;
    // }

}
