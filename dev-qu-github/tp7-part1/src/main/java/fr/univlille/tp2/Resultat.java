package fr.univlille.tp2;

import java.util.*;

public class Resultat {
	private static final int NB_ETAPES = 6;
	Map<Participant, Temps[]> resultats;
	Map<String, Set<Participant>> annuaire;

	public Resultat() {
		resultats = new TreeMap<>();
		annuaire = new TreeMap<>();
		for (Participant participant: Inscrits.participants) {
			String nom = participant.nom;
			if (annuaire.containsKey(nom)) {
				annuaire.get(nom).add(participant);
			} else {
				Set<Participant> set = new HashSet<>();
				set.add(participant);
				annuaire.put(nom, set);
			}
			resultats.put(participant, new Temps[NB_ETAPES]);
		}
	}

	public boolean ajout(Participant participant, Temps temps, int etape) {
		if (! resultats.containsKey(participant)) return false;
		if ((etape < 0) || (etape >= NB_ETAPES)) return false;
		Temps[] scores = this.resultats.get(participant);
		scores[etape] = temps;
		return true;
	}
	
	
	public String resultat(Participant participant, int etape) {
		Temps[] temps = this.resultats.get(participant);
		if (temps == null) return null;
		else return resultatEtapeToString(participant, etape, temps[etape]);
	}


	private static String resultatEtapeToString(Participant participant, int etape, Temps temps) {
		StringBuilder resultat = new StringBuilder();
		resultat.append(participant).append(' ').append(etape).append("->");
		if (temps == null) resultat.append("pas présent");
		else resultat.append(temps); 
		return resultat.toString();
	}

	public String afficheEtape(int etape) {
		StringBuilder cumul = new StringBuilder("Etape ").append(etape).append('\n');
		for (Map.Entry<Participant, Temps[]> entry: resultats.entrySet()) {
			cumul.append(entry.getKey()).append(" : ").append(entry.getValue()[etape]).append('\n');
		}
		return cumul.toString();
	}

	@Override
	public String toString() {
		StringBuilder cumul = new StringBuilder();
		for (Map.Entry<Participant, Temps[]> entry: resultats.entrySet()) {
			Temps[] scores = entry.getValue();
			cumul.append(entry.getKey()).append(" : ").append(Arrays.toString(scores)).append('\n');
		}
		return cumul.toString();
	}

	public Temps tempsGlobal(Participant inscrit) {
		Temps global = new Temps(0);
		Temps[] scores = resultats.get(inscrit);
		for (int etape = 0; etape < NB_ETAPES; etape++) {
			global = Temps.add(global, scores[etape]);
		}
		return global;
	}

	public Set<Participant> rechercheSequentielle(String nom) {
		Set<Participant> returned = new HashSet<>();
		for (Participant participant : resultats.keySet()) {
			if (nom == participant.nom) {
				returned.add(participant);
			}
		}
		return returned;
	}
	
	public Set<Participant> recherche(String nom) {
		if (annuaire.containsKey(nom)) {
			return annuaire.get(nom);
		} else {
			return new HashSet<>();
		}
	}

	public static void main(String[] args) {
		Resultat resultats = new Resultat();
		resultats.ajout(Inscrits.participants[0], new Temps(7843), 0);
		System.out.println(resultats);
	}
}
