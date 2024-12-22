package org.example;

import jdk.jfr.Category;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Graph extends JFrame {

    private final int DEFAULT_PADDING = 15;

    //конструктор
    public Graph(Map<String, Integer> map){
        init(map);
    }

    //инициализация
    private void init(Map<String, Integer> map){
        CategoryDataset dataset = createDataset(map);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart); //все окно панели с данными
        chartPanel.setBorder(BorderFactory.createEmptyBorder(DEFAULT_PADDING ,DEFAULT_PADDING,
                DEFAULT_PADDING ,DEFAULT_PADDING )); //параметры границ отступа
        chartPanel.setBackground(Color.WHITE); //цвет фона
        add(chartPanel);

        pack(); //сборка
        setTitle("Модели роботов и их кол-во"); //заголовок диаграммы
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //при нажатии на крест - закрытие окна
    }

    //Создаем диаграмму(chart)
    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("Модели роботов", "модель",
                "Кол-во роботов этой модели", dataset);
        return chart;
    }

    //создание набора данных
    private CategoryDataset createDataset(Map<String, Integer> map) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        map.forEach((key,value) -> {
            dataset.setValue(value, "ModelID" , key);
        });
        return dataset;
    }
}
