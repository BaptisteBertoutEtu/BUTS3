/*
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
import java.util.Stack;

/**
 * BSTPriorityQueue
 *
 * <p>
 * cf le <a href="https://moodle.univ-lille.fr/pluginfile.php/2714501/mod_resource/content/3/r302_ctp2022_file_priorite_avec_arbre_de_recherche.pdf">sujet du contrôle TP</a>.
 *
 * <p>
 * @author <a href="mailto:prenom.nom.etu@univ-lille.fr">Prénom Nom</a> (Groupe S3-Z)
 * @version 5 Oct. 2022
 */
class BSTPriorityQueue<E> implements PriorityQueueInterface<E>,Comparable<E>, Iterable<E>{
	private BSTPriorityQueue<E> left;
    private BSTPriorityQueue<E> right;
    private E key;
    private final Comparator<? super E> comparator;

    private BSTPriorityQueue(E key, BSTPriorityQueue<E> left, BSTPriorityQueue<E> right, 
			Comparator<? super E> comparator) {
		this.key = key;
		this.left = left;
		this.right = right;
		this.comparator = comparator;
	}

    @SuppressWarnings("unused")
	private BSTPriorityQueue(E key, BSTPriorityQueue<E> left, BSTPriorityQueue<E> right) {
		this(key, left, right, null);
	}
	/** Arbre vide avec <code>Comparator</code> */
	public BSTPriorityQueue(Comparator<? super E> comparator){
		this(null, null, null, comparator);
	}

	/** Arbre vide sans <code>Comparator</code> */
	public BSTPriorityQueue() {
		this(null);
	}
	
	/*
	 * Question 2
	 */
	@Override
	public void clear() {
		this.key = null;
		this.left = null;
		this.right = null;
	}
	
	@Override
	public boolean isEmpty() {
		return this.key==null;
	}
	
	@Override
	public Comparator<? super E> comparator() {
		return this.comparator;
	}

	/*
	 * Question 3
	 */
	@Override
	public int size() {
		if(isEmpty()) return 0;
		return 1 + this.left.size() + this.right.size();
	}

	/*
	 * Question 4
	 */
	@Override
	public boolean offer(E e) {
        if (isEmpty()) {
            this.key = e;
            this.left = new BSTPriorityQueue<>();
            this.right = new BSTPriorityQueue<>();
            return true;
        }
		if (compareTo(e)<0) {
            return this.right.offer(e);
        }
        else if (compareTo(e)>0) {
            return this.left.offer(e);
        }
        else{
            return false;
        }

	}

    @Override
    public int compareTo(E o) {
        return (Integer) this.key - (Integer) o;
    }

	/*
	 * Question 5
	 */
	@Override
	public E peek() {
        if (this.left.isEmpty()) {
            return this.key;
        }
        return this.left.peek();
	}

	@Override
	public E poll() {
        if (isEmpty()) {
           return null; 
        }
		if (this.left.isEmpty()) {
            E el = this.key;
            if (this.right.isEmpty()) {
                this.clear();
            }
            else{
                this.key = this.right.key;
                this.right=this.right.right;
            }
            return el;
        }
        return this.left.poll();
	}

	/*
	 * Question 6
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		// je sais pas ce qu'il faut faire, j'ai pas compris
        return null;
	}

	/*
	 * Question 7
	 */
	
    @Override
    public String toString() {
        return "[" + infixe() + "]";
    }
    
    /**
     * Parcours infixe d'un <code>BST</code>
     * @return La liste des <code>Entry</code> dans l'ordre infixe
	 */
    public String infixe(){
        if(isEmpty()) return "";
		StringBuilder sb = new StringBuilder();
        sb.append(this.left.infixe());
        sb.append(this.key + ", ");
        sb.append(this.right.infixe());
        return sb.toString();
	}


    /*
     * Question 8
     */
    
    // Q 8.1

    private Stack<BSTPriorityQueue<E>> bstMinStack(){
        Stack<BSTPriorityQueue<E>> s = new Stack<BSTPriorityQueue<E>>();
        if(this.left.isEmpty()) {
            s.add(this);
            return s;
        } 
        return this.left.bstMinStack();
    }


    // Q 8.2
	public class BSTIterator implements Iterator<E> {
		protected Stack<BSTPriorityQueue<E>> chemin;
        private int index = 0;

        
		
		public BSTIterator(Stack<BSTPriorityQueue<E>> chemin) {
            this.chemin = chemin;
        }

        // Q 8.3

		private Stack<BSTPriorityQueue<E>> nextBST(){
            //je sais pas comment la faire et je sais pas a quoi elle sert
            return null;
		}
		
		// Q 8.4
		@Override
		public boolean hasNext() {
			return this.index != this.chemin.size();
		}

		@Override
		public E next() {
            return this.nextBST().get(0).key;
		}
		
		@Override
		public void remove() {
            //je sais pas ce qui faut faire donc je considere qu'on remove le dernier
            this.chemin.pop();
		}
		
	}
	
	// Q 8.5
	@Override
	public Iterator<E> iterator() {
		return new BSTIterator(bstMinStack());
	}

	/*
	 * Question 9
	 */
	/**
     * Sorts the specified array of objects according to the order induced by
     * the specified comparator.  All elements in the array must be
     * <i>mutually comparable</i> by the specified comparator (that is,
     * {@code c.compare(e1, e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the array).
     *
     * @param <T> the class of the objects to be sorted
     * @param a the array to be sorted
     * @param c the comparator to determine the order of the array.  A
     *        {@code null} value indicates that the elements'
     *        {@linkplain Comparable natural ordering} should be used.
     * @throws ClassCastException if the array contains elements that are
     *         not <i>mutually comparable</i> using the specified comparator
     * @throws IllegalArgumentException (optional) if the comparator is
     *         found to violate the {@link Comparator} contract
     */
    public static <T> void sort(T[] a, Comparator<? super T> c) {
		//flemme de la faire
	}
	
	/*
	 * Question 10
	 */
	public static void main(String[] args) {
        // sert a rien puisque j'ai pas la méthode sort()
	}
	
}
