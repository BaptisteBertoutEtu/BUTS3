package test.java.fr.univlille;

/**
 * Dev. efficace -*- Seance TP 1 -*- Exercice 2
 * Tests pour la liste chaînée <code>MyList</code>
 *
 * @author <a href="mailto:patricia.everaere-caillier@univ-lille.fr">Patricia Everaere-Frédéric
 *         Guyomarch</a>, IUT, Universite de Lille
 */


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {
    private MyList<Integer> list;

    @BeforeEach

    public void setUp() {
        list = new MyList<Integer>();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(20);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testClear() {
        assertTrue(list.isEmpty());
        list.add(33);
        list.add(45);
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testClearPointer() {
        assertNull(list.next);
        list.add(33);
        list.add(45);
        assertNotNull(list.next);
        list.clear();
        assertNull(list.next);
    }

    @Test
    public void testToString() {
        assertEquals("[]", list.toString());
        list.add(33);
        list.add(45);
        list.add(7);
        assertEquals(list.toString(), "[33, 45, 7]");
    }

    /******************************************************/

    @Test
    public void testAddE() {
        System.out.println(list.size() + " : " + list.toString());

        assertTrue(list.add(33));
        System.out.println(list.size() + " : " + list.toString());

        assertTrue(list.add(45));
        System.out.println(list.size() + " : " + list.toString());

        assertTrue(list.add(7));
        System.out.println(list.size() + " : " + list.toString());

        assertEquals(Integer.valueOf(33), list.data);
        assertEquals(Integer.valueOf(45), list.next.data);
        assertEquals(Integer.valueOf(7), list.next.next.data);
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());
        list.add(33);
        list.add(45);
        list.add(7);
        assertEquals(3, list.size());
        list.remove(0);
        assertEquals(2, list.size());
        list.remove(1);
        assertEquals(1, list.size());
        list.remove(0);
        assertEquals(0, list.size());
        list.add(7);
        assertEquals(1, list.size());
    }

    @Test
    public void testGet() {
        list.add(33);
        list.add(45);
        list.add(7);
        assertEquals(Integer.valueOf(45), list.get(1));
        assertNotEquals(Integer.valueOf(33), list.get(2));

    }

    @Test
    public void testGetWrongIndex1() {
        list.add(33);
        list.add(45);
        list.add(7);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void testGetWrongIndex2() {
        list.add(33);
        list.add(45);
        list.add(7);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));

        ;
    }

    @Test
    public void testIndexOf() {
        list.add(33);
        list.add(45);
        list.add(7);
        list.add(33);

        assertEquals(1, list.indexOf(Integer.valueOf(45)));
        assertEquals(0, list.indexOf(Integer.valueOf(33)));
        assertEquals(2, list.indexOf(Integer.valueOf(7)));
        assertEquals(-1, list.indexOf(Integer.valueOf(25)));
    }

    @Test
    public void testLastIndexOf() {
        list.add(33);
        list.add(45);
        list.add(7);
        list.add(33);

        assertEquals(3, list.lastIndexOf(Integer.valueOf(33)));
        assertEquals(2, list.lastIndexOf(Integer.valueOf(7)));
        assertEquals(-1, list.lastIndexOf(Integer.valueOf(25)));
    }

    @Test
    public void testContains() {
        list.add(33);
        list.add(45);
        list.add(7);
        assertTrue(list.contains(Integer.valueOf(33)));
        assertFalse(list.contains(Integer.valueOf(8)));
    }

    @Test
    public void testAddIntE() {
        list.add(5);
        list.add(4);
        list.add(33);
        assertEquals(Integer.valueOf(33), list.next.next.data);
        list.add(0, 96);
        assertEquals(Integer.valueOf(96), list.data);
        assertEquals(Integer.valueOf(33), list.next.next.next.data);
        list.add(2, 18);
        assertEquals(Integer.valueOf(18), list.next.next.data);
        assertEquals(Integer.valueOf(33), list.next.next.next.next.data);
    }

    @Test
    public void testRemoveObject() {
        list.add(5);
        list.add(4);
        list.add(33);
        assertFalse(list.remove(Integer.valueOf(20))); 
        assertEquals(Integer.valueOf(33), list.next.next.data);
        assertTrue(list.remove(Integer.valueOf(33)));
        list.add(Integer.valueOf(35));
        assertNotEquals(Integer.valueOf(33), list.next.next.data);
    }

    @Test
    public void testRemoveInt() {
        list.add(5);
        list.add(4);
        list.add(33);
        assertEquals(Integer.valueOf(33), list.remove(2));
        assertEquals(Integer.valueOf(4), list.next.data);
        assertEquals(Integer.valueOf(5), list.remove(0));
        assertEquals(Integer.valueOf(4), list.data);
        assertEquals(Integer.valueOf(4), list.remove(0));
        assertTrue(list.isEmpty());
    }

    @Test
    public void testRemoveFromEmpty() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        ;

    }

    @Test
    public void testRemoveWrongIndex1() {
        list.add(2);
        list.add(5);
        list.add(21);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    public void testRemoveWrongIndex2() {
        list.add(2);
        list.add(5);
        list.add(21);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));
    }

}
