package test.java.fr.univlille;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import main.java.fr.univlille.Inscrits;
import main.java.fr.univlille.Participant;
import main.java.fr.univlille.Resultat;
import main.java.fr.univlille.Temps;

import static org.junit.jupiter.api.Assertions.*;

class ResultatTest {

    Resultat resultats;

    @BeforeEach
    public void setup() {
        Participant.reset();
        resultats = new Resultat();
    }

    @Test
    public void test_ajout_temps_fructueux() {
        Participant participant = Inscrits.INSCRITS[1];
        assertTrue(resultats.ajout(participant, new Temps(4000), 0));
    }

    @Test
    public void test_ajout_temps_echec() {
        Participant participant = new Participant("Toujours", "Pala");
        assertFalse(resultats.ajout(participant, new Temps(4000), 0));
    }

    @Test
    public void resultatTest() {
        Participant p = Inscrits.INSCRITS[0];
        resultats.ajout(p, new Temps(4000), 0);

        // Vérification de l'affichage pour ce participant é l'étape 0
        assertEquals("Dupont Paul 1 0->1:6:40", resultats.resultat(p, 0));

        // Vérification du comportement pour ce participant é une autre étape
        assertEquals("Dupont Paul 1 1->pas présent", resultats.resultat(p, 1));

        // Vérification du comportement pour un autre participant
        Participant participant = new Participant("Pala", "illé");
        assertNull(resultats.resultat(participant, 1));
    }

    @Test
    void test_initialisation_resultats() {
        Temps[] nullArray = new Temps[6];
        for (Participant participant: Inscrits.INSCRITS) {
            assertTrue(resultats.resultats.containsKey(participant));
            assertArrayEquals(nullArray, resultats.resultats.get(participant));
        }
    }

    @Test @Timeout(1) void test_ajout_d_un_temps() {
        Participant participant = Inscrits.INSCRITS[2];
        resultats.ajout(participant, new Temps(10300), 3);
        assertArrayEquals(
                new Temps[] { null, null, null, new Temps(10300), null, null } ,
                resultats.resultats.get(participant)
        );
    }

    @Test void test_echec_sur_ajout_participant_absent() {
        Participant participant = new Participant("Callens", "Elise");
        assertFalse(resultats.ajout(participant, new Temps(18976), 4));
    }

    @Test 
    void test_echec_ajout_sur_etape_erronee() {
        Participant participant = Inscrits.INSCRITS[2];
        assertFalse(resultats.ajout(participant, new Temps(18976), -1));
        assertFalse(resultats.ajout(participant, new Temps(8349), 6));
    }

    @Test void test_string_resultat() {
        Participant participant = Inscrits.INSCRITS[1];
        resultats.ajout(participant, new Temps(7209), 4);
        assertEquals("Durand Pierre 2 4->2:0:9", resultats.resultat(participant, 4));
    }

    @Test void test_string_resultat_pour_participant_absent() {
        Participant participant = new Participant("Kajack", "Marie");
        String display = resultats.resultat(participant, 2);
        assertEquals(null, display);
    }

    @Test void test_string_resultat_pour_etape_non_renseignee() {
        Participant participant = Inscrits.INSCRITS[0];
        String display = resultats.resultat(participant, 2);
        assertEquals("Dupont Paul 1 2->pas présent", display);
    }
}