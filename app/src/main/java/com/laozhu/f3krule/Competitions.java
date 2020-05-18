package com.laozhu.f3krule;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *比赛再内存中存储的容器
 *可以用于在内存中快速检索一个比赛
 *可以提供一个比赛的排序列表
 */

public class Competitions {
    private HashMap<String, Competition> competitionsHashMap = new HashMap<>();
    private ArrayList<Competition> competitionsArrayList = new ArrayList<>();


    public void appendCompetion(Competition competition){
        Competition competitionFound = competitionsHashMap.get(competition.getName());
        if(competitionFound != null){
            competitionsHashMap.remove(competitionFound.getName());
            competitionsArrayList.remove(competitionFound);
        }
        competitionsArrayList.add(competition);
        competitionsHashMap.put(competition.getName(), competition);
    }

    public void clear() {
        competitionsHashMap.clear();
        competitionsArrayList.clear();
    }


    public Competition getCompetion(String name){
        return competitionsHashMap.get(name);
    }
    public ArrayList<Competition> getCompetionsArrayList() {
        return competitionsArrayList;
    }
}
