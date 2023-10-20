package main.java.fr.univlille;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class ListUtils {
    private static final int MAX = 30;
    private static final int MAXINT = 100;
    private static final Random R = new Random();

    public static List<Integer> genereRdmIntList(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < R.nextInt(1,MAX+1); i++) {
            list.add(R.nextInt(MAXINT));
        }
        return list;
    }

    public static void toScreen(List<Integer> l){
        Iterator<Integer> LUIterator = l.iterator();
        StringBuilder sc = new StringBuilder(LUIterator.next());
        while (LUIterator.hasNext()) {
            sc.append("->" + LUIterator.next());
        }
        System.out.println(sc);
    }

    public static void reverseToScreen(List<Integer> l){
        ListIterator<Integer> LUIterator = l.listIterator(l.size());
        StringBuilder sc = new StringBuilder(LUIterator.previous());
        while (LUIterator.hasPrevious()) {
            sc.append("->" + LUIterator.previous());
        }
        System.out.println(sc);
    }

    public static int somme(List<Integer> l){
        int i = 0;
        for (Integer integer : l) {
            i+=integer;
        }
        return i;
    }

    public static int min(List<Integer> l){
        int min = l.get(0);
        for (Integer integer : l) {
            if(integer<min) min = integer;
        }
        return min;
    }

    public static List<Integer> positions(List<Integer> l, int n){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Integer integer : l) {
            if(integer == n) list.add(integer);
        }
        return list;
    }
}
