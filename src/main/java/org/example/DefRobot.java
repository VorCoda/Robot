package org.example;

class DefRobot extends Robot {

    public DefRobot(String name, String color, Integer height, Integer modelID, Boolean isOnFactory) {
        super(name, color, height, modelID, isOnFactory);
        //если ввели модель = 7
        if (modelID == 7) {
            Robot robot = new ModelG7(name, color, height, isOnFactory);
        }

    }

    @Override
    public Double powerCoef() {
        return Double.parseDouble(String.valueOf(force * height));
    }
}
