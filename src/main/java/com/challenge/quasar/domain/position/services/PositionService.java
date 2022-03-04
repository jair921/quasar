package com.challenge.quasar.domain.position.services;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PositionService {

    @Autowired
    private Environment env;

    public double[] getLocation(double[] distances) {

        NonLinearLeastSquaresSolver solver = new
                NonLinearLeastSquaresSolver(
                        new TrilaterationFunction(secretPositions(), distances),
                        new LevenbergMarquardtOptimizer()
                 );

        LeastSquaresOptimizer.Optimum optimum = solver.solve();

        return optimum.getPoint().toArray();
    }

    private double[][] secretPositions() {
        String[] namesSatellites = (String[]) env.getProperty("satellites.names").split(",");

        double[][] positions = new double[namesSatellites.length][];

        String[] listNamesSatellites;
        int i = 0;
        for( String name : namesSatellites){
            String[] point = env.getProperty("satellites." + name + ".point").split(",");
            positions[i] = Arrays.stream(point)
                    .map(Double::valueOf)
                    .mapToDouble(Double::doubleValue)
                    .toArray();
            i++;
        }

        return positions;
    }
}