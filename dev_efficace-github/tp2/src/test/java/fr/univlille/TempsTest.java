package test.java.fr.univlille;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.fr.univlille.Temps;

public class TempsTest {
    Temps t1, t2, t3, t4;

    @BeforeEach
    public void setup() {
        t1 = new Temps(150);
        t2 = new Temps(400);
        t3 = new Temps(-50);
        t4 = new Temps(5000);
    }

    @Test
    public void testToString() {
        assertEquals("0:2:30", t1.toString());
        assertEquals("0:6:40", t2.toString());
        assertEquals("0:0:0", t3.toString());
        assertEquals("1:23:20", t4.toString());
    }

    @Test
    public void testAdd() {
        assertEquals("1:30:0", Temps.add(t2, t4).toString());
        assertEquals("0:9:10", Temps.add(t1, t2).toString());
    }

}
