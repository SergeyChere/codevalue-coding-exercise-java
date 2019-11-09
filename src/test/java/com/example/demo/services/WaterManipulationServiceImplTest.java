package com.example.demo.services;

import com.example.demo.services.manipulation.WaterManipulationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class WaterManipulationServiceImplTest {

    private int tankQuantity = 10;

    @InjectMocks
    private WaterManipulationService waterManipulationService;

    @Before
    private void init() {
        waterManipulationService.manageTankQuantity(tankQuantity);
        waterManipulationService.addWater(45, 0);
        waterManipulationService.addWater(40, 1);
    }

    @Test
    public void addWaterFalse() {
        Assert.assertFalse(waterManipulationService.addWater(6, 0));
    }

    @Test
    public void addWaterTrue() {
        Assert.assertTrue(waterManipulationService.addWater(10, 1));
    }
}
