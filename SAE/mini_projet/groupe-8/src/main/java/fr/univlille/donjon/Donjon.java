package fr.univlille.donjon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univlille.Mathf;
import fr.univlille.effect.Bonus;

public class Donjon {
    private DonjonFloor[] floors;
    private static Map<Bonus, Integer> spellInCD;

    
    public Donjon(){
        generateFloors();
        spellInCD = new HashMap<Bonus, Integer>();
    }
    
    public DonjonFloor[] getFloors(){
        return floors;
    }
    
    public int getFloorsCount(){
        return floors.length;
    }
    
    public int getRoomsCount(int stage){
        if(stage >= 0 && stage < floors.length) return floors[stage].getRoomsCount();
        return -1;
    }
    
    public DonjonFloor getFloor(int stage){
        if(stage >= 0 && stage < floors.length) return floors[stage];
        return null;
    }
    
    public DonjonRoom getRoom(int stage, int room){
        DonjonFloor floor = getFloor(stage);
        if(floor != null) return floor.getRoom(room);
        return null;
    }
    
    public Theme getTheme(int stage){
        DonjonFloor dj = getFloor(stage);
        if(dj == null) return null;
        return dj.getTheme();
    }
    
    public static Map<Bonus, Integer> getSpellInCD() {
        return spellInCD;
    }
    public void generateFloors(){
        Theme[] themes = Theme.values();

        floors = new DonjonFloor[themes.length];

        List<Theme> currentThemes = new ArrayList<>();
        for(Theme t : themes){
            if(t != Theme.FINALE){
                currentThemes.add(t);
            }
        }

        for(int i = 0; i < themes.length; i++){
            Theme theme;
            if(i == themes.length - 1){
                theme = Theme.FINALE;
            }else{
                theme = currentThemes.get(Mathf.random(currentThemes.size() - 1));
                currentThemes.remove(theme);
            }
            floors[i] = new DonjonFloor(theme);
        }
    }

    protected void print(){
        System.out.println(this);
        for(DonjonFloor df : floors) df.print(1);
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[floorsCount:" + getFloorsCount() + "]";
    }
}