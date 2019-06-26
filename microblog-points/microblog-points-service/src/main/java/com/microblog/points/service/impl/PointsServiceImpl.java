package com.microblog.points.service.impl;

import com.microblog.cache.localcache.LocalCache;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.WebResult;
import com.microblog.points.service.PointsService;
import com.microblog.points.dao.mapper.PointsMapper;
import com.microblog.points.dao.model.Points;
import com.microblog.points.service.strategy.PointsStrategy;
import com.microblog.points.service.strategy.PointsStrategyImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *功能描述
 * @author lgj
 * @Description  积分操作实现类
 * @date 5/15/19
*/

@Slf4j
@Service
public class PointsServiceImpl implements PointsService {

    private static String POINT_CACHE_KEY_PREFX = "POINT:";

    private PointsStrategy pointsStrategy = new PointsStrategyImpl();

    @Autowired
    private PointsMapper pointsMapper;

    @Autowired
    private LocalCache<String, Points> pointCache;

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
    public BaseResult handlePoints(Long userId, Integer addType) {
        long pointCount = 0;
        try{
            pointCount = pointsStrategy.getPoints(addType);
        }
        catch(Exception ex){
            return new WebResult(0,ex.getMessage());
        }

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
        pointCache.set(POINT_CACHE_KEY_PREFX+userId,points);
        return  new WebResult(1,"积分添加成功");
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

        Points points =  pointCache.get(POINT_CACHE_KEY_PREFX+userId);

        if(points == null){
            points = pointsMapper.selectByUserId(userId);
            if(points == null){
                return  0;
            }
            pointCache.set(POINT_CACHE_KEY_PREFX+userId,points);
        }

        return points.getPoints();
    }
}
