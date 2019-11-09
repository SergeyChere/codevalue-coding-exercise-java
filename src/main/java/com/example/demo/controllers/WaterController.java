package com.example.demo.controllers;

import com.example.demo.services.manipulation.WaterManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WaterController {

    @Autowired
    private WaterManipulationService waterManipulationService;

    @GetMapping("/getMaxCapacity/{id}")
    public int QueryMaxCapacity(@PathVariable("id")int id) {
        return waterManipulationService.queryMaxCapacity(id);
    }

    @GetMapping("/getCurrentCapacity/{id}")
    public int QueryCurrentCapacity(@PathVariable("id")int id) {
        return waterManipulationService.queryCurrentCapacity(id);
    }

    @GetMapping("/addWater/{water}/{id}")
    public boolean addWater(@PathVariable("water") int water, @PathVariable("id") int id) {
        return waterManipulationService.addWater(water, id);
    }
}
