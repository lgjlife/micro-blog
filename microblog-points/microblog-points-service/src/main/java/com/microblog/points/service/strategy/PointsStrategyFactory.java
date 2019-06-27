package com.microblog.points.service.strategy;

import com.microblog.common.module.points.PointsTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointsStrategyFactory {

    @Autowired
    private SignPointsStrategy signPointsStrategy;

    @Autowired
    private CommonPointsStrategy commonPointsStrategy;


    public PointsStrategy  getPointsStrategy(int type){

        if(PointsTypes.POINTS_ADD_TYPE_DAILY_SIGNATURE.getType() == type){
            return signPointsStrategy;

        }
        else if(PointsTypes.POINTS_ADD_TYPE_PUBLISH_BLOG.getType() == type){
            return commonPointsStrategy;

        }
        else if(PointsTypes.POINTS_DEC_TYPE_DELETD_BLOG.getType() == type){
            return commonPointsStrategy;

        }
        else {
            return null;
        }
    }
}
