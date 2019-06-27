package com.microblog.points.service.strategy;


import com.microblog.points.dao.mapper.PointsMapper;
import com.microblog.points.dao.mapper.SignMapper;
import com.microblog.points.dao.model.Points;
import com.microblog.points.dao.model.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

/**
 *功能描述
 * @author lgj
 * @Description  签到积分策略
 * @date 6/27/19
*/

@Component("SignPointsStrategy")
public class SignPointsStrategy extends AbstractPointsStrategy {

    @Autowired
    private PointsMapper pointsMapper;

    @Autowired
    private SignMapper signMapper;

    @Override
    public void handler(Long userId, Integer type)throws Exception {
        Integer point =   super.getPoints(type);

        LocalDate localDate = LocalDate.now();
        Sign sign = signMapper.selectByYear(userId,localDate.getYear());

        if(SignHistoryUtil.isSign(sign.getSignHistory(),localDate.getDayOfYear())){

            throw new SignException("今日已经签到！");
        }
        if(sign == null){
            sign = new Sign();
            sign.setUserId(userId);
            sign.setLastSignTime(new Date());
            sign.setSignHistory(SignHistoryUtil.sign(SignHistoryUtil.defaultsignHistory(),localDate.getDayOfYear()));
            sign.setYear(localDate.getYear());
            signMapper.insert(sign);
        }
        else{
            sign.setLastSignTime(new Date());
            sign.setSignHistory(SignHistoryUtil.sign(sign.getSignHistory(),localDate.getDayOfYear()));
            signMapper.updateByYear(sign,localDate.getYear());
        }



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
