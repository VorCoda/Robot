package org.example;


import java.util.ArrayList;
import java.util.Map;

public class Main{
    public static void main(String[] args){
        ArrayList<Robot> robots = Generator.generateRobots(100);
        Map<String, Integer> map = Robot.getModelsCount(robots);
        Graph graph = new Graph(map);
        graph.setVisible(true);
        for (Robot r : robots){
            System.out.println(r.GetModelI());
        }

    }
}
