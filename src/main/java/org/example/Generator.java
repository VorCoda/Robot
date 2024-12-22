package org.example;

import java.util.ArrayList;
import java.util.Random;
//класс, который содержит генератор/ры
public class Generator {

    //генерирует робот разных моделей
    public static ArrayList<Robot>  generateRobots(int count){
        ArrayList<Robot> robots = new ArrayList<>();
        Integer[] models = {23, 45, 155, 789, 707, 17, 7, 1, 345, 1001};
        String[] names = {"Bib", "Bob", "DD", "V", "G"};
        String[] colors = {"White", "Black", "Gray"};
        for(int i = 0; i < count; i ++){
            Random random = new Random();
            String name = names[random.nextInt(4)];
            String color = colors[random.nextInt(2)];
            int height = 10 + 13 + random.nextInt();
            int modelID = models[random.nextInt(9)];
            robots.add(new DefRobot(name, color, height, modelID, true));
        }
        return robots;
    }
}

