package com.microblog.points.service.strategy;


import com.microblog.common.module.points.PointsTypes;

public class PointsStrategyImpl implements PointsStrategy {

    @Override
    public int getPoints(Integer type) {

        if(PointsTypes.POINTS_ADD_TYPE_DAILY_SIGNATURE.equals(type)){
            return  1;
        }

        else if(PointsTypes.POINTS_ADD_TYPE_PUBLISH_BLOG.equals(type)){

            return 2;
        }

        else if(PointsTypes.POINTS_DEC_TYPE_DELETD_BLOG.equals(type)){

            return -2;
        }

        return 0;
    }
}
