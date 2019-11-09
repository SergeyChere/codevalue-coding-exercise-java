package com.example.demo.runner;

import com.example.demo.model.Tank;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Vector;

@Component
@Profile("management")
public class WaterTankManagementRunner implements CommandLineRunner {

    @Value(value = "${tank.quantity}")
    private int tankQuantity;

    private Vector<Tank> tanks = new Vector<>();

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < tankQuantity; i++) {
            tanks.add(new Tank());
            tanks.get(i).setId(i);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(tanks.get(i)));
        }
    }
}
