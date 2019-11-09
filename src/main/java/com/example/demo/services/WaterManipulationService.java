package com.example.demo.services;

public interface WaterManipulationService {

    int queryMaxCapacity();
    int queryCurrentCapacity();
    boolean addWater(int water);
}
