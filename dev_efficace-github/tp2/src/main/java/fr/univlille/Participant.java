package main.java.fr.univlille;

public class Participant implements Comparable<Participant>{
    private final Integer NODOSSARD;
    private final String NOM;
    private final String PRENOM;
    private static int numAuto = 1;

    public Participant(String nom, String prenom){
        this.NOM = nom;
        this.PRENOM = prenom;
        this.NODOSSARD = Participant.numAuto;
        Participant.numAuto++;
    }

    public Integer getDossard(){
        return this.NODOSSARD;
    }

    public static void reset(){
        Participant.numAuto = 1;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(this.NOM);
        sb.append(" "+this.PRENOM);
        sb.append(" "+this.NODOSSARD);
        return sb.toString();
    }

    @Override
    public int compareTo(Participant o) {
        return this.NODOSSARD-o.getDossard();
    }
}
