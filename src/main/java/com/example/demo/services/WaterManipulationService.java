package com.example.demo.services;

public interface WaterManipulationService {
    int queryMaxCapacity(int id);
    int queryCurrentCapacity(int id);
    boolean addWater(int water, int id);
}
