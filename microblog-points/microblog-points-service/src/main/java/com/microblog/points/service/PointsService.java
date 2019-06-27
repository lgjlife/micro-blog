package com.microblog.points.service;

import com.microblog.common.result.BaseResult;

import java.util.List;

public interface PointsService {
     BaseResult handlePoints(Long userId, Integer type);
     long queryPoints(Long userId);
     List<Integer> getCurMonthSignDate(Long userId, int year , int month);
}
