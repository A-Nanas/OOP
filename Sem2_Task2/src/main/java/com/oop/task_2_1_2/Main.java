package com.oop.task_2_1_2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;

import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Creates pizzeria with params taken from json file pizzeriaParams.json.
 * and putting recommendations after ending to Recommendations.json file
 */

public class Main {
    public static void main(final String[] args) throws IOException {

        Path path = Paths.get("..\\Sem2_Task2\\pizzeriaParams.json");
        String jsonString = new String(Files.readAllBytes(path));
        Gson gson = new Gson();
        Params parameters = gson.fromJson(jsonString, Params.class);

        Pizzeria bestPizza = new Pizzeria(parameters.warehouseCapacity, parameters.bakersAmount,
                parameters.deliversAmount, parameters.ordersAmount, parameters.timeBetwOrders);

        Outputs rez = bestPizza.results();
        File file = new File("..\\Sem2_Task2\\Recommendations.json");
        file.delete();

        FileOutputStream os = new FileOutputStream("Recommendations.json", true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        String temp = gson1.toJson(rez);
        bw.append(temp);
        bw.close();
    }
}
