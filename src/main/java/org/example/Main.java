package org.example;

import org.example.data_loading.TrainerKt;
import org.example.data_loading.TrainingData;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<TrainingData> signs = TrainerKt.readTrainingData();
    }
}