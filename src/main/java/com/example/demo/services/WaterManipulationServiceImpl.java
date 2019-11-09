package com.example.demo.services;

import com.example.demo.model.Tank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WaterManipulationServiceImpl implements WaterManipulationService {

    private Tank tank = new Tank();

    @Value(value = "${max.water.capacity}")
    private int maxCapacity;

    @Override
    public int queryMaxCapacity() {
        return maxCapacity - queryCurrentCapacity();
    }

    @Override
    public int queryCurrentCapacity() {
        return tank.getCapacity();
    }

    @Override
    synchronized public boolean addWater(int water) {
        if (queryCurrentCapacity() == 0 && water <= maxCapacity) {
            tank.setCapacity(water);
            return true;
        }
        if (water > queryMaxCapacity()) {
            return false;
        }
        tank.setCapacity(tank.getCapacity() + water);
        return true;
    }

    @Scheduled(fixedRate = 60000)
    private void calculationLeaking() {
        if (tank.getCapacity() != 0) {
            int capacity = tank.getCapacity() - 1;
            tank.setCapacity(capacity);
        }
    }
}
