package com.laozhu.f3krule;

import java.util.ArrayList;
import java.util.HashMap;

public class MapArrayContainer<T extends Entity> {
    private HashMap<String, T> tHashMap = new HashMap<>();
    private ArrayList<T> tArrayList = new ArrayList<>();


    public void append(T t){
        T tFound = tHashMap.get(t.getName());
        if(tFound != null){
            tHashMap.remove(tFound.getName());
            tArrayList.remove(tFound);
        }
        tArrayList.add(t);
        tHashMap.put(t.getName(), t);
    }

    public void clear() {
        tHashMap.clear();
        tArrayList.clear();
    }


    public T get(String name){
        return tHashMap.get(name);
    }
    public ArrayList<T> getArrayList() {
        return tArrayList;
    }
}
