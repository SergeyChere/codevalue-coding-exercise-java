package com.example.demo.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
