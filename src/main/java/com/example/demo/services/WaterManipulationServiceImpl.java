package com.example.demo.services;

import com.example.demo.model.Tank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Vector;

@Service
public class WaterManipulationServiceImpl implements WaterManipulationService {

    @Autowired
    private Vector<Tank> tanks;

    @Value(value = "${max.water.capacity}")
    private int maxCapacity;

    @Override
    public int queryMaxCapacity(int id) {
        return tanks.get(id).getAvailableCapacity();
    }

    @Override
    public int queryCurrentCapacity(int id) {
        return tanks.get(id).getCurrentCapacity();
    }

    @Override
    synchronized public boolean addWater(int water, int id) {
        if (queryCurrentCapacity(id) == 0 && water <= maxCapacity) {
            tanks.get(id).setCurrentCapacity(water);
            tanks.get(id).setAvailableCapacity(maxCapacity - water);
            tanks.get(id).setTime(System.currentTimeMillis());
            return true;
        }
        if (water > queryMaxCapacity(id)) {
            return false;
        }
        tanks.get(id).setCurrentCapacity(tanks.get(id).getCurrentCapacity() + water);
        tanks.get(id).setAvailableCapacity(tanks.get(id).getAvailableCapacity() - water);
        tanks.get(id).setTime(System.currentTimeMillis());
        return true;
    }
}
