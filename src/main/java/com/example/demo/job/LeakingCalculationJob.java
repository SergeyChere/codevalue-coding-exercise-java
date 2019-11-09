package com.example.demo.job;

import com.example.demo.model.Tank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Vector;

@Component
public class LeakingCalculationJob {

    @Autowired
    private Vector<Tank> tanks;

    @Scheduled(fixedRate = 1000)
    private void calculationLeaking() {
        for (Tank tank : tanks) {
            if (tank.getCurrentCapacity() != 0) {
                int tempValue = (int)((System.currentTimeMillis() - tank.getTime()));
                if (tempValue >= 60000) {
                    System.out.println(tank.getId()+": "+(System.currentTimeMillis()-tank.getTime()));
                    int currentCapacity = tank.getCurrentCapacity() - 1;
                    int maxCapacity = tank.getAvailableCapacity() + 1;
                    tank.setCurrentCapacity(currentCapacity);
                    tank.setAvailableCapacity(maxCapacity);
                    tank.setTime(System.currentTimeMillis());
                    if (tank.getCurrentCapacity() == 0) {
                        tank.setTime(0);
                    }
                }
            }
        }
    }
}
