package com.example.demo.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Tank {

    private int id;
    private int currentCapacity;
    private int maxCapacity;
}
