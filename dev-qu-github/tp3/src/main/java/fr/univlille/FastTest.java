package main.java.fr.univlille;

public class FastTest {
    
    public static void main(String[] args) {
        
        Chocolat c = new Chocolat(new Chantilly(new Gaufre())) ;
        System.out.println(c.getName());
        System.out.println(c.getPrice());
    }
}
