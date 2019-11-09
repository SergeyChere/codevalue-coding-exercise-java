package com.example.demo.services.manipulation;

public interface WaterManipulationService {

    int queryMaxCapacity(int id);
    int queryCurrentCapacity(int id);
    boolean addWater(int water, int id);
    void manageTankQuantity(int tankQuantity);
}
