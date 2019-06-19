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
    public boolean handlePoints(Long userId,Integer addType) {
        long pointCount = pointsStrategy.getPoints(addType);
        Points points =  pointsMapper.selectByUserId(userId);
        if(points == null){
            points = new Points();
            points.setUserId(userId);
            points.setPoints(pointCount);
            pointsMapper.insert(points);
        }
        else {
            long currentPointCount = points.getPoints()+pointCount;
            if(currentPointCount < 0){
                currentPointCount = 0;
            }
            points.setPoints(currentPointCount);
            pointsMapper.updateByPrimaryKey(points);
        }


        return true;
    }

    /**
     *功能描述
     * @author lgj
     * @Description 　获取用户的积分数
     * @date 5/15/19
     * @param:
     * @return:
     *
    */
    @Override
    public long queryPoints(Long userId) {

        if(true){
            throw new NullPointerException("积分出现异常");
        }
        Points points = pointsMapper.selectByUserId(userId);
        if(points == null){
            return  0;
        }
        return points.getPoints();
    }
}
