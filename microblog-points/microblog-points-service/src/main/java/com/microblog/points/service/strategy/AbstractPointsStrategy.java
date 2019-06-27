package com.microblog.points.service.strategy;

import com.microblog.common.module.points.PointsTypes;

/**
 *功能描述
 * @author lgj
 * @Description  积分策略抽象类
 * @date 6/27/19
*/
public abstract class AbstractPointsStrategy implements PointsStrategy {



    public Integer getPoints(Integer type){

        Integer point = 0;

        if(PointsTypes.POINTS_ADD_TYPE_DAILY_SIGNATURE.getType() == type){
            return PointsTypes.POINTS_ADD_TYPE_DAILY_SIGNATURE.getPoint();

        }
        else if(PointsTypes.POINTS_ADD_TYPE_PUBLISH_BLOG.getType() == type){
            return PointsTypes.POINTS_ADD_TYPE_PUBLISH_BLOG.getPoint();

        }
        else if(PointsTypes.POINTS_DEC_TYPE_DELETD_BLOG.getType() == type){
            return PointsTypes.POINTS_DEC_TYPE_DELETD_BLOG.getPoint();

        }
        else {
            return null;
        }

    }



}
