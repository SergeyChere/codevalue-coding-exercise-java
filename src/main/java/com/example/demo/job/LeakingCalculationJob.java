package com.example.demo.job;

import com.example.demo.configuration.WaterConfiguration;
import com.example.demo.model.Tank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LeakingCalculationJob {

    @Autowired
    WaterConfiguration waterConfiguration;

    @Scheduled(fixedRateString = "${time.for.leaking}")
    private void calculationLeaking() {
        for (Tank tank : waterConfiguration.getTanks()) {
            if (tank.getCurrentCapacity() != 0) {
                int currentCapacity = tank.getCurrentCapacity() - 1;
                int maxCapacity = tank.getMaxCapacity() + 1;
                tank.setCurrentCapacity(currentCapacity);
                tank.setMaxCapacity(maxCapacity);
            }
        }
    }
}
