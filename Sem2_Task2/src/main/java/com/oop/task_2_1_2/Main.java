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
    public static void main(final String[] args) throws InterruptedException, IOException {

        Path path = Paths.get("pizzeriaParams.json");
        String jsonString = new String(Files.readAllBytes(path));
        Gson gson = new Gson();
        Params parameters = gson.fromJson(jsonString, Params.class);

        Pizzeria bestPizza = new Pizzeria(parameters.warehouseCapacity, parameters.bakersAmount,
                parameters.deliversAmount, parameters.ordersAmount, parameters.timeBetwOrders);

        for (int i = 0; i < parameters.deliversAmount; i++) {
            bestPizza.deliveryMen[i].thread.join();
        }

        Outputs rez = bestPizza.results();
        File file = new File("Recommendations.json");
        file.delete();

        FileOutputStream os = new FileOutputStream("Recommendations.json", true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        String temp = gson1.toJson(rez);
        bw.append(temp);
        bw.close();
    }
}
