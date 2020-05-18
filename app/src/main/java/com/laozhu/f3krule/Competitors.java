package com.laozhu.f3krule;

import java.util.ArrayList;
import java.util.HashMap;

public class Competitors {
    private HashMap<String, Competitor> competitorsHashMap = new HashMap<>();
    private ArrayList<Competitor> competitorsArrayList = new ArrayList<>();


    public void appendCompetor(Competitor competitor){
        Competitor competitorFound = competitorsHashMap.get(competitor.getName());
        if(competitorFound != null){
            competitorsHashMap.remove(competitorFound.getName());
            competitorsArrayList.remove(competitorFound);
        }
        competitorsArrayList.add(competitor);
        competitorsHashMap.put(competitor.getName(), competitor);
    }

    public void clear() {
        competitorsHashMap.clear();
        competitorsArrayList.clear();
    }


    public Competitor getCompetion(String name){
        return competitorsHashMap.get(name);
    }

    public ArrayList<Competitor> getCompetionsArrayList() {
        return competitorsArrayList;
    }
}
