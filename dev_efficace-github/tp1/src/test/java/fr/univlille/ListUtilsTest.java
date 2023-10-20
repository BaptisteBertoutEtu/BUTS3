package test.java.fr.univlille;

/**
 * Dev. efficace -*- Seance TP 1 -*- Exercice 2
 * Tests pour la liste chaînée <code>MyList</code>
 * 
 * @author <a href="mailto:patricia.everaere-caillier@univ-lille.fr">Patricia Everaere-Frédéric
 *         Guyomarch</a>, IUT, Universite de Lille
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class ListUtilsTest {

	final int MAX = 100;
	final int MAX_SIZE = 30;
	final int MIN = 1;

	@Before
	public void setUp() {
	}

	@Test
	public void testGenereRdmIntList() {
		List<Integer> l = ListUtils.genereRdmIntList();
		boolean listOk = true;
		if ((!l.isEmpty()) && l.size() <= 30) {
			for (Integer i : l)
				if (i < MIN || i > MAX)
					listOk = false;
		} else
			listOk = false;

		assertTrue(listOk);
	}

	@Test
	public void testAffiche() {
		String separator = System.getProperty("line.separator");
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		List<Integer> l = new ArrayList<Integer>();
		System.setOut(ps);

		l.add(2);
		l.add(45);
		l.add(12);
		l.add(58);
		l.add(24);

		String expected = "2 -> 45 -> 12 -> 58 -> 24";

		ListUtils.toScreen(l);

		assertEquals(expected + separator, os.toString());
		System.setOut(originalOut);
	}

	@Test
	public void testAfficheInverse() {
		String separator = System.getProperty("line.separator");
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		List<Integer> l = new ArrayList<Integer>();
		System.setOut(ps);

		l.add(2);
		l.add(45);
		l.add(12);
		l.add(58);
		l.add(24);

		String expected = "24 -> 58 -> 12 -> 45 -> 2";

		ListUtils.reverseToScreen(l);

		assertEquals(expected + separator, os.toString());
		System.setOut(originalOut);
	}

	@Test
	public void testSomme() {
		List<Integer> l = new ArrayList<Integer>();
		l.add(45);
		l.add(12);
		l.add(58);
		l.add(24);
		assertEquals(139, ListUtils.somme(l));
	}

	@Test
	public void testMoyenne() {
		List<Integer> l = new ArrayList<Integer>();
		l.add(2);
		l.add(12);
		l.add(33);
		l.add(24);
		assertEquals(17, ListUtils.moyenne(l));
	}

	@Test
	public void testMax() {
		List<Integer> l = new ArrayList<Integer>();
		l.add(2);
		l.add(12);
		l.add(88);
		l.add(24);
		assertEquals(88, ListUtils.max(l));
	}

	@Test
	public void testMin() {
		List<Integer> l = new ArrayList<Integer>();
		l.add(2);
		l.add(12);
		l.add(1);
		l.add(24);
		assertEquals(1, ListUtils.min(l));
	}

	@Test
	public void testPositions() {
		List<Integer> l = new ArrayList<Integer>();
		List<Integer> lPositions = new ArrayList<Integer>();

		l.add(1);
		l.add(22);
		l.add(45);
		l.add(56);
		l.add(1);
		l.add(34);
		l.add(1);

		lPositions.add(0);
		lPositions.add(4);
		lPositions.add(6);

		assertEquals(lPositions, ListUtils.positions(l, 1));

	}

	@Test
	public void testEven() {
		List<Integer> l = new ArrayList<Integer>();
		List<Integer> lPaire = new ArrayList<Integer>();
		l.add(2);
		l.add(12);
		l.add(24);
		l.add(55);
		l.add(88);
		l.add(7);
		lPaire.add(2);
		lPaire.add(12);
		lPaire.add(24);
		lPaire.add(88);

		Collections.sort(ListUtils.even(l));
		Collections.sort(lPaire);
		assertEquals(lPaire, ListUtils.even(l));
	}

	@Test
	public void testIsSorted() {
		List<Integer> l = new ArrayList<Integer>();
		List<Integer> lTrie = new ArrayList<Integer>();

		l.add(88);
		lTrie.add(5);
		l.add(55);
		lTrie.add(12);
		l.add(36);
		lTrie.add(36);
		l.add(12);
		lTrie.add(55);
		l.add(5);
		lTrie.add(88);

		assertTrue(!ListUtils.isSorted(l) && ListUtils.isSorted(lTrie));
	}
}