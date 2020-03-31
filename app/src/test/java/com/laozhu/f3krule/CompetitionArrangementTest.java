package com.laozhu.f3krule;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompetitionArrangementTest {

    private void newCompetition(CompetitionArrangement competitionArrangement) {
        Competition competition = competitionArrangement.newCompetition("test competition");
        assertEquals("test competition", competition.getName());
        competition = competitionArrangement.getCompetition();
        assertEquals("test competition", competition.getName());
    }

    private void addCompetitor(CompetitionArrangement competitionArrangement){
        Competition competition = competitionArrangement.newCompetition("test competition");
        Competitor competitor = Competitor.newCompetitor("Jerry");
        competition.addCompetitor(competitor);
        competitor = competition.getCompetitor("Jerry");
        assertEquals("Jerry", competitor.getName());
    }

    private void addRound2Competition(CompetitionArrangement competitionArrangement){
        Task task = new TaskLastFlight();
        Round round = competitionArrangement.addRound2Competition(task);
        task = round.getTask();
        assertEquals(TaskLastFlight.class.getName(), task.getClass().getName());
    }

    @Test
    public void testArrangement(){
        CompetitionArrangement competitionArrangement = new CompetitionArrangement();
        newCompetition(competitionArrangement);
        addCompetitor(competitionArrangement);
        addRound2Competition(competitionArrangement);
    }
}
