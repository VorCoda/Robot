package org.example;

// класс описывающий модель G7, наследник общего класса робот
class ModelG7 extends Robot {

    //поля задаются автоматически в конструкторе если модель = 7
    boolean isWings;
    String specialModule;

    //конструктор класса
    public ModelG7(String name, String color, Integer height, boolean isOnFactory) {
        super(color, name, height, 7, isOnFactory); //модель устанавливается автоматически
        this.isWings = true;
        this.specialModule = "G7_AI_system";
    }

    //гетер наличия крыльев
    public Boolean getisWings() {
        return isWings;
    }

    //геттер наличия спец модуля
    public String getSpecialModule() {
        return specialModule;
    }

    //метод расчет коэфициента силы
    @Override
    public Double powerCoef() {
        return force * height * 2.2;
    }
}
