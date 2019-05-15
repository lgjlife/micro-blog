package com.microblog.points.service;

public interface PointsService {
     boolean handlePoints(long userId,String type);
     long queryPoints(long userId);
}
