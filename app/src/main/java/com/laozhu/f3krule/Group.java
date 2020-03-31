package com.laozhu.f3krule;

import java.util.ArrayList;
import java.util.HashMap;

public class Group {
    private HashMap<String, Competitor> competitors = new HashMap();

    void addCompetitor(Competitor competitor){
        competitors.put(competitor.getName(), competitor);
    }

    HashMap<String, Competitor> getCompetitors(){
        return competitors;
    }

    Competitor getCompetitorByName(String name){
        return competitors.get(name);
    }

}
