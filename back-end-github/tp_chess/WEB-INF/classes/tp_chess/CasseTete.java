package tp_chess;

public class CasseTete {
	
	
	/**
	 * Soit une ligne de pion Blanc ou Noir représentés par des caractères.
	 * B : blanc
	 * N : noir
	 */
	private char[] ligne = new char[7];
	
	
	/**
	 * Au début, il y a trois pions blancs à droite, trois pions noirs à gauche.
	 * Il y a un espace au milieu.
	 * 
	 * Le but sera d'amener tous les pions blanc à droite et les pions noirs à gauche.
	 */
	public CasseTete() {
		"NNN BBB".getChars(0, ligne.length, ligne, 0);
	}
	
	
	/**
	 * Permet de savoir ce qu'il y a à la case i de la ligne.
	 * 
	 * @param i devrait être compris entre 0 et 6 inclus
	 * @return un caractère B, N ou espace
	 */
	public char get(int i) {
		return ligne[i];
	}
	
	
	/**
	 * Cherche vers quelle case peut avancer le pion de la case i.
	 * Un pion blanc se déplace à gauche, un pion noir à droite.
	 * Un pion se déplace d'une case, ou peut sauter un seul pion de l'autre couleur.
	 * 
	 * @param i est la case du pion à déplacer
	 * @return l'indice de la case où le pion arrive, -1 si il ne peut pas bouger.
	 */
	public int destination(int i) {
		char c = get(i);
		if ( c == ' ' ) return -1;
		int j = i;
		
		for ( int k = 0; k < 2; k++ ) {
			if ( c == 'B' ) j = j - 1;
			if ( c == 'N' ) j = j + 1;
			if ( j < 0 || j > 6 ) return -1;
			if ( get(j) == ' ' ) {
				return j;
			}
		}
		
		return -1;
	}
	
	
	/**
	 * Essaye d'avancer le pion de la case i
	 * 
	 * @param i est la case du pion que l'on veut déplacer
	 * @return vrai si le pion a pu être déplacé
	 */
	public boolean jouer(int i) {
		int d = destination(i);
		if ( d < 0 ) return false;
		
		ligne[d] = get(i);
		ligne[i] = ' ';
		return true;
	}
	
	/**
	 * teste si le jeu est fini
	 */
	public boolean estFini() {
		for ( int i = 0; i < ligne.length; i++ ) {
			if ( destination(i) >= 0 ) return false;
		}
		return true;
	}
	
	
	/**
	 * Teste si la partie est gagnée
	 * @return vrai si c'est gagné
	 */
	public boolean gagne() {
		return new String(ligne).equals("BBB NNN");
	}
	
	/**
	 * Fournit la représentation texte du jeu actuel
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for (int i=0; i < ligne.length; i++)
			sb.append(get(i));
		return sb.toString();
	}
	
}
