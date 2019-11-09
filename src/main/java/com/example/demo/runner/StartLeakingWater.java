package com.example.demo.runner;

import com.example.demo.services.WaterManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartLeakingWater implements CommandLineRunner {

    @Autowired
    private WaterManipulationService waterManipulationService;

    @Override
    public void run(String... args) throws Exception {
        waterManipulationService.fillingWater();
    }
}
