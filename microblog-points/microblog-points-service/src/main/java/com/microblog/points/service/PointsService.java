package com.microblog.points.service;

import com.microblog.common.result.BaseResult;

public interface PointsService {
     BaseResult handlePoints(Long userId, Integer type);
     long queryPoints(Long userId);
}
