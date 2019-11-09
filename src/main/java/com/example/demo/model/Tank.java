package com.example.demo.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tank {

    private int id;
    private int currentCapacity;
    private int availableCapacity;
    private long time;
}
