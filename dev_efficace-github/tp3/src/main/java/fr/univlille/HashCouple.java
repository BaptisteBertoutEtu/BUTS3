package main.java.fr.univlille;

import java.util.AbstractMap;

public class HashCouple extends AbstractMap.SimpleEntry<String, Integer>{
    
    public HashCouple(String key, Integer value){
        super(key, value);
    }

    public int hashCode(){
        return getKey().hashCode();
    }
}
