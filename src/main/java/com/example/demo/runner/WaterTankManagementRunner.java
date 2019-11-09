package com.example.demo.runner;

import com.example.demo.configuration.WaterConfiguration;
import com.example.demo.model.Tank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("management")
public class WaterTankManagementRunner implements CommandLineRunner {

    @Value(value = "${tank.quantity}")
    private int tankQuantity;

    @Value(value = "${max.water.capacity}")
    private int maxCapacity;

    @Autowired
    WaterConfiguration waterConfiguration;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < tankQuantity; i++) {
            waterConfiguration.getTanks().add(new Tank(i, 0, maxCapacity, 0));
        }
    }
}
