package com.microblog.common.module.points;

/**
 *功能描述
 * @author lgj
 * @Description 积分操作类型
 * @date 5/15/19
*/
public class PointsTypes {

    /***
     * 添加积分：每日签到
     */
    public static final Integer POINTS_ADD_TYPE_DAILY_SIGNATURE=10;
    /***
     * 添加积分:发布微博
     */
    public static final Integer POINTS_ADD_TYPE_PUBLISH_BLOG=11;

    /***
     * 减少积分：删除微博
     */
    public static final Integer POINTS_DEC_TYPE_DELETD_BLOG=12;

}
