package com.example.demo.runner;

import com.example.demo.services.manipulation.WaterManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WaterManageRunner implements CommandLineRunner {

    @Value(value = "${tank.quantity}")
    private int tankQuantity;

    @Autowired
    private WaterManipulationService waterManipulationService;

    @Override
    public void run(String... args) throws Exception {
        waterManipulationService.manageTankQuantity(tankQuantity);
    }
}
