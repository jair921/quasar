package com.challenge.quasar.domain.position.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @Test
    public void getLocationPosition() throws Exception {
        double[] distances = new double[]{100.0, 115.5, 142.7};
        double[] expected = new double[]{-58.315252587138595, -69.55141837312165};
        double[] points = positionService.getLocation(distances);

        assertEquals(expected[0], points[0]);
        assertEquals(expected[1], points[1]);
    }

}
