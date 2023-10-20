package tp2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    @BeforeEach
    public void setUp() {
        Participant.reset();
    }

    @Test
    void testToString() {

        Participant p1 = new Participant("Dupont", "Paul");
        Participant p2 = new Participant("Durand", "Pierre");

        assertEquals("Dupont Paul 1", p1.toString());
        assertEquals("Durand Pierre 2", p2.toString());
        Participant.reset();
        Participant p3 = new Participant("Smith", "John");
        assertEquals("Smith John 1", p3.toString());
        Participant.reset();

    }

    @Test void test_creating_a_participant() {
        Participant participant = new Participant("DUPONT", "Jean");
        assertEquals(1, participant.dossard);
        assertEquals("DUPONT", participant.nom);
        assertEquals("Jean", participant.prenom);
    }

    @Test void test_creating_many_participants() {
        Participant.reset();
        Participant participant1 = new Participant("DUPONT", "Jean");
        assertEquals(1, participant1.dossard);
        assertEquals("DUPONT", participant1.nom);
        assertEquals("Jean", participant1.prenom);

        Participant participant2 = new Participant("DURANT", "Jacques");
        assertEquals(2, participant2.dossard);
        assertEquals("DURANT", participant2.nom);
        assertEquals("Jacques", participant2.prenom);

        Participant participant3 = new Participant("MARTIN", "Sylvie");
        assertEquals(3, participant3.dossard);
        assertEquals("MARTIN", participant3.nom);
        assertEquals("Sylvie", participant3.prenom);
    }
}