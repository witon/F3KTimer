package com.laozhu.f3krule;

public class TimeStamp
{
    long time;
    String msg;
    public void start(String msg){
        this.msg = msg;
        time = System.currentTimeMillis();
    }
    public void stop(){
        System.out.print(msg);
        System.out.print(":");
        System.out.println(System.currentTimeMillis() - time);
    }
};

