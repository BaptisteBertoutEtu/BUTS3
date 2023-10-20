package tp2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResultatTest {

    Resultat resultats;

    @BeforeEach
    public void setup() {
        Participant.reset();
        resultats = new Resultat();
    }

    @Test
    void test_initialisation_resultats() {
        Temps[] nullArray = new Temps[6];
        for (Participant participant: Inscrits.participants) {
            assertTrue(resultats.resultats.containsKey(participant));
            assertArrayEquals(nullArray, resultats.resultats.get(participant));
        }
    }

    @Test
    void test_ajout_temps_fructueux() {
        Participant participant = Inscrits.participants[1];
        assertTrue(resultats.ajout(participant, new Temps(4000), 0));
    }

    @Test
    void test_ajout_temps_echec() {
        Participant participant = new Participant("Toujours", "Pala");
        assertFalse(resultats.ajout(participant, new Temps(4000), 0));
    }

    @Test void test_ajout_d_un_temps() {
        Participant participant = Inscrits.participants[2];
        resultats.ajout(participant, new Temps(10300), 3);
        assertArrayEquals(
                new Temps[] { null, null, null, new Temps(10300), null, null } ,
                resultats.resultats.get(participant)
        );
    }

    @Test 
    void test_echec_sur_ajout_participant_absent() {
        Participant participant = new Participant("Callens", "Elise");
        assertFalse(resultats.ajout(participant, new Temps(18976), 4));
    }

    @Test 
    void test_echec_ajout_sur_etape_erronee() {
        Participant participant = Inscrits.participants[2];
        assertFalse(resultats.ajout(participant, new Temps(18976), -1));
        assertFalse(resultats.ajout(participant, new Temps(8349), 6));
    }

    @Test
    void resultatTest() {
        Participant p = Inscrits.participants[0];
        resultats.ajout(p, new Temps(4000), 0);

        // Vérification de l'affichage pour ce participant à l'étape 0
        assertEquals("Dupont Paul 1 0->1:6:40", resultats.resultat(p, 0));

        // Vérification du comportement pour ce participant à une autre étape
        assertEquals("Dupont Paul 1 1->pas présent", resultats.resultat(p, 1));

        // Vérification du comportement pour un autre participant
        Participant participant = new Participant("Pala", "illé");
        assertNull(resultats.resultat(participant, 1));
    }

    
    @Test 
    void test_string_resultat() {
        Participant participant = Inscrits.participants[1];
        resultats.ajout(participant, new Temps(7209), 4);
        assertEquals("Durand Pierre 2 4->2:0:9", resultats.resultat(participant, 4));
    }

    @Test 
    void test_string_resultat_pour_participant_absent() {
        Participant participant = new Participant("Kajack", "Marie");
        String display = resultats.resultat(participant, 2);
        assertEquals(null, display);
    }

    @Test 
    void test_string_resultat_pour_etape_non_renseignee() {
        Participant participant = Inscrits.participants[0];
        String display = resultats.resultat(participant, 2);
        assertEquals("Dupont Paul 1 2->pas présent", display);
    }

    @Test 
    void test_afficheEtape() {
        resultats.ajout(Inscrits.participants[0], new Temps(3760), 2);
        resultats.ajout(Inscrits.participants[1], new Temps(4809), 2);
        resultats.ajout(Inscrits.participants[2], new Temps(2998), 2);
        resultats.ajout(Inscrits.participants[3], new Temps(3240), 2);
        resultats.ajout(Inscrits.participants[4], new Temps(3907), 2);
        assertEquals( "Etape 2\n" +
                "Dupont Paul 1 : 1:2:40\n" +
                "Durand Pierre 2 : 1:20:9\n" +
                "Smith Aline 3 : 0:49:58\n" +
                "Riviere Louis 4 : 0:54:0\n" +
                "Audic Laura 5 : 1:5:7\n",
                resultats.afficheEtape(2));
    }

    @Test 
    void test_temps_global() {
        resultats.ajout(Inscrits.participants[0], new Temps(15863), 0);
        resultats.ajout(Inscrits.participants[0], new Temps(20632), 1);
        resultats.ajout(Inscrits.participants[0], new Temps(19954), 2);
        resultats.ajout(Inscrits.participants[0], new Temps(14578), 3);
        resultats.ajout(Inscrits.participants[0], new Temps(23555), 4);
        resultats.ajout(Inscrits.participants[0], new Temps(22004), 5);
        assertEquals(new Temps(15863+20632+19954+14578+23555+22004), resultats.tempsGlobal(Inscrits.participants[0]));
    }

}