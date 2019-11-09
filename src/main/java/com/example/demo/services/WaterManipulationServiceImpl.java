package com.example.demo.services;

import com.example.demo.configuration.WaterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WaterManipulationServiceImpl implements WaterManipulationService {

    @Autowired
    private WaterConfiguration waterConfiguration;

    @Value(value = "${max.water.capacity}")
    private int maxCapacity;

    @Override
    public int queryMaxCapacity(int id) {
        return waterConfiguration.getTanks().get(id).getMaxCapacity();
    }

    @Override
    public int queryCurrentCapacity(int id) {
        return waterConfiguration.getTanks().get(id).getCurrentCapacity();
    }

    @Override
    synchronized public boolean addWater(int water, int id) {
        if (queryCurrentCapacity(id) == 0 && water <= maxCapacity) {
            waterConfiguration.getTanks().get(id).setCurrentCapacity(water);
            waterConfiguration.getTanks().get(id).setMaxCapacity(maxCapacity - water);
            return true;
        }
        if (water > queryMaxCapacity(id)) {
            return false;
        }
        waterConfiguration.getTanks().get(id).setCurrentCapacity(waterConfiguration.getTanks().get(id).getCurrentCapacity() + water);
        waterConfiguration.getTanks().get(id).setMaxCapacity(waterConfiguration.getTanks().get(id).getMaxCapacity() - water);
        return true;
    }
}
