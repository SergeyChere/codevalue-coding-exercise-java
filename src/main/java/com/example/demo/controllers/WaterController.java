package com.example.demo.controllers;

import com.example.demo.services.WaterManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WaterController {

    @Autowired
    private WaterManipulationService waterManipulationService;

    @GetMapping("/getMaxCapacity")
    public int QueryMaxCapacity() {
        return waterManipulationService.queryMaxCapacity();
    }

    @GetMapping("/getCurrentCapacity")
    public int QueryCurrentCapacity() {
        return waterManipulationService.queryCurrentCapacity();
    }

    @GetMapping("/addWater/{water}")
    public boolean addWater(@PathVariable("water") int water) {
        return waterManipulationService.addWater(water);
    }
}
