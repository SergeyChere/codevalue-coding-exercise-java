package com.example.demo.services.manipulation;

import com.example.demo.model.Tank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WaterManipulationServiceImpl implements WaterManipulationService {

    private List<Tank> tanks = new ArrayList<>();

    @Value(value = "${max.water.capacity}")
    private int maxCapacity;

    @Override
    public int queryMaxCapacity(int id) {
        return maxCapacity - queryCurrentCapacity(id);
    }

    @Override
    public int queryCurrentCapacity(int id) {
        Tank tank = tanks.get(id);
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
    synchronized public boolean addWater(int water, int id) {
        if (queryCurrentCapacity(id) == 0 && water <= maxCapacity) {
            Tank tank = tanks.get(id);
            tank.setCapacity(water);
            tank.setTime(System.currentTimeMillis());
            return true;
        }
        if (water > queryMaxCapacity(id)) {
            return false;
        }
        Tank tank = tanks.get(id);
        tank.setCapacity(tank.getCapacity() + water);
        tank.setTime(System.currentTimeMillis());
        return true;
    }

    @Override
    public void manageTankQuantity(int tankQuantity) {
        for (int i = 0; i < tankQuantity; i++) {
            tanks.add(new Tank());
        }
    }
}
