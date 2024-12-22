package org.example;

import java.sql.*;
import java.util.ArrayList;

class Database {

    private static Connection connection;

    private static Statement statement;

    public static void connectDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        statement = connection.createStatement();
    }

    //Создание таблицы Роботы
    public static void createTableRobots() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS Robots (" +
                "color TEXT, " +
                "height INTEGER" +
                "force INTEGER, " +
                "modelID INTEGER, " +
                "name TEXT, " +
                "isOnFactory BOOLEAN)");
    }

    //Создание таблицы роботы модели G7
    public static void createTableRobotsG7() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS RobotsG7 (" +
                "color TEXT, " +
                "height INTEGER" +
                "force INTEGER, " +
                "modelID INTEGER, " +
                "name TEXT, " +
                "isOnFactory BOOLEAN, " +
                "isWings BOOLEAN, " +
                "specialModule TEXT)");
    }

    //заполнения таблиц Robots и RobotsG7
    public static void inputData(ArrayList<? extends Robot> robots) throws SQLException {
        for (Robot robot : robots) {
            if (robot instanceof ModelG7) {
                ModelG7 robotG7 = (ModelG7) robot;
                statement.execute(String.format("INSERT INTO RobotsG7" +
                                "VALUES ('%s', '%d', '%d', '%d', '%s', '%b','%b', '%s')",
                        robotG7.GetColor(), robotG7.GetHeight(), robotG7.GetForce(),
                        robotG7.GetModelI(), robotG7.GetName(), robotG7.GetisOnFactory(),
                        robotG7.isWings, robotG7.specialModule));

            } else {
                statement.execute(String.format("INSERT INTO Robots" +
                                "VALUES ('%s', '%d', '%d', '%d', '%s', '%b')", robot.GetColor(), robot.GetHeight(),
                        robot.GetForce(), robot.GetModelI(), robot.GetName(), robot.GetisOnFactory()));
            }

        }
    }

    //удаление данных - таблиц
    public static void deleteData() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS Robots");

    }

    //чтение информации
    public static ArrayList<Robot> readData() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Robots");
        ArrayList<Robot> robots = getDataFromResultSet(resultSet);
        return robots;
    }

    //получение информации из набора данных
    public static ArrayList<Robot> getDataFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Robot> robots = new ArrayList<>();
        while (resultSet.next()) {
            String color = resultSet.getString("color");
            Integer height = resultSet.getInt("height");
            Integer force = resultSet.getInt("force");
            Integer modelID = resultSet.getInt("modelID");
            String name = resultSet.getString("name");
            Boolean isOnFactory = resultSet.getBoolean("isOnFactory");

            Robot robot = new DefRobot(name, color, height, modelID, isOnFactory);

            robots.add(robot);
        }

        return robots;
    }

    //Закрытие БД
    public static void closeDB() {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
