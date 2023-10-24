package fr.univlille;

/*
 * Copyright (C) 2018 University of Lille
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests for a priority queue
 * @author <a href="mailto:Frederic.Guyomarch@univ-lille1.fr">Frédéric Guyomarch</a>, IUT-A
 * @date 2022-09-28
 */


public class BSTPriorityQueueTest {

	@Test
	public void testIsEmpty() {
		PriorityQueueInterface<Integer> tas = new BSTPriorityQueue<>();
		assertTrue(tas.isEmpty());
		tas.offer(20);
		assertFalse(tas.isEmpty());
	}

	@Test
	public void testMakeEmpty() {
		PriorityQueueInterface<Integer> tas = new BSTPriorityQueue<>();
		tas.offer(20);
		tas.clear();
		assertTrue(tas.isEmpty());
	}

	@Test
	public void testToString() {
		PriorityQueueInterface<Integer> tas = new BSTPriorityQueue<>();
		assertEquals(tas.toString(), "[]");
		tas.offer(14);
		tas.offer(10);
		tas.offer(16);
		assertEquals("[10, 14, 16, ]", tas.toString());
	}

	@Test                                 
	public void testOffer() {
		PriorityQueueInterface<Integer> tas = new BSTPriorityQueue<>();
		assertTrue(tas.offer(33));
		assertTrue(tas.offer(45));
		assertTrue(tas.offer(7));
		assertTrue(tas.offer(20));
		assertTrue(tas.offer(10));
		assertTrue(tas.offer(50));
		assertEquals("[7, 10, 20, 33, 45, 50, ]", tas.toString());
	}

	@Test
	public void testSize() {
		PriorityQueueInterface<Integer> tas = new BSTPriorityQueue<>();
		assertEquals(0, tas.size());
		tas.offer(33);
		tas.offer(45);
		tas.offer(7);
		assertEquals(3, tas.size());
		tas.offer(17);
		tas.offer(1);
		assertEquals(5, tas.size());
	}

	@Test
	public void testPoll() {
		PriorityQueueInterface<Integer> tas = new BSTPriorityQueue<>();
		assertEquals(null, tas.poll());
		tas.offer(5);
		tas.offer(4);
		tas.offer(33);
		assertEquals(3, tas.size());
		assertEquals(Integer.valueOf(4), tas.poll());
		assertEquals("[5, 33, ]", tas.toString());
		assertEquals(2, tas.size());
		assertEquals(Integer.valueOf(5), tas.poll());
		assertEquals(1, tas.size());
		assertEquals(Integer.valueOf(33), tas.poll());
		assertTrue(tas.isEmpty());
	}

	@Test
	public void testPeek() {
		PriorityQueueInterface<Integer> tas = new BSTPriorityQueue<>();
		tas.offer(5);
		tas.offer(4);
		tas.offer(33);
		assertEquals(Integer.valueOf(4), tas.peek());
		tas.offer(2);
		assertEquals(Integer.valueOf(2), tas.peek());
		tas.offer(3);
		assertEquals(Integer.valueOf(2), tas.peek());
	}
}

