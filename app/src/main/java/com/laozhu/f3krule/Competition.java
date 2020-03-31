package com.laozhu.f3krule;

import java.util.ArrayList;
import java.util.HashMap;

public class Competition {
    private String name = null;
    ArrayList<Round> rounds = new ArrayList<>();
    HashMap<String, Competitor> competitors = new HashMap<>();
    ArrayList<Group> groups = new ArrayList<>();

    public String getName(){
        return name;
    }
    public Competition(String name){
        this.name = name;
    }

    /**
     * 添加一个轮次
     *
     * @param round
     */
    void addRound(Round round) {
    }


    /**
     * 增加一个参赛者
     *
     * @param competitor
     */
    public void addCompetitor(Competitor competitor) {
        competitors.put(competitor.getName(), competitor);

    }

    public Competitor getCompetitor(String name){
        return competitors.get(name);
    }
}
