package com.example.demo.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("management")
class WaterManipulationServiceImplTest {

    private Map<Boolean, Integer> counterByStatus;

    @Autowired
    WaterManipulationService waterManipulationService;

    @LocalServerPort
    private int randomServerPort;

    public void init(){
        counterByStatus = new ConcurrentHashMap<>();
        counterByStatus.put(true, 0);
        counterByStatus.put(false, 0);
    }

    @Test
    void addWaterTrue() {
        Assert.assertTrue(waterManipulationService.addWater(45, 0));
    }

    @Test
    void addWaterFalse() {
        Assert.assertFalse(waterManipulationService.addWater(52, 1));
    }

    @Test
    void addWaterRace() {
        init();
        sendRequestToServer("http://localhost:"+randomServerPort+"/api/addWater/5/2");
        Assert.assertEquals(10, counterByStatus.get(true).intValue());
        Assert.assertEquals(1, counterByStatus.get(false).intValue());
    }

    public void sendRequestToServer(String url) {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Stream.iterate(1, n -> n + 1).limit(11).parallel().forEach(x -> {
            try {
                ResponseEntity<Object> exc = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Object.class);
                counterByStatus.compute((Boolean) exc.getBody(), ((httpStatus, integer) -> integer+1));
            } catch (HttpClientErrorException exc) {
                exc.getStackTrace();
            }
        });
    }
}
