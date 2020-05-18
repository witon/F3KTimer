package com.laozhu.f3krule;

import java.util.HashMap;

public class Group {
    private HashMap<String, Competitor> competitors = new HashMap();

    public void addCompetitor(Competitor competitor){
        competitors.put(competitor.getName(), competitor);
    }

    public HashMap<String, Competitor> getCompetitors(){
        return competitors;
    }

    public Competitor getCompetitorByName(String name){
        return competitors.get(name);
    }

}

