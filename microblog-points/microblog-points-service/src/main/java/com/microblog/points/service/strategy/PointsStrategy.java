package com.microblog.points.service.strategy;

/**
 *功能描述
 * @author lgj
 * @Description  积分策略接口
 * @date 6/27/19
*/
public interface PointsStrategy {

    void handler(Long userId, Integer type) throws Exception;

}
