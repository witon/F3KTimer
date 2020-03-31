package com.laozhu.f3krule;

import org.junit.Test;
import static org.junit.Assert.*;

public class CompetitorTest {

    @Test
    public void setName() {
        Competitor competitor = new Competitor();
        competitor.setName("Tom");
        assertEquals("Tom", competitor.getName());
    }

    @Test
    public void newCompetitor(){
        Competitor competitor = Competitor.newCompetitor("Jerry");
        assertEquals("Jerry", competitor.getName());
    }
}
