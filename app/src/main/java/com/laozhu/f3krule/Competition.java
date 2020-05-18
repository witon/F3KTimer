package com.laozhu.f3krule;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 管理比赛的信息
 */
public class Competition extends Entity {
    ArrayList<Round> rounds = new ArrayList<>();
    HashMap<String, Competitor> competitors = new HashMap<>();
    ArrayList<Group> groups = new ArrayList<>();

    public Competition(String name) {
        super(name);
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

    /**
     * 通过名字获取这个比赛的参赛者
     * @param name
     * @return Competitor
     */
    public Competitor getCompetitor(String name){
        return competitors.get(name);
    }
}
