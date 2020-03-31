package com.laozhu.f3krule;

import java.util.ArrayList;

public class Competitor {
    ArrayList<RoundAction> roundActions = new ArrayList<>();
    String name;

    void setName(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }

    void addRound(Round round){

    }

    public static Competitor newCompetitor(String name){
        Competitor competitor = new Competitor();
        competitor.setName(name);
        return competitor;
    }

}
