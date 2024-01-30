package test.java.fr.univlille.cell;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

import main.java.fr.univlille.model.cell.Coordinate;

public class CoordinateTest {
    
    @Test
    public void getCol_should_return_5(){
        Coordinate coord = new Coordinate(5, 0);
        assertEquals(coord.getCol(), 5);
    }

    @Test
    public void getRow_should_return_5(){
        Coordinate coord = new Coordinate(0, 5);
        assertEquals(coord.getRow(), 5);
    }

    @Test
    public void two_coordinate_should_be_equals(){
        Coordinate coord1 = new Coordinate(5, 5);
        Coordinate coord2 = new Coordinate(5, 5);

        assertEquals(coord1, coord2);
    }

    @Test
    public void two_coordinate_should_not_be_equals(){
        Coordinate coord1 = new Coordinate(5, 0);
        Coordinate coord2 = new Coordinate(0, 5);

        assertNotEquals(coord1, coord2);
    }

}
