package com.laozhu.f3krule;

/**
 * 进行比赛的安排
 */
public class CompetitionArrangement {
    Competition competition = null;
    int lastRoundId = 0;

    /**
     * 新建一个比赛
     * @return
     */
    public Competition newCompetition(String name){
        competition = new Competition(name);
        lastRoundId = 0;
        return competition;
    }

    public Competition getCompetition(){
        return competition;
    }

    /**
     * 把一个参赛者加到比赛
     * @param competitor
     */
    public void addCompetitor2Competition(Competitor competitor){
    }

    private int getNextRoundId(){
       lastRoundId ++;
       return lastRoundId;
    }


    /**
     * 在比赛中新建一个轮次
     * @param task
     * @return
     */
    public Round addRound2Competition(Task task) {
        Round round = new Round();
        round.setTask(task);
        round.setId(getNextRoundId());
        return round;
    }

}
