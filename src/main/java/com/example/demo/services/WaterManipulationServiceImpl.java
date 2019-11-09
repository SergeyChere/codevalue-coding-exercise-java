package com.example.demo.services;

import com.example.demo.model.Tank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WaterManipulationServiceImpl implements WaterManipulationService {

    private Tank tank = new Tank();

    @Value(value = "${max.water.capacity}")
    private int maxCapacity;

    @Override
    public int queryMaxCapacity() {
        return tank.getCapacity() - queryCurrentCapacity();
    }

    @Override
    public int queryCurrentCapacity() {
        if (tank.getCapacity() == 0) {
            return 0;
        }
        int returnValue;
        int tempValue = (int)((System.currentTimeMillis() - tank.getTime())/1000);
        if (tempValue < 60) {
            return tank.getCapacity();
        }
        else {
            tempValue = tempValue/60;
            if (tempValue < tank.getCapacity()) {
                return tank.getCapacity() - tempValue;
            }
            else {
                int difference = tempValue % tank.getCapacity();
                returnValue = tank.getCapacity() - difference;
            }
        }
        return returnValue;
    }

    @Override
    synchronized public boolean addWater(int water) {
        if (queryCurrentCapacity() == 0) {
            fillingWater();
            return true;
        }
        if (water > queryMaxCapacity()) {
            return false;
        }
        tank.setCapacity((tank.getCapacity() - queryMaxCapacity()) + water);
        tank.setTime(System.currentTimeMillis());
        return true;
    }

    @Override
    public void fillingWater() {
        tank.setCapacity(maxCapacity);
        tank.setTime(System.currentTimeMillis());
    }
}
