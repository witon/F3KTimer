package com.laozhu.f3krule;

import java.util.ArrayList;

public class Competitor extends Entity {
    private ArrayList<RoundAction> roundActions = new ArrayList<>();

    public Competitor(String name) {
        super(name);
    }

    void addRound(Round round){

    }

    public static Competitor newCompetitor(String name){
        Competitor competitor = new Competitor(name);
        competitor.setName(name);
        return competitor;
    }

}
