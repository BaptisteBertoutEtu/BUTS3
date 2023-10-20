package main.java.fr.univlille;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Resultat {
    public Map<Participant,Temps[]> resultats;
    private static final int TAILLE = 6;


    public Resultat(){
        this.resultats = new TreeMap<Participant,Temps[]>();
        for (int i = 0; i < TAILLE-1; i++) {
            this.resultats.put(Inscrits.INSCRITS[i], new Temps[TAILLE]);
        }
    }

    public boolean ajout(Participant p, Temps t, int i){
        if (!this.resultats.containsKey(p)) return false;
        if (i>=TAILLE || i<0) return false;
        Temps[] newList = this.resultats.get(p);
        newList[i] = t;
        this.resultats.put(p, newList);
        return true;
    }

    public String resultat(Participant p, int i){
        if(!this.resultats.containsKey(p)) return null;
        StringBuilder sb = new StringBuilder(p.toString()+" ");
        Temps[] newList = this.resultats.get(p);
        sb.append(i+"->");
        if (newList[i]==null) {
            sb.append("pas présent");
        }
        else{
            sb.append(newList[i].toString());
        }
        return sb.toString();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Participant,Temps[]> entry : this.resultats.entrySet()) {
            for (int i = 0; i < TAILLE; i++) {
                sb.append(this.resultat(entry.getKey(), i)+"\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String afficheEtape(int i){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Participant,Temps[]> entry : this.resultats.entrySet()) {
            sb.append(this.resultat(entry.getKey(), i)+"\n");
            sb.append("\n");
        }
        return sb.toString();
    }

    private Temps tempsGlobal(Participant participant){
        Temps[] t = this.resultats.get(participant);
        int tempsGlobal = 0;
        if(!this.aParticiperATout(participant)) return null;
        for (Temps temps : t) {
            tempsGlobal+=temps.getTempsCoureur();
        }
        return new Temps(tempsGlobal);
    }

    private boolean aParticiperATout(Participant p){
        Temps[] newList = this.resultats.get(p);
        for (Temps temps : newList) {
            if(temps==null) return false;
        }
        return true;
    }

    public Participant gagnant(){
        Temps tempsMax = new Temps(0);
        Participant gagnant = new Participant(null, null);
        Temps tempT;
        for (Map.Entry<Participant,Temps[]> entry : this.resultats.entrySet()) {
            tempT =  this.tempsGlobal(entry.getKey());
            if(tempsMax.getTempsCoureur() > tempT.getTempsCoureur()) {
                tempsMax = tempT;
                gagnant = entry.getKey();
            }
        }
        return gagnant;
    }

    public static void main(String[] args) {
        Resultat r = new Resultat();
        // System.out.println(r.toString());
        System.out.println(r.afficheEtape(2));
    }

}
