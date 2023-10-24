package fr.univlille;

public class Main {
    
    /**
	 * Un main pour répondre à la question 1 et essayer les méthodes.
	 */
	public static void main(String[] args) {
		// La liste de trigrammes
		String[] trigrammes = new String[]{
				"Ala",
				"Arg",
				"Asn",
				"Asp",
				"Cys",
				"Gln",
				"Glu",
				"Gly",
				"His",
				"Ile",
				"Leu",
				"Lys",
				"Met",
				"Phe",
				"Pro",
				"Ser",
				"Thr",
				"Trp",
				"Tyr",
				"Val"
		};
		// La liste des noms à associer aux trigrammes,
		// dans le même ordre !
		String[] noms = new String[]{
				"Alanine",
				"Arginine",
				"Asparagine",
				"Aspartate",
				"Cystéine",
				"Glutamine",
				"Glutamate",
				"Glycine",
				"Histidine",
				"Isoleucine",
				"Leucine",
				"Lysine",
				"Méthionine",
				"Phénylalanine",
				"Proline",
				"Sérine",
				"Thréonine",
				"Tryptophane",
				"Tyrosine",
				"Valine"
		};
		// On instancie les noeuds
		@SuppressWarnings("unchecked")
		BST<String, String>[] bsts = (BST<String, String>[]) new BST[trigrammes.length];
		for(int i=0; i<bsts.length; ++i)
			bsts[i] = new BST<String, String>(trigrammes[i], noms[i]);
		
		// On crée les liens de filiation
		bsts[10].left = bsts[7];
		bsts[10].right = bsts[14];
		bsts[7].left = bsts[0];
		bsts[7].right = bsts[8];
		bsts[0].right = bsts[4];
		bsts[4].left = bsts[3];
		bsts[4].right = bsts[5];
		bsts[3].left = bsts[1];
		bsts[1].right = bsts[2];
		bsts[5].right = bsts[6];
		bsts[8].right = bsts[9];
		bsts[14].left = bsts[13];
		bsts[14].right = bsts[18];
		bsts[13].left = bsts[11];
		bsts[11].right = bsts[12];
		bsts[18].left = bsts[15];
		bsts[18].right = bsts[19];
		bsts[17].left = bsts[16];
		bsts[15].right = bsts[17];

		// On lui donne un nom et on l'affiche
		BST<String, String> bst = bsts[10];
		// System.out.println(bst.toStringDebug());

		bst.infixe();
		System.out.println(bst.toString());
	}
}
