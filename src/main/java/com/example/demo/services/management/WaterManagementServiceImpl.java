package com.example.demo.services.management;

import org.springframework.stereotype.Service;

@Service
public class WaterManagementServiceImpl implements WaterManagementService {

    @Override
    public int queryMaxCapacity() {
        return 0;
    }

    @Override
    public int queryCurrentCapacity() {
        return 0;
    }

    @Override
    public boolean addWater(int water) {
        return false;
    }
}
