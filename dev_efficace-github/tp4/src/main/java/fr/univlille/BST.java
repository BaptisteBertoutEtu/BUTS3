package fr.univlille;

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


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * M3103 - Algorithmique avancée - TP 4
 *         Arbres binaires de recherche
 *         
 * Dans ce TP nous allons implémenter l'interface <code>Map</code> à l'aide d'un
 * arbre binaire de recherche. Nous ne nous occupons absolument pas de l'équilibrage 
 * de l'arbre. La seule propriété que nous garantissons sur l'arbre, est qu'il soit
 * un arbre de recherche.      
 *
 * @param <K> le type des clefs
 * @param <V> le type des valeurs
 * 
 * @author Frédéric Guyomarch
 * @version 2020-10-21
 */
public class BST<K extends Comparable<K>, V> implements Map<K, V> {
	private final static String nl = System.getProperty("line.separator");
	
	/* Attributs */
	private K key;
	private V value;
	//private
	BST<K, V> left;
	//private
	BST<K, V> right;
	private final Comparator<? super K> comparator;

	/**
	 * Constructeurs
	 */
	private BST(K key, V value, BST<K,V> left, BST<K,V> right, 
			Comparator<? super K> comparator) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
		this.comparator = comparator;
	}

	private BST(K key, V value, BST<K,V> left, BST<K,V> right) {
		this(key, value, left, right, null);
	}
	/** Arbre vide avec <code>Comparator</code> */
	public BST(Comparator<? super K> comparator){
		this(null, null, null, null, comparator);
	}

	/** Arbre vide sans <code>Comparator</code> */
	public BST() {
		this(null);
	}

	/** Arbre réduit à une feuille avec <code>Comparator</code> */
	public BST(K key, V value, Comparator<? super K> comparator) {
		this.key = key;
		this.value = value;
		this.comparator = comparator;
		left = new BST<K,V>(comparator);
		right = new BST<K,V>(comparator);
	}

	/** Arbre réduit à une feuille sans <code>Comparator</code> */
	public BST(K key, V value) {
		this(key, value, null);
	}
	
	/**
	 * Méthode privée de comparaison enter clef pour rendre transparent
	 * la présence ou pas d'un <code>Comparator</code>. 
	 */
	private int compare(K key1, K key2){
		if (comparator != null)
			return comparator.compare(key1, key2);
		return ((Comparable<K>) key1).compareTo(key2);
	}
	
	/**
	 * Un main pour essayer les méthodes.
	 */
	public static void main(String[] args) {
		BST<Integer, String> bst8 = new BST<>(Integer.valueOf(8), "-8-");
		System.out.println(bst8);		
	}
	
	
	/**
	 * Modifie un BST existant et le remplace par la feuille contenant 
	 * la clef <code>key</code> associée à la valeur <code>value</code>
	 */
	private void createLeafBST(K key, V value) {
		this.key = key;
		this.value = value;
		left = new BST<>();
		right = new BST<>();
	}

	/**
	 * Recopie un BST existant et le place sur l'arbre courant
	 */
	private void copyBST(BST<K, V> bst) {
		if (this.comparator != bst.comparator)
			throw new UncompatibleComparatorException();
		key = bst.key;
		value = bst.value;
		left = bst.left;
		right = bst.right;
	}
	
	/**
	 * @return la clef
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @return la valeur
	 */
	public V getValue() {
		return value;
	}

	/**
	 * met à jour la valeur
	 * @return l'ancienne valeur
	 */
	public V setValue(V newValue) {
		V oldValue = value;
		value = newValue;
		return oldValue;
	}
	
	@Override
	public void clear() {
		key = null;
		value = null;
		left = null;
		right = null;
	}

	@Override
	public boolean containsKey(Object key) {
		try {
			return this.search(key).key.equals(key);
		} catch (ClassCastException e) {
			return false;
		}
	}

	/**
	 * Méthode privée qui localise l'emplacement d'une certaine clef, ou l'endroit où
	 * celle-ci devrait s'insérer s'il elle est absente.
	 * Résultat <code>null</code> si incompatibilité avec l'arbre.
	 */
	@SuppressWarnings("unchecked")
	// private
	BST<K, V> search(Object o) {
		if(isEmpty()) return this; 
		if(getKey().equals(o)) return this;
		if(compare(getKey(), (K)o) < 0)return this.left.search(o);
		return this.right.search(o);
	}

	@Override
	public V get(Object key) {
		BST<K, V> bst = search(key);
		if (bst!=null && bst.key.equals(key))
			return bst.value;
		return null;
	}

	@Override
	public V put(K key, V value) {
		BST<K, V> s = search(key);
		if(s.isEmpty()){
			s.createLeafBST(key, value);
			return null;
		}
		V oldValue = s.value;
		s.value = value;
		return oldValue;
	}

	@Override
	public V remove(Object k) {
		BST<K, V> bst = search(k);
		if (bst==null)
			return null;
		V ret = bst.value;
		if (bst.left.isEmpty())
			bst.copyBST(bst.right);
		else if (bst.right.isEmpty())
			bst.copyBST(bst.left);
		else {
			Map.Entry<K, V> oldEntry = right.deleteMin();
			this.key=oldEntry.getKey();
			this.value = oldEntry.getValue();
		}
		return ret;
	}

	/**
	 * Supprime le minimum d'un <code>BST</code> et retourne le couple supprimé
	 */
	private Map.Entry<K, V> deleteMin() {
		BST<K, V> bstMin = bstMin();
		Map.Entry<K, V> tmp = new AbstractMap.SimpleEntry<K, V>(bstMin.key, bstMin.value);
		bstMin.copyBST(bstMin.right);
		return tmp;
	}

	/**
	 * Localise le minimum d'un <code>BST</code>
	 */
	public BST<K, V> bstMin() {
		if(isEmpty()) return this;
		return this.left.bstMin();
	}
	
	@Override
	public int size() {
		if(isEmpty()) return 0;
		return 1 + this.left.size() + this.right.size();
	}

	@Override
	public boolean isEmpty() {
		return key == null;
	}

	public boolean isLeave() {
		return key!=null && left.isEmpty() && right.isEmpty();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Entry<? extends K, ? extends V> me : m.entrySet())
			put(me.getKey(), me.getValue());
	}		

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<K> keySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<V> values() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsValue(Object value) {
		if(isEmpty()) return false;
		if(getValue().equals(value)) return true;
		return this.left.containsValue(value) || this.right.containsValue(value);
	}

	/**
	 * Parcours infixe d'un <code>BST</code>
	 * @return La liste des <code>Entry</code> dans l'ordre infixe
	 */
	public List<Map.Entry<K, V>> infixe(){
		if(isEmpty()) return new ArrayList<Map.Entry<K,V>>();
		List<Map.Entry<K,V>> temp = new ArrayList<Map.Entry<K,V>>();
		temp.addAll(this.left.infixe());
		temp.add(Map.entry(key, value));
		temp.addAll(this.right.infixe());
		return temp;
	}

	/**
	 * Parcours préfixe d'un <code>BST</code>
	 * @return La liste des <code>Entry</code> dans l'ordre préfixe
	 */
	public List<Map.Entry<K, V>> prefixe(){
		if(isEmpty()) return new ArrayList<Map.Entry<K,V>>();
		List<Map.Entry<K,V>> temp = new ArrayList<Map.Entry<K,V>>();
		temp.add(Map.entry(key, value));
		temp.addAll(this.left.infixe());
		temp.addAll(this.right.infixe());
		return temp;
	}
	
	@Override
	// Affiche infixe sur une ligne
	public String toString() {
		return infixe().toString();// + nl + prefixe().toString();
	}

	/*
	 * return version textuelle de l'arbre (ASCII art ?)
	 * en une seule <code>String</code> en utilisant le séparateur
	 * de lignes du système.
	 */
	public String toStringDebug() {
		String res = new String();
		for(String s : toStringArray()){
			res += s + nl;
		}
		return res;
	}

	/**
	 * Pour l'affichage de l'arbre en plusieurs lignes,
	 * chaque ligne dans une <code>String</code>.
	 * @return le tableau de des lignes
	 */
	public String[] toStringArray(){
		if (isEmpty())
			return new String[0];
		String lOne = key+"->"+value;
		lOne = "<" + lOne + ">";
		if (isLeave())
			return new String[]{lOne};
		String[] lBot = left.toStringArray();
		String[] rBot = right.toStringArray();
		String[] bot;
		int nbSpacesL = 0;
		int nbSpacesR = 0;

		if (left.isEmpty()){
			bot = BSTutils.stringArrayConcatH(lOne.length(), rBot);
			nbSpacesR = rBot[0].length();
		}
		else if(right.isEmpty()){
			bot = BSTutils.stringArrayConcatH(lBot, lOne.length());
			nbSpacesL = lBot[0].length();
		}
		else {
			bot = BSTutils.stringArrayConcatH(lBot, rBot, lOne.length());
			nbSpacesR = rBot[0].length();
			nbSpacesL = lBot[0].length();
		}

		int ios = BSTutils.indexOfSlash(lBot); 
		int iob = BSTutils.indexOfBackslash(rBot); 

		String[] top = new String[]{
				BSTutils.nSpaces(nbSpacesL)	+ lOne + BSTutils.nSpaces(nbSpacesR),

				BSTutils.nSpaces(ios+1) + BSTutils.nUnderscores(nbSpacesL-ios-1) + BSTutils.leftLeg(left.isEmpty()) 
				+ BSTutils.nSpaces(lOne.length()-2) 
				+ BSTutils.rightLeg(right.isEmpty()) + BSTutils.nUnderscores(iob) + BSTutils.nSpaces(nbSpacesR-iob-1), 

				BSTutils.nSpaces(ios) + BSTutils.leftLeg(left.isEmpty())
				+ BSTutils.nSpaces(lOne.length() - ios + nbSpacesL + iob - 1) 
				+  BSTutils.rightLeg(right.isEmpty()) + BSTutils.nSpaces(nbSpacesR-iob-1)};

		return BSTutils.stringArrayConcatV(top, bot);
	}
}

