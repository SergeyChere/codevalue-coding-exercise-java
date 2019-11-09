package com.example.demo.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Tank implements TanksCapacities {

    private int id;
    private int capacity;

    @Override
    public int queryMaxCapacity(int maxCapacity) {
        return maxCapacity - capacity;
    }

    @Override
    public int queryCurrentCapacity() {
        return capacity;
    }
}
