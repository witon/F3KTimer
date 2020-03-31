package com.laozhu.f3krule;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupTest {
    @Test
    public void addCompetitor() {
        Competitor competitor = Competitor.newCompetitor("Tom");
        Group group = new Group();
        group.addCompetitor(competitor);
        Competitor competitorResult = group.getCompetitorByName("Tom");
        assertEquals("Tom", competitorResult.getName());
    }

}
