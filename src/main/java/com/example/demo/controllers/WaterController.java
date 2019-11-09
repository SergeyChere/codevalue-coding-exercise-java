package com.example.demo.controllers;

import com.example.demo.model.Tank;
import com.example.demo.services.WaterManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;

@RestController
@RequestMapping("/api")
public class WaterController {

    @Autowired
    private WaterManipulationService waterManipulationService;

    @GetMapping("/getMaxCapacity/{id}")
    public int QueryMaxCapacity(@PathVariable("id") int id) {
        return waterManipulationService.queryMaxCapacity(id);
    }

    @GetMapping("/getCurrentCapacity/{id}")
    public int QueryCurrentCapacity(@PathVariable("id") int id) {
        return waterManipulationService.queryCurrentCapacity(id);
    }

    @GetMapping("/addWater/{water}/{id}")
    public boolean addWater(@PathVariable("water") int water, @PathVariable("id") int id) {
        return waterManipulationService.addWater(water, id);
    }

    /*
    Delete before sending
     */

    @Autowired
    private Vector<Tank> tanks;

    @GetMapping("/check")
    public Vector<Tank> checkVector() {
        return tanks;
    }
}
