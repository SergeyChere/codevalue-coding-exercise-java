package com.example.demo.configuration;

import com.example.demo.model.Tank;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Vector;

@Configuration
public class WaterConfiguration {

    private Vector<Tank> tanks = new Vector<>();

    @Bean
    public Vector<Tank> getTanks() {
        return tanks;
    }
}
