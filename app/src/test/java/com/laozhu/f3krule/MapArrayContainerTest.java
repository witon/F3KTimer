package com.laozhu.f3krule;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class MapArrayContainerTest {

    @Test
    public void appendAndGetEntity() {
        MapArrayContainer<Entity> entitys = new MapArrayContainer<>();
        Entity entity1 = new Entity("entity1");
        entitys.append(entity1);
        Entity entityResult = entitys.get("entity1");
        assertEquals("entity1", entityResult.getName());
    }

    @Test
    public void getArrayList() {
        MapArrayContainer<Entity> entitys = new MapArrayContainer<>();
        Entity entity1 = new Entity("entity1");
        entitys.append(entity1);
        Entity entity2 = new Entity("entity2");
        entitys.append(entity2);

        HashMap<String, Entity> destEntitysHashMap = new HashMap<>();
        destEntitysHashMap.put(entity1.getName(), entity1);
        destEntitysHashMap.put(entity2.getName(), entity2);

        ArrayList<Entity> arrayList = entitys.getArrayList();
        for(Entity e : arrayList) {
            destEntitysHashMap.remove(e.getName());
        }
        assertTrue(destEntitysHashMap.isEmpty());
    }

    @Test
    public void appendEntity() {
        MapArrayContainer<Entity> mapArrayContainer = new MapArrayContainer<>();
        int testNum = 10;
        Entity [] entitiesArray = new Entity[testNum];
        for(int i=0; i<testNum; i++) {
            Entity e = new Entity(new Integer(i).toString());
            entitiesArray[i] = e;
        }
        for(int i=0; i<testNum; i++){
            mapArrayContainer.append(entitiesArray[i]);
        }
        //append duplicate
        for(int i=0; i<testNum; i++){
            mapArrayContainer.append(entitiesArray[i]);
        }
        ArrayList<Entity> entitiesArrayList = mapArrayContainer.getArrayList();
        assertEquals(testNum, entitiesArrayList.size());
    }
}