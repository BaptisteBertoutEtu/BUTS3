package tp2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TempsTest {
    Temps t1, t2, t3, t4;

    @BeforeEach
    public void setup() {
        t1 = new Temps(150);
        t2 = new Temps(400);
        t3 = new Temps(-50);
        t4 = new Temps(5000);
    }

    @Test
    void testToString() {
        assertEquals("0:2:30", t1.toString());
        assertEquals("0:6:40", t2.toString());
        assertEquals("0:0:0", t3.toString());
        assertEquals("1:23:20", t4.toString());
    }
    																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																	
    @Test
    void testAdd() {
        assertEquals("1:30:0", Temps.add(t2, t4).toString());
        assertEquals("0:9:10", Temps.add(t1, t2).toString());
    }


    @Test void create_temps_and_test_toString() {
        Temps temps = new Temps(8046);
        assertEquals("2:14:6", temps.toString());
    }

    @Test void test_addition_temps() {
        Temps temps1 = new Temps(5687) ;
        Temps temps2 = new Temps(368) ;
        Temps somme = new Temps(5687 + 368);
        assertEquals(somme, Temps.add(temps1, temps2));
    }
}
