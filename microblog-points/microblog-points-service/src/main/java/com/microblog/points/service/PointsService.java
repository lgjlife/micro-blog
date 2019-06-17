package com.microblog.points.service;

public interface PointsService {
     boolean handlePoints(Long userId,Integer type);
     long queryPoints(Long userId);
}
