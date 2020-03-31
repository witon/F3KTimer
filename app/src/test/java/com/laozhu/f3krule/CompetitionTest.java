package com.laozhu.f3krule;

import org.junit.Test;
import static org.junit.Assert.*;

public class CompetitionTest {

    @Test
    public void newCompetition() {
        Competition competition = new Competition("test competition");
        assertEquals("test competition", competition.getName());
    }
}
