package com.example.demo.services;

import com.example.demo.configuration.TanksList;
import com.example.demo.model.Tank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WaterManipulationServiceImpl implements WaterManipulationService {

    @Autowired
    private TanksList tanksList;

    @Value(value = "${max.water.capacity}")
    private int maxCapacity;

    @Override
    public int queryMaxCapacity(int id) {
        return tanksList.getTanks().get(id).queryMaxCapacity(maxCapacity);
    }

    @Override
    public int queryCurrentCapacity(int id) {
        return tanksList.getTanks().get(id).queryCurrentCapacity();
    }

    @Override
    synchronized public boolean addWater(int water, int id) {
        if (queryCurrentCapacity(id) == 0 && water <= maxCapacity) {
            tanksList.getTanks().get(id).setCapacity(water);
            return true;
        }
        if (water > queryMaxCapacity(id)) {
            return false;
        }
        tanksList.getTanks().get(id).setCapacity(tanksList.getTanks().get(id).getCapacity() + water);
        return true;
    }

    @Scheduled(fixedRate = 60000)
    private void calculationLeaking() {
        for (Tank tank : tanksList.getTanks()) {
            if (tank.getCapacity() != 0) {
                int capacity = tank.getCapacity() - 1;
                tank.setCapacity(capacity);
            }
        }
    }
}
