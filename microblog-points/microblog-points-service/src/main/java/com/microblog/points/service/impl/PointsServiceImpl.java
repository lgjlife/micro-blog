package com.microblog.points.service.impl;

import com.microblog.cache.localcache.LocalCache;
import com.microblog.common.result.BaseResult;
import com.microblog.common.result.WebResult;
import com.microblog.points.dao.mapper.PointsMapper;
import com.microblog.points.dao.mapper.SignMapper;
import com.microblog.points.dao.model.Points;
import com.microblog.points.dao.model.Sign;
import com.microblog.points.service.PointsService;
import com.microblog.points.service.strategy.PointsStrategy;
import com.microblog.points.service.strategy.PointsStrategyFactory;
import com.microblog.points.service.strategy.SignHistoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private PointsStrategyFactory pointsStrategyFactory;

    @Autowired
    private PointsMapper pointsMapper;

    @Autowired
    private SignMapper signMapper;


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
    public BaseResult handlePoints(Long userId, Integer type) {

        try{
            PointsStrategy pointsStrategy = pointsStrategyFactory.getPointsStrategy(type);
            if(pointsStrategy != null){
                pointsStrategy.handler(userId,type);
            }
            return  new WebResult(WebResult.RESULT_SUCCESS,"积分添加成功");

        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return  new WebResult(WebResult.RESULT_FAIL,ex.getMessage());
        }

    }

    /**
     *功能描述
     * @author lgj
     * @Description  获取当月的签到记录
     * @date 6/27/19
     * @param:
     * @return:
     *
    */
    public  List<Integer> getCurMonthSignDate(Long userId,int year , int month){

        Sign sign = signMapper.selectByYear(userId,year);
        if(sign ==  null){
            return  new ArrayList<>();
        }
        try{
            return  SignHistoryUtil.getSignHistoryByMonth(sign.getSignHistory(),year,month);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            return  new ArrayList<>();
        }

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
