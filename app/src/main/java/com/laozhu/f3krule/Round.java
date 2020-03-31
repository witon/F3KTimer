package com.laozhu.f3krule;

import java.util.ArrayList;

public class Round {
   private int roundId = 0;
   ArrayList<Group> groups = new ArrayList<>();
   Task task = null;


   public void setId(int id) {
      roundId = id;
   }

   public int getId() {
      return roundId;
   }

   public void setTask(Task task) {
      this.task = task;
   }

   public Task getTask(){
      return task;
   }

}


