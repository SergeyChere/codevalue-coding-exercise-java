package com.example.demo.services.management;

public interface WaterManagementService {
    int queryMaxCapacity();
    int queryCurrentCapacity();
    boolean addWater(int water);
}
