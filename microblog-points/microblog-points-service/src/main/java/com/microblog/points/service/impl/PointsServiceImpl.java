package com.microblog.points.service.impl;

import com.microblog.points.dao.mapper.PointsMapper;
import com.microblog.points.dao.model.Points;
import com.microblog.points.service.PointsService;
import com.microblog.points.service.strategy.PointsStrategy;
import com.microblog.points.service.strategy.PointsStrategyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *功能描述
 * @author lgj
 * @Description  积分操作实现类
 * @date 5/15/19
*/
@Service
public class PointsServiceImpl implements PointsService {


    private PointsStrategy pointsStrategy = new PointsStrategyImpl();

    @Autowired
    private PointsMapper pointsMapper;

    /**
     *功能描述
     * @author lgj
     * @Description  添加积分
     * @date 5/15/19
     * @param:
     * @return:
     *
    */
    @Override
    public boolean handlePoints(long userId,String addType) {
        int pointCount = pointsStrategy.getPoints(addType);
        Points points =  pointsMapper.selectByUserId(userId);
        long currentPointCount = points.getPoints()+pointCount;
        if(currentPointCount < 0){
            currentPointCount = 0;
        }
        points.setPoints(currentPointCount);
        return true;
    }
}
