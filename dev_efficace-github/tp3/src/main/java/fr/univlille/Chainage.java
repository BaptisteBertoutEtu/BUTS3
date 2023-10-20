package main.java.fr.univlille;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class Chainage implements HashTable {
    public static final int DEFAULT_LENGTH = 16 ;
    private final int TAILLE;
    LinkedList<HashCouple>[] table ;

    
    @SuppressWarnings("unchecked")
    public Chainage(int taille) {
        this.TAILLE=taille;
        this.table = (LinkedList<HashCouple>[]) Array.newInstance(LinkedList.class, taille);
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<HashCouple>();
        }
    }

    public Chainage() {
        this(Chainage.DEFAULT_LENGTH);
    }

    @Override
    public void clear() {
        for (LinkedList<HashCouple> linkedList : table) {
            linkedList.clear();
        }
    }

    @Override
    public Integer get(String key) {
        HashCouple hc = this.getCouple(key);
        return hc==null?null:hc.getValue();
    }

    public HashCouple getCouple(String key){
        LinkedList<HashCouple> ll = this.table[Math.floorMod(((String)key).hashCode(), this.TAILLE)];
        for (HashCouple hashCouple : ll) {
            if(hashCouple.getKey().equals((String)key)) return hashCouple;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        for (LinkedList<HashCouple> linkedList : table) {
            if(!linkedList.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        LinkedList<HashCouple> ll = this.table[Math.floorMod(((Integer)value).hashCode(), this.TAILLE)];
        for (HashCouple hashCouple : ll) {
            if(hashCouple.getValue().equals(((Integer)value))) return true;
        }
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        if(!(key instanceof String)) return false;
        return getCouple((String)key)!=null;
    }

    @Override
    public Integer put(String key, Integer value) {
        if (this.containsKey(key)){
            HashCouple hc = this.getCouple(key);
            this.getCouple(key).setValue((Integer)value);
            return hc.getValue();
        }
        this.table[Math.floorMod(key.hashCode(), this.TAILLE)].add( new HashCouple(key, value));
        return null;
    }

    @Override
    public int size() {
        int somme = 0;
        for (LinkedList<HashCouple> linkedList : table) {
            somme+=linkedList.size();
        }
        return somme;
    }

    @Override
    public Integer remove(Object key) {
        HashCouple hc = this.getCouple((String) key);
        this.table[Math.floorMod(key.hashCode(), this.TAILLE)].remove(key);
        return hc.getValue();
    }
}