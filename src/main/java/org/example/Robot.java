package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public  abstract class Robot{
    String color;
    Integer height; //зависит от modelID
    Integer force; //зависит от modelID
    Integer modelID;
    String name;
    Boolean isOnFactory;
    ArrayList<Robot> robots = new ArrayList<>();

    //конструктор робота, силу force и высоту height устанавливает сам в зависимости от modelID
    public Robot(String name, String color, int height, int modelID, boolean isOnFactory){
        this.name = name;
        this.color = color;
        this.height = height;
        this.modelID = modelID;
        this.isOnFactory = isOnFactory;
        if(modelID > 777){
            this.force = 150;
        }
        else{
            this.force = 70;
        }
    }

    //геттер цвета
    public String GetColor(){
        return color;
    }
    //геттер высоты
    public Integer GetHeight(){
        return height;
    }
    //геттер силы
    public Integer GetForce(){
        return force;
    }
    //геттер состояния на заводе/нет
    public Boolean GetisOnFactory(){
        return isOnFactory;
    }

    //геттер имени
    public String GetName(){
        return name;
    }
    //геттер модели
    public Integer GetModelI(){
        return modelID;
    }

    //Фильтр роботов по моделям
    public static ArrayList<Robot> getFilteredByModelID(ArrayList<Robot> robots, int modelID){
        ArrayList<Robot> filteredRobots =  new ArrayList<>();
        for (Robot robot : robots){
            if(robot.GetModelI().equals(modelID)){
                filteredRobots.add(robot);
            }
        }
        return filteredRobots;
    }

    public  static Map<String, Integer> getModelsCount (ArrayList<Robot> robots){
        Map<String, Integer> map = new HashMap<>();
        for(Robot robot : robots){
            if(map.containsKey(robot.GetModelI().toString())){
                map.put(robot.GetModelI().toString(), map.get(robot.GetModelI().toString()) + 1);
            }
            else{
                map.put(robot.GetModelI().toString(), 2);
            }
        }
        return map;
    }

    // Обработчик для изменения высоты и силы
    public void updateRobot(int newForce, int newHeight){
        if(isOnFactory){
            if(newHeight < 70 && newHeight > 30){
                height = newHeight;
            }
            if(newForce < 600 && newForce > 70){
                force = newForce;
            }
            System.out.println("Robot was updated");
            isOnFactory = false; //выводим робота с завода
        }
        else {
            System.out.println("Robot isn't on Factory now or new parameters is worse");
        }
    }

    //отправляем робота на завод
    public void goToFactory(){
        isOnFactory = true;
    }

    //абстрактный метод вычисления коэфициента силы
    public abstract Double powerCoef();


    //сохранение списка роботов в файл, находящийся по пути path
    public void writeFile(ArrayList<Robot> robots, String path){
        try (FileWriter writer = new FileWriter(path)) {
            for (Robot r : robots) {
                writer.write(r.toString());
                writer.write(System.lineSeparator()); // добавляем новую строку после каждого элемента
            }
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //чтение файла, а также всех данных в виде экземпляра класса
    public ArrayList<Robot> readFile(String path) throws IllegalAccessException{
        ArrayList<Robot> robots = new ArrayList<>();
        File file = new File(path);
        //данные будут вводится в формате "color name modelID isWings specialModule"
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                String color = parts[0];
                String name = parts[1];
                int modelID = Integer.parseInt(parts[2]);
                boolean isWings = Boolean.parseBoolean(parts[3]);
                String specialModule = parts[4];

                // Создаем объект соответствующего класса на основе modelID
                Robot robot;
                //если модель 7(ModelG7)
                if (modelID == 7) {
                    robot = new ModelG7(name, color, height, isOnFactory);
                }
                else {
                    // Создадим другую модель
                    robot = new DefRobot(name, color, height, modelID, isOnFactory);
                }

                robot.addRobotToList();
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return robots;
    }

    //добавляет робота в список всех роботов
    public void addRobotToList(Robot this){
        robots.add(this);
    }
}



