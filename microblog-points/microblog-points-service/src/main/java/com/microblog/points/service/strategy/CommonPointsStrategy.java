package com.microblog.points.service.strategy;


import com.microblog.points.dao.mapper.PointsMapper;
import com.microblog.points.dao.model.Points;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *功能描述
 * @author lgj
 * @Description  签到积分策略
 * @date 6/27/19
*/

@Component("CommonPointsStrategy")
public class CommonPointsStrategy extends AbstractPointsStrategy {

    @Autowired
    private PointsMapper pointsMapper;



    @Override
    public void handler(Long userId, Integer type)throws Exception {
        Integer point =   super.getPoints(type);


        Points points =  pointsMapper.selectByUserId(userId);


        if(points == null){
            points = new Points();
            points.setUserId(userId);
            points.setPoints((long)point);
            pointsMapper.insert(points);
        }
        else {
            long currentPointCount = points.getPoints()+point;
            if(currentPointCount < 0){
                currentPointCount = 0;
            }
            points.setPoints(currentPointCount);
            pointsMapper.updateByPrimaryKey(points);
        }

    }
}
