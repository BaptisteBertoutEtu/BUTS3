package test.java.fr.univlille;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import main.java.fr.univlille.Participant;

public class ParticipantTest {
    @BeforeEach
    public static void setUp() {
        Participant.reset();
    }

    @Test
    public void testToString() {

        Participant p1 = new Participant("Dupont", "Paul");
        Participant p2 = new Participant("Durand", "Pierre");

        assertEquals("Dupont Paul 1", p1.toString());
        assertEquals("Durand Pierre 2", p2.toString());
        Participant.reset();
        Participant p3 = new Participant("Smith", "John");
        assertEquals("Smith John 1", p3.toString());
        Participant.reset();

    }
}