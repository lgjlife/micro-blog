package com.microblog.points.service.strategy;

public interface PointsStrategy {

    int getPoints(Integer type)  throws IllegalArgumentException;

}
