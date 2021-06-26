package com.oop.task_2_1_2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        Path path = Paths.get("C:\\Work\\OOP\\Sem2_Task2\\pizzeriaParams.json");
        String jsonString = new String(Files.readAllBytes(path));
        Gson gson = new Gson();
        Params parameters = gson.fromJson(jsonString, Params.class);

        Pizzeria bestPizza = new Pizzeria(parameters.warehouseCapacity, parameters.bakersAmount,
                parameters.deliversAmount, parameters.ordersAmount, parameters.timeBetwOrders);

        for (int i = 0; i < parameters.deliversAmount; i++){
            bestPizza.deliveryMen[i].thread.join();
        }

        Outputs rez = bestPizza.results();
        File file = new File("Recommendations.json");
        file.delete();

        FileOutputStream os = new FileOutputStream("Recommendations.json",true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        String temp = gson1.toJson(rez);
        bw.append(temp);
        bw.close();
    }
}
