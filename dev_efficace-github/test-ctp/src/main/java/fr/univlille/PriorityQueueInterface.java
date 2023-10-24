/*
 * Copyright (c) 2003, 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * Modified by  <a href="mailto:frederic.guyomarch@univ-lille.fr">
 * Frédéric Guyomarch</a> (ULille) for the ressource R3.02 of the BUT
 * in Computer Sciences.
 * 
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.univlille;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.IntFunction;

/**
 * An unbounded priority {@linkplain Queue queue}.
 * The elements of the priority queue are ordered according to their
 * {@linkplain Comparable natural ordering}, or by a {@link Comparator}
 * provided at queue construction time, depending on which constructor is
 * used.  A priority queue does not permit {@code null} elements.
 * A priority queue relying on natural ordering also does not permit
 * insertion of non-comparable objects (doing so may result in
 * {@code ClassCastException}).
 *
 * <p>The <em>head</em> of this queue is the <em>least</em> element
 * with respect to the specified ordering. The queue retrieval
 *  operations {@code poll}, {@code remove}, {@code peek}, and 
 *  {@code element} access the element at the head of the queue.
 *
 * @since 1.5
 * @author Josh Bloch, Doug Lea modified by Frédéric Guyomarch
 * @param <E> the type of elements held in this queue
 */
public interface PriorityQueueInterface<E> {
	/**
	 * Removes all of the elements from this priority queue.
	 * The queue will be empty after this call returns.
	 */
	public void clear();

	/**
	 * Returns the number of elements in this collection.  If this collection
	 * contains more than {@code Integer.MAX_VALUE} elements, returns
	 * {@code Integer.MAX_VALUE}.
	 *
	 * @return the number of elements in this collection
	 */
	public int size();

	/**
	 * Returns {@code true} if this collection contains no elements.
	 *
	 * @return {@code true} if this collection contains no elements
	 */
	public boolean isEmpty();

	/**
	 * Returns the comparator used to order the elements in this
	 * queue, or {@code null} if this queue is sorted according to
	 * the {@linkplain Comparable natural ordering} of its elements.
	 *
	 * @return the comparator used to order this queue, or
	 *         {@code null} if this queue is sorted according to the
	 *         natural ordering of its elements
	 */
	public Comparator<? super E> comparator();

	/**
	 * Inserts the specified element into this priority queue.
	 *
	 * @return {@code true} (as specified by {@link Queue#offer})
	 * @throws ClassCastException if the specified element cannot be
	 *         compared with elements currently in this priority queue
	 *         according to the priority queue's ordering
	 * @throws NullPointerException if the specified element is null
	 */
	public boolean offer(E e); 

	/**
	 * Retrieves, but does not remove, the head of this queue,
	 * or returns {@code null} if this queue is empty.
	 *
	 * @return the head of this queue, or {@code null} if this queue is empty
	 */
	public E peek();

	/**
	 * Retrieves and removes the head of this queue,
	 * or returns {@code null} if this queue is empty.
	 *
	 * @return the head of this queue, or {@code null} if this queue is empty
	 */
	public E poll();

	/**
	 * Returns an array containing all of the elements in this collection;
	 * the runtime type of the returned array is that of the specified array.
	 * If the collection fits in the specified array, it is returned therein.
	 * Otherwise, a new array is allocated with the runtime type of the
	 * specified array and the size of this collection.
	 *
	 * <p>If this collection fits in the specified array with room to spare
	 * (i.e., the array has more elements than this collection), the element
	 * in the array immediately following the end of the collection is set to
	 * {@code null}.  (This is useful in determining the length of this
	 * collection <i>only</i> if the caller knows that this collection does
	 * not contain any {@code null} elements.)
	 *
	 * <p>If this collection makes any guarantees as to what order its elements
	 * are returned by its iterator, this method must return the elements in
	 * the same order.
	 *
	 * @apiNote
	 * This method acts as a bridge between array-based and collection-based APIs.
	 * It allows an existing array to be reused under certain circumstances.
	 * Use {@link #toArray()} to create an array whose runtime type is {@code Object[]},
	 * or use {@link #toArray(IntFunction)} to control the runtime type of
	 * the array.
	 *
	 * <p>Suppose {@code x} is a collection known to contain only strings.
	 * The following code can be used to dump the collection into a previously
	 * allocated {@code String} array:
	 *
	 * <pre>
	 *     String[] y = new String[SIZE];
	 *     ...
	 *     y = x.toArray(y);</pre>
	 *
	 * <p>The return value is reassigned to the variable {@code y}, because a
	 * new array will be allocated and returned if the collection {@code x} has
	 * too many elements to fit into the existing array {@code y}.
	 *
	 * <p>Note that {@code toArray(new Object[0])} is identical in function to
	 * {@code toArray()}.
	 *
	 * @param <T> the component type of the array to contain the collection
	 * @param a the array into which the elements of this collection are to be
	 *        stored, if it is big enough; otherwise, a new array of the same
	 *        runtime type is allocated for this purpose.
	 * @return an array containing all of the elements in this collection
	 * @throws ArrayStoreException if the runtime type of any element in this
	 *         collection is not assignable to the {@linkplain Class#getComponentType
	 *         runtime component type} of the specified array
	 * @throws NullPointerException if the specified array is null
	 */
	<T> T[] toArray(T[] a);

	/**
	 * Returns an iterator over the elements in this queue. The iterator
	 * does not return the elements in any particular order.
	 *
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator();


}
